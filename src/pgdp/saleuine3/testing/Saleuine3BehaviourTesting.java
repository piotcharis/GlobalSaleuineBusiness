package pgdp.saleuine3.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pgdp.pingulib.datastructures.trees.BST;
import pgdp.saleuine3.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Saleuine3BehaviourTesting {
    private final PrintStream out = System.out;
    private final ByteArrayOutputStream outCatch = new ByteArrayOutputStream();
    private ArrayList<Integer> ids;
    private BST<MarketOrder> orderDB;

    @BeforeEach
    void reset() {
        orderDB = new BST<>();
        ids = new ArrayList<>();
    }

    @Test
    public void testBestelluinRunAmount() {
        /*
         * Hier teste ich, ob die Methode run() im Bestelluin die übergegebene Anzahl an MarketOrders erstellt
         * und im orderDB hinzufügt, indem ich die Größe des orderDB
         * mit der erwarteten Anzahl an Bestellungen vergleiche.
         */
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(6);
        ids.add(7);
        ids.add(8);

        Bestelluin bestelluin = new Bestelluin(ids, 5, orderDB, "testPingu1");

        bestelluin.run();

        assertEquals(5, orderDB.size());
    }

    @Test
    public void testBestelluinRunAddMarketOrder() {
        /*
         * Hier teste ich, ob die Methode run() in Bestelluin eine schon vorhandene MarketOrder updatet
         * und nicht neu einfügt, indem ich überprüfe, ob die Größe des orderDBs nach dem run immer noch
         * 1 ist und die Anzahl in ordered des MarketOrders nicht 0 ist.
         */
        ids.add(1);

        MarketOrder order = new MarketOrder(1, 10);
        orderDB.insert(order);

        Bestelluin bestelluin = new Bestelluin(ids, 1, orderDB, "testPingu2");

        bestelluin.run();

        assertNotEquals(0, orderDB.get(order).getOrdered());
        assertEquals(1, orderDB.size());
    }

    @Test
    public void testBestelluinRunInsert() {
        /*
         * Hier teste ich, ob die funktion run() in Bestelluin am Ende den richtigen String in der Konsole ausgibt,
         * indem ich es mit dem erwarteten String vergleiche.
         */

//        BITTE FÜR DIE MANUELLE KORREKTUR AUSKOMMENTIEREN!!
//        System.setOut(new PrintStream(outCatch));

        ids.add(1);
        ids.add(2);
        ids.add(3);

        Bestelluin bestelluin = new Bestelluin(ids, 3, orderDB, "testPingu3");

        bestelluin.run();

//        BITTE FÜR DIE MANUELLE KORREKTUR AUSKOMMENTIEREN!!
//        assertEquals("testPingu3 finished working.", outCatch.toString().trim());
    }

    @Test
    public void testPinguFoodGeneratorRunNormal() {
        /*
         * Hier teste ich, ob die Methode run() in PinguFoodGenerator die MarketOrders in einem Normalfall
         * die richtige Anzahl gibt, indem ich die Supply Variablen der einzelnen MarketOrders mit den erwarteten
         * Werten vergleiche, wobei hier maximal 50 PinguFood oder so viele bis die Order fullfilled ist
         * gegeben werden sollen.
         */
        orderDB.insert(new MarketOrder(0, 10));
        orderDB.insert(new MarketOrder(1, 20));
        orderDB.insert(new MarketOrder(2, 5));
        orderDB.insert(new MarketOrder(3, 100));
        orderDB.insert(new MarketOrder(4, 2));
        orderDB.insert(new MarketOrder(5, 35));

        ids.add(0);
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);


        PinguFoodGenerator generator = new PinguFoodGenerator(10, 5, orderDB, ids, "generator 1");

        generator.run();

        assertEquals(10, orderDB.get(new MarketOrder(0, 0)).getSupply());
        assertEquals(20, orderDB.get(new MarketOrder(1, 0)).getSupply());
        assertEquals(5, orderDB.get(new MarketOrder(2, 0)).getSupply());
        assertEquals(50, orderDB.get(new MarketOrder(3, 0)).getSupply());
        assertEquals(2, orderDB.get(new MarketOrder(4, 0)).getSupply());
        assertEquals(35, orderDB.get(new MarketOrder(5, 0)).getSupply());
    }

    @Test
    public void testPinguFoodGeneratorEmpty() {
        /*
         * Hier teste ich, ob die Methode run() in PinguFoodGenrator bei dem Sonderfall, dass orderDB leer ist,
         *  nicht abbricht, sondern kein Pingufood gibt und am Ende den richtigen String in der Konsole ausgibt, indem
         * ich überprüfe, ob orderDB nach dem run immer noch leer ist und den String auf der Konsole
         * mit dem erwarteten String vergleiche.
         */

//        BITTE FÜR DIE MANUELLE KORREKTUR AUSKOMMENTIEREN!! - https://zulip.in.tum.de/#narrow/stream/894-PGdP-W12H02--.20Global.20Saleuin.20Business/topic/Testing/near/456162
//        System.setOut(new PrintStream(outCatch));

        ids.add(0);
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);

        PinguFoodGenerator generator = new PinguFoodGenerator(10, 5, orderDB, ids, "generator 2");

        generator.run();

        assertTrue(orderDB.isEmpty());

