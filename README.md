**Global Saleuine Business**

Das Semester neigt sich langsam dem Ende und die Saleuine Claudia und Karl-Heinz blicken stolz auf ihr Unternehmen, das sie nur durch deine Hilfe zum globalen Marktführer erweitern konnten. Doch aktuell braucht das Unternehmen noch einiges an aktivem Management durch Claudia und Karl-Heinz. Um dies zu ändern, bitten dich die beiden ein letzte Mal um Hilfe.

Das Unternehmen der Saleuine ist inzwischen global tätig. Dazu gibt es ein weit verzweigtes Netzwerk an Märkten, in denen das von allen geliebte PinguFood verkauft wird. Beliefert werden die Märkte von einem PinguFoodGenerator, wobei Claudia und Karl-Heinz noch jede einzelne Bestellung absegnen und jede Lieferung händisch freigeben müssen. Dies sollst du nun ändern. Wie immer haben sich die Saleuine bereits einen Lösungsansatz für dich einfallen lassen (genauere Beschreibungen dazu findest du weiter unten bei den Aufgaben):

Bestelluine: Jeder Bestelluin erhält einen Zuständigkeitsbereich in Form einer ID-Liste, sowie die Anzahl an Bestellungen, die er/sie verarbeiten soll. Wie die Bestellungen angenommen/erzeugt werden, muss dich nicht kümmern.

PinguFoodGenerator: Claudia und Karl-Heinz sind inzwischen soweit, zusätzliche PinguFoodGenerator zu bauen. Jedem dieser Generatoren weisen sie genau wie den Bestelluinen einen Zuständigkeitsbereich zu. Zusätzlich hat jeder Generator eine maximale Menge an PinguFood, die er pro Lieferung verschicken kann.

SaleuinBusiness: In ihrer Zentrale speichern Claudia und Karl-Heinz alle Markt-Bestellungen in einer Datenbank. Begeistert von deiner Arbeit in Woche 08 nutzen sie dafür einen binären Suchbaum (BST) aus MarketOrder-Objekten, wobei die Markt-ID der relevante Schlüssel für den Baum ist.

Das Wichtigste ist, dass alle Bestelluine und PinguFoodGenerator parallel arbeiten können und dabei keine Daten verloren gehen.

**Das Template**

Das Template enthält bereits einigen Code:

Den BST aus Woche 08 mit einigen Modifikationen:

Eine neue Methode T get(T value). Diese funktioniert identisch zu contains mit dem einen Unterschied, dass nicht true oder false zurückgegeben wird, sondern das zu value equivalente Element, falls dieses im Baum gespeichert ist, oder null.

Die Methode insert gibt jetzt ein boolean zurück, welches angibt, ob der insert erfolgreich war.

Die benötigten Änderungen wurden entsprechend auch in BSTNode und ihren Unterklassen dort implementiert, wo sie nötig waren.

Die Klasse MarketOrder mit allen benötigten Methoden. Ein Objekt dieser Klasse symbolisiert eine Bestellung des Markts mit der entsprechenden id. Außerdem werden noch Werte für die bestellten (ordered) und die gelieferten (supply) Mengen an PinguFood gespeichert. Grundsätzlich gilt, dass ein Markt nie mehr PinguFood annimmt, als er bisher bestellt hat.

Die Klassen Bestelluin, PinguFoodGenerator und SaleuinBusiness mit den benötigten Attributen und Konstruktoren.

**Threadsichere Datenstruktur**

Zuerst musst du dich darum kümmern, dass die verwendete Datenstruktur threadsicher ist. Wie du das machst, bleibt dir überlassen. Du darfst Monitore, Semaphoren und Locks (selbstgeschrieben (auch aus P-Aufgaben) oder aus der Java Standardbibliothek) verwenden. Achte aber darauf, dass der Performance-Vorteil von Parallelisierung nicht durch zu striktes Synchronisieren verloren geht.

**BST Funktionalität**

Diese Aufgabe testet, ob der BST mit nicht-parallelen Operationen korrekt funktioniert. Dazu werden die (an die oben erwähnten Änderungen angepassten) Tests von W08P02 ausgeführt. Alle Tests sind public und sollten ohne Fehler durchlaufen. Hier sind die Tests der P-Aufgabe. Sie wurden für diese Aufgabe lediglich um Tests für get erweitert.

**MarketOrder-BST threadsicher**

