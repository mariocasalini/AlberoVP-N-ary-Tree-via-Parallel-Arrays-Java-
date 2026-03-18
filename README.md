# 🌳 AlberoVP: N-ary Tree via Parallel Arrays (Java)

Progetto per l'esame finale di **Laboratorio di Algoritmi e Strutture Dati** (A.A. 2024/2025).

Questo progetto consiste nella progettazione e implementazione in **Java** di una struttura dati ad albero n-ario (dove non è noto a priori il numero massimo di figli per nodo). A differenza delle classiche implementazioni basate su puntatori ai figli, questo albero è stato modellato utilizzando **due strutture indicizzate (array) parallele**.

---

## 🚀 Architettura e Scelte Implementative

L'implementazione rispetta vincoli architetturali stringenti e mette in mostra solide basi algoritmiche:

### 1. Struttura a Vettori Paralleli
L'intera topologia dell'albero è gestita tramite due array dinamici:
* [cite_start]`nodi[]`: Memorizza le istanze dei nodi effettivi[cite: 1587].
* `padri[]`: Memorizza le dipendenze gerarchiche. [cite_start]Il nodo in posizione *i-esima* del secondo array è il padre del nodo nella medesima posizione del primo array[cite: 1588].

La logica di inserimento prevede il ridimensionamento dinamico degli array in fase di runtime (tramite `System.arraycopy`) per espandere la memoria man mano che nuovi nodi vengono aggiunti, garantendo che le nuove foglie vengano collocate nelle ultime posizioni disponibili.

### 2. Attraversamento Grafi (BFS & DFS)
Il progetto include l'implementazione degli algoritmi classici di esplorazione:
* [cite_start]**Visita in Profondità (DFS):** Implementata in modo **iterativo** utilizzando uno *Stack* (`LinkedList.addFirst` / `removeFirst`), garantendo un'esplorazione ramo per ramo a prova di stack-overflow[cite: 1630].
* [cite_start]**Visita in Ampiezza (BFS):** Implementata utilizzando una *Queue* (coda FIFO) per analizzare i nodi livello per livello[cite: 1631].

### 3. Ricorsione e Topologia
Per le operazioni di calcolo strutturale, sono stati implementati algoritmi ricorsivi ottimizzati:
* Calcolo delle **foglie totali** tramite esplorazione dei sotto-alberi.
* [cite_start]Calcolo dell'**altezza massima** dell'albero[cite: 1627].
* [cite_start]Generazione della **notazione a stringa formattata** dell'albero (es. `A[B[], C[D[]]]`), utile per la serializzazione e la visualizzazione testuale della gerarchia[cite: 1632].

### 4. Analisi Asintotica
Il codice sorgente è interamente documentato, includendo per ogni metodo implementato l'analisi della **complessità computazionale asintotica** (Notazione Big-O), evidenziando la consapevolezza dell'impatto prestazionale delle operazioni sulle strutture ad array rispetto a quelle a liste concatenate (es. O(1), O(n), O(n^2)).

---

## 💻 Come eseguire il progetto

1. Clona la repository sul tuo ambiente locale.
2. Assicurati di avere installato il **JDK** (Java Development Kit).
3. Compila i file `.java` situati nella cartella `src/alberovp/`:
   `javac src/alberovp/*.java`
4. Esegui il file `Main` per lanciare la suite di test integrata:
   `java -cp src alberovp.Main`

La classe `Main` copre il 100% delle funzionalità richieste: inserimento nodi, calcolo del livello, visite BFS/DFS, sostituzione radice e rappresentazione a stringa.