//        BITTE FÜR DIE MANUELLE KORREKTUR AUSKOMMENTIEREN!!
//        assertEquals("generator 2 supplied 0.", outCatch.toString().trim());
    }

    @Test
    public void testPinguFoodGeneratorRunComplex() {
        /*
         * Hier teste ich, ob die run() Methode in PinguFoodGenerator, mit einer responsibleIds Liste,
         * die sowohl Ids, die im orderDB existieren als auch nicht, richtig nur die Ids, die existieren,
         * Pingufood liefert und die totalSupplied am Ende richtig berechnet. Das mache ich, indem ich die Supply
         * Variable der einzelnen MarketOrders mit den erwarteten Werten so wie oben vergleiche und die TotalSupplied Variable
         * mit der Summe der Werten der einzelnen MarketObjekten vergleiche.
         */
        orderDB.insert(new MarketOrder(0, 15));
        orderDB.insert(new MarketOrder(1, 25));
        orderDB.insert(new MarketOrder(3, 300));
        orderDB.insert(new MarketOrder(4, 5));
        orderDB.insert(new MarketOrder(5, 65));
        orderDB.insert(new MarketOrder(7, 50));

        ids.add(0);
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(6);
        ids.add(7);


        PinguFoodGenerator generator = new PinguFoodGenerator(10, 10, orderDB, ids, "generator 3");

        generator.run();

        assertEquals(15, orderDB.get(new MarketOrder(0, 0)).getSupply());
        assertEquals(25, orderDB.get(new MarketOrder(1, 0)).getSupply());
        assertEquals(100, orderDB.get(new MarketOrder(3, 0)).getSupply());
        assertEquals(5, orderDB.get(new MarketOrder(4, 0)).getSupply());
        assertEquals(65, orderDB.get(new MarketOrder(5, 0)).getSupply());
        assertEquals(50, orderDB.get(new MarketOrder(7, 0)).getSupply());

        assertEquals(260, generator.getTotalSupply());
    }

    @Test
    public void testRunBusiness() {
        List<ArrayList<Integer>> bestelluinIDLists = new ArrayList<>();
        List<ArrayList<Integer>> generatorIds = new ArrayList<>();


        for (int i = 0; i < 1000; i++) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                tempList.add(j);
            }
            bestelluinIDLists.add(tempList);
            generatorIds.add(tempList);
        }

        SaleuinBusiness business = new SaleuinBusiness(bestelluinIDLists, generatorIds, 10, 1, 5);

        business.runBusiness();

        System.out.println(business.getOrderDB());
    }
}
