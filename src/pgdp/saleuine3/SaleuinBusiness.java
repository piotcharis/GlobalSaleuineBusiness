package pgdp.saleuine3;

import java.util.ArrayList;
import java.util.List;

import pgdp.pingulib.datastructures.trees.BST;

public class SaleuinBusiness {
    private BST<MarketOrder> orderDB;
    private List<ArrayList<Integer>> bestelluinIDLists;
    private List<ArrayList<Integer>> generatorIDLists;

    private int ordersPerBestelluin;
    private int supplyRunsPerGenerator;
    private int maxGeneratorOutput;

    public SaleuinBusiness(List<ArrayList<Integer>> bestelluinIDLists, List<ArrayList<Integer>> generatorIDLists,
                           int ordersPerBestelluin, int supplyRunsPerGenerator, int maxGeneratorOutput) {
        this.orderDB = new BST<>();
        this.bestelluinIDLists = bestelluinIDLists;
        this.generatorIDLists = generatorIDLists;
        this.ordersPerBestelluin = ordersPerBestelluin;
        this.supplyRunsPerGenerator = supplyRunsPerGenerator;
        this.maxGeneratorOutput = maxGeneratorOutput;
    }

    public BST<MarketOrder> getOrderDB() {
        return orderDB;
    }

    public void runBusiness() {
        Thread bestelluins = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bestelluinIDLists.size(); i++) {
                    Bestelluin bestelluin =
                            new Bestelluin(bestelluinIDLists.get(i), ordersPerBestelluin, orderDB,
                                    "Bestelluin " + (i + 1));
                    Thread thread = new Thread(bestelluin);

                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread generators = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < generatorIDLists.size(); i++) {
                    PinguFoodGenerator generator =
                            new PinguFoodGenerator(maxGeneratorOutput, supplyRunsPerGenerator, orderDB,
                                    generatorIDLists.get(i),
                                    "Generator " + (i + 1));
                    Thread thread = new Thread(generator);

                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        bestelluins.start();
        generators.start();

        try {
            bestelluins.join();
            generators.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