Diese Aufgabe testet, ob die in SaleuinBusiness verwendete Datenstruktur wirklich threadsicher ist. Hierfür gibt es keinen public-Test. Achte also darauf, deine Implementierung selbst ausführlich zu testen!

**Saleuin Business**

Jetzt geht es darum, das parallel laufende Geschäft der Saleuine zu implementieren

**Bestelluin**

Implementiere die run-Methode der Klasse Bestelluin. Die Methode soll ordersToMake-mal mit Hilfe der vorgegebenen Methode eine MarketOrder erstellen und diese in die orderDB einfügen oder, wenn bereits eine Bestellung des entsprechenden Markts gespeichert ist, die bestehende Bestellung updaten (addMarketOrder). Außerdem soll abschließend immer (also nach Abschluss aller Bestellungen oder bei einer Unterbrechung des Threads) folgender String auf der Konsole ausgegeben werden: "<name>⎵finished⎵working."
  
**PinguFoodGenerator**
  
Implementiere die run-Methode der Klasse PinguFoodGenerator. Die Methode soll supplyRuns-mal durch responsibleIDs iterieren und jeweils den zur ID zugehörigen Markt mit maximal maxSupply PinguFood beliefern. Die gesamte Menge an PinguFood, mit der der Generator Märkte beliefert hat, soll in totalSupply gespeichert werden. Außerdem soll abschließend immer (also nach Abschluss aller Lieferungen oder bei einer Unterbrechung des Threads) folgender String auf der Konsole ausgegeben werden: "<name>⎵supplied⎵<totalSupply>."

**SaleuinBusiness**
  
Implementiere jetzt die Methode runBusiness der Klasse SaleuinBusiness. Die Methode soll für jede in bestelluinIDLists enthaltene Liste einen Bestelluin bzw. für jede in generatorIDLists einen PinguFoodGenerator erstellen und in jeweils einem eigenen Thread deren run-Methode ausführen. Für die Anzahl der Bestellungen bzw. die Anzahl der Lieferungs-Iterationen sollen orderPerBestelluin bzw. supplyRunsPerGenerator genutzt werden. Als Namen sollen "Bestelluin⎵<i>" bzw. "Generator⎵<i>" genutzt werden, wobei i die Position der ID-Liste in der entsprechend übergeordneten Liste entspricht (angefangen mit 1!). Die Methode soll außerdem erst terminieren, sobald alle gestarteten Threads beendet wurden. maxSupply jedes PinguFoodGenerator soll maxGeneratorOutput entsprechen.
die parallele Implementierung sollte maximal 70% der Zeit einer equivalenten sequenziellen Implementierung benötigen!
  
**Testing**
  
Abschließend musst du noch ein paar Tests schreiben, wie du es bereits von den PPL-Aufgaben gewohnt bist.

Füge der Klasse Saleuine3BehaviourTesting Tests für folgende Methoden hinzu:
  
Bestelluin.run(): Die Tests sollen die korrekte lineare(=sequentielle) Funktionalität der Methode überprüfen. Threadsicherheit und Funktionalität bei paralleler Ausführung müssen nicht getestet werden.
  
PinguFoodGenerator.run(): Die Tests sollen die korrekte lineare(=sequentielle) Funktionalität der Methode überprüfen. Threadsicherheit und Funktionalität bei paralleler Ausführung müssen nicht getestet werden.
  
Teste beide Methoden mit jeweils (mindestens) drei Testfälle. Kommentiere kurz, was und wie (z.B. "ich teste die Addition, indem ich meine Implementierung mit dem korrekten Ergebnis vergleiche.") getestet wird.
  
Hinweis: leider kannst du auf Artemis nicht auf die Konsolenausgabe zugreifen. Falls du diese trotzdem testen möchtest, findest du in diesem Zulip Post weitere Informationen.
  
**Hinweise**
  
Hilfsmethoden und Attribute sind erlaubt (auch Getter). Wichtig ist aber, dass die mitgelieferten Konstruktoren und die Methode Bestelluin.generateMarketOrder nicht geänder werden (das Initialisieren von Hilfsattributen in den Konstruktoren ist aber ok, allerdings ohne die Parameterliste zu verändern).
Methoden und Klassen aus der Java Standardbibliothek sind erlaubt. Für orderDB muss aber der mitgelieferte (und threadsicher gemachte) BST verwendet werden.
Sollten mehrere Testfälle nach dem selben Prinziep testen ("wie"), reicht die Begründung beim ersten Test und Hinweise auf diese bei den übrigen.
