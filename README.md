# 🌳 Albero N-Ario tramite Vettori Paralleli (Java)

Progetto d'esame per il corso di **Laboratorio di Algoritmi e Strutture Dati** (A.A. 2024-2025).

Questo progetto implementa una struttura dati ad **albero n-ario** (dove il numero massimo di figli per nodo non è noto a priori) in linguaggio **Java**. L'albero memorizza informazioni e garantisce che ogni nuovo nodo inserito diventi l'ultimo figlio del nodo padre specificato.

---

## 🏗️ Struttura Dati: I Vettori Paralleli

[cite_start]L'implementazione si discosta dalle classiche strutture a puntatori collegati (linked nodes) per rispettare i vincoli di progetto, avvalendosi di due array paralleli gestiti dinamicamente:

1. [cite_start]**`nodi[]`**: Array contenente le istanze dei nodi effettivi.
2. **`padri[]`**: Array che memorizza la gerarchia. [cite_start]Il nodo in posizione *i-esima* di questo array rappresenta il padre del nodo situato nella medesima posizione *i-esima* nell'array `nodi`. La radice ha valore `null` in questo array.

L'espansione della memoria avviene in fase di esecuzione: ogni volta che si aggiunge un nodo, gli array vengono ridimensionati espandendo la loro capacità di `+1` e copiando i vecchi valori, garantendo che i nuovi figli occupino le ultime posizioni della struttura.

---

## ⚙️ Funzionalità e Algoritmi Implementati

[cite_start]La classe `AlberoVP` implementa tutti i metodi richiesti dalle specifiche, con un'attenzione particolare alle performance (complessità asintotica):

### Inserimento e Modifica
* **`setRadice(String info)`**: Inserisce la radice in un albero vuoto (Complessità: O(n)).
* **`setNuovaRadice(String info)`**: Inserisce una nuova radice, declassando la vecchia radice a figlia della nuova (Complessità: O(n)).
* **`addFiglio(NodoVP u, String info)`**: Aggiunge un nuovo figlio al nodo `u` (Complessità: O(n)).
* **`setInfoNodoAlbero(NodoVP v, String info)`**: Modifica il payload testuale di un nodo esistente (Complessità: O(n)).

### Interrogazione e Metriche
* [**`getRadice()`** / **`getPadre(NodoVP v)`**: Navigazione base verso l'alto (Complessità: O(n)).
* **`getFigli(NodoVP v)`** / **`getNumFigli(NodoVP v)`**: Navigazione verso il basso con restituzione di liste dinamiche (Complessità: O(n))[cite: 1620, 1625].
* **`getLivello(NodoVP v)`**: Calcola la profondità di un nodo risalendo l'albero fino alla radice (Complessità: O(n^2)).
* **`getAltezza()`**: Calcola l'altezza complessiva dell'albero (Complessità: O(n^2)).
* **`getNumFoglie(NodoVP v)`**: Ricerca ricorsiva che esplora i sotto-alberi per contare i nodi privi di figli (Complessità: O(n^2)).

### Visite dell'Albero (Attraversamenti Grafi)
Per esplorare l'albero, sono stati implementati algoritmi iterativi supportati da strutture dati ausiliarie del framework `java.util`:
* **Visita in Profondità (DFS - `visitaProfondita`)**: Usa uno `Stack` (`LinkedList` gestita con inserimento in testa) per esplorare l'albero ramo per ramo (Complessità: O(n^2)). I figli vengono caricati nello stack in ordine inverso per garantire la visita da sinistra verso destra.
* **Visita in Ampiezza (BFS - `visitaAmpiezza`)**: Usa una `Queue` (coda FIFO) per analizzare l'albero livello per livello (Complessità: O(n^2))[cite: 1631].

### Rappresentazione Strutturale
* **`stringaAlbero()`**: Metodo ricorsivo che serializza l'albero in una stringa formattata annidata (es. `A[B[], C[D[]]]`), rispettando fedelmente il formato richiesto dalle specifiche di progetto.

---

## 💻 Compilazione ed Esecuzione (Test Suite)

[cite_start]Il progetto include un file `Main.java` che funge da driver di test, verificando tutte le operazioni principali descritte.

Per compilare ed eseguire il progetto da terminale:

## 💻 Come eseguire il progetto

1. Clona la repository sul tuo ambiente locale.
2. Assicurati di avere installato il **JDK** (Java Development Kit).
3. Compila i file `.java` situati nella cartella `src/alberovp/`:
   `javac src/alberovp/*.java`
4. Esegui il file `Main` per lanciare la suite di test integrata:
   `java -cp src alberovp.Main`
