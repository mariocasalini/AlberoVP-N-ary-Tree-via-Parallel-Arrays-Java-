// Autori: Marco Arcuri (7162557), Mario Casalini (7164154), Tommaso Scoti (7164697)
package alberovp;

import java.util.LinkedList;

public class AlberoVP {
	private NodoVP[] padri;
	private NodoVP[] nodi;

	// costruttore
	public AlberoVP() {

		// Array parallelo a `nodi[]` che contiene per ogni posizione il riferimento al
		// padre del nodo, o null se è la radice.
		padri = new NodoVP[0];
		// Array che contiene tutti i nodi dell'albero; la posizione di ciascun nodo
		// indica il suo indice univoco.
		nodi = new NodoVP[0];
	}

	// Aggiunge la radice all'albero se non esiste ancora.
	// Complessità: O(n)
	public NodoVP setRadice(String info) {
		if (getNumNodi() > 0) {
			// Se l'albero ha già una radice, non inserisce nulla e restituisce null
			System.out.println("L'albero ha già una radice. Usa setNuovaRadice per cambiarla.");
			return null;
		}
		NodoVP v = new NodoVP(info);
		addNodo(null, v);
		return v;
	}

	// Sostituisce la radice esistente con una nuova, impostando la vecchia come
	// figlio della nuova radice.
	// Complessità: O(n)
	public NodoVP setNuovaRadice(String info) {
		// Se non c'è ancora radice, basta creare la prima
		NodoVP oldR = getRadice();
		if (oldR == null) {
			return setRadice(info);
	   	}

		// Creo il nuovo nodo radice e lo aggiungo in coda
		NodoVP newR = new NodoVP(info);
		addNodo(null, newR);

		// Ricalcolo il padre della vecchia radice:
		// oldR.getIndice() non cambia, quindi uso direttamente quell'indice
		padri[oldR.getIndice()] = newR;

		return newR;
	}

	// Ritorna l'indice dell'elemento che ha padre null (la radice)
	// Complessità: O(n)
	public int indiceRadice() {
		for (int i = 0; i < getNumNodi(); i++)
			if (padri[i] == null)
				return i;
		System.out.println("Nessuna radice presente nell'albero");
		return -1;
	}

	// Ritorna il nodo radice.
	// Complessità: O(n)
	public NodoVP getRadice() {
		int idx = indiceRadice();
		if (idx == -1) {
			return null;
		}
		return nodi[idx];
	}

	// Restituisce il numero di nodi presenti nell'albero.
	// Complessità: O(1)
	public int getNumNodi() {
		return nodi.length;
	}

	// Restituisce il padre di un dato nodo.
	// Complessità: O(n)
	public NodoVP getPadre(NodoVP v) {
		if (v == null || !presente(v))
			return null;
		return padri[v.getIndice()];

	}

	// Calcola il livello (profondità) di un nodo rispetto alla radice.
	// Complessità: O(n^2)
	public int getLivello(NodoVP v) {
		if (!presente(v) || v == null)
			return -1;

		int livello = 0;
		NodoVP temp = getPadre(v);
		while (temp != null) {
			livello++;
			temp = getPadre(temp);
		}
		return livello;
	}

	// Restituisce i figli diretti di un nodo.
	// Complessità: O(n)
	public LinkedList<NodoVP> getFigli(NodoVP v) {
		LinkedList<NodoVP> figli = new LinkedList<>();
		if (v == null || !presente(v))
			return figli;

		for (int i = 0; i < getNumNodi(); i++) {
			if (padri[i] == v) {
				figli.add(nodi[i]);
			}
		}
		return figli;
	}

	// Aggiunge un nuovo figlio al nodo u, o radice se u è null e l'albero vuoto.
	// Complessità: O(n)
	public NodoVP addFiglio(NodoVP u, String info) {
		NodoVP v = new NodoVP(info);
		// Se l'albero è vuoto e u == null, diventa radice:
		if (u == null && getNumNodi() == 0) {
			return setRadice(info);
		}

		if (!presente(u)) {
			// Padre non trovato → non inserisco
			System.out.println("Errore: il nodo padre specificato non è presente nell'albero.");
			return null;
		}

		addNodo(u, v);
		return v;
	}

	// Aggiunge internamente un nodo v sotto il padre u.
	// Complessità: O(n)
	private void addNodo(NodoVP u, NodoVP v) {
		// Se il padre u non è presente nell'albero, stampa un messaggio d'errore e
		// interrompe l'inserimento.
		if (u != null && !presente(u)) {
			System.out.println("Errore: il nodo padre specificato non è presente nell'albero.");
			return;
		}

		// Calcola l'indice del nuovo nodo come il numero corrente di nodi.
		int n = getNumNodi();
		v.setIndice(n);// Imposta l'indice al nodo v (utile per ricerche e riferimenti).
		System.out.println("Nodo Aggiunto");

		// Espande l'array dei nodi di una posizione, copiando quelli esistenti.
		NodoVP[] temp = new NodoVP[n + 1];
		System.arraycopy(nodi, 0, temp, 0, n);
		nodi = temp;
		nodi[n] = v;// Aggiunge il nuovo nodo in ultima posizione.

		// Espande l'array dei padri con lo stesso procedimento.
		temp = new NodoVP[n + 1];
		System.arraycopy(padri, 0, temp, 0, n);
		padri = temp;
		padri[n] = u; // Imposta il padre del nuovo nodo.
	}

	// Restituisce il numero di figli diretti di un nodo.
	// Complessità: O(n)
	public int getNumFigli(NodoVP v) {
		// Se il nodo non è presente o è nullo, restituisce -1.
		if (!presente(v) && v != null)
			return -1;

		// restituisce quanto è grande la lista dei figli.
		LinkedList<NodoVP> figli = getFigli(v);
		return figli.size();
	}

	// Restituisce l'informazione salvata in un nodo.
	// Complessità: O(n)
	public String getInfoNodo(NodoVP v) {
		// Se il nodo è nullo o non presente, restituisce un messaggio d'errore.
		if (v == null || !presente(v))
			return "nodo non trovato";

		// Cerca il nodo nell'array e ne restituisce l'informazione.
		for (int i = 0; i < getNumNodi(); i++) {
			if (nodi[i].equals(v))
				return nodi[i].getInfo();
		}
		return "Il nodo inserito non è presente nell'albero";
	}

	// Calcola l'altezza dell'albero (numero di livelli).
	// Complessità: O(n^2)
	public int getAltezza() {
		// Se non ci sono nodi, l'altezza è 0.
		if (getNumNodi() <= 0)
			return 0;

		int altezza = 0;
		// Scorre tutti i nodi e trova il livello massimo.
		for (int i = 0; i < getNumNodi(); i++) {
			if (getLivello(nodi[i]) > altezza)
				altezza = getLivello(nodi[i]);
		}
		return altezza + 1; // L'altezza è il livello massimo + 1.
	}

	// Conta ricorsivamente il numero di foglie nel sottoalbero di v.
	// Complessità: O(n^2)
	public int getNumFoglie(NodoVP v) {
		// Se il nodo è nullo o non presente, restituisce 0.
		if (v == null || !presente(v))
			return 0;

		// Ottiene la lista dei figli del nodo.
		LinkedList<NodoVP> figli = getFigli(v);

		// Se non ha figli, è una foglia.
		if (figli.isEmpty()) {
			System.out.println("Il nodo inserito è una foglia");
			return 1;
		}

		// Conta ricorsivamente le foglie nei sottoalberi dei figli.
		int conta = 0;
		for (NodoVP f : figli) {
			conta += getNumFoglie(f);
		}
		return conta;
	}

	// Modifica l'informazione di un nodo esistente.
	// Complessità: O(n)
	public void setInfoNodoAlbero(NodoVP v, String info) {
		// Se il nodo è nullo o non presente, stampa un messaggio e termina.
		if (v == null || !presente(v)) {
			System.out.println("Nodo non presente nell'albero");
			return;
		} else {
			// Cerca il nodo e ne aggiorna l'informazione.
			for (int i = 0; i < getNumNodi(); i++) {
				if (nodi[i].equals(v)) {
					nodi[i].setInfo(info);
					return;
				}
			}
		}
	}

	// Visita in profondità (DFS) restituisce l'ordine dei nodi.
	// Complessità: O(n^2)
	public LinkedList<NodoVP> visitaProfondita() {
		LinkedList<NodoVP> risultato = new LinkedList<>();
		LinkedList<NodoVP> stack = new LinkedList<>(); // Stack per DFS

		NodoVP radice = getRadice();
		if (radice == null)
			return risultato;

		stack.addFirst(radice); // Inserisce la radice come primo nodo da esplorare.

		while (!stack.isEmpty()) {
			NodoVP u = stack.removeFirst(); // Estrae nodo da visitare.
			risultato.addLast(u); // Aggiunge il nodo al risultato.

			// Inserisce i figli in ordine inverso per visitarli nell’ordine corretto.
			LinkedList<NodoVP> figli = getFigli(u);
			for (int i = figli.size() - 1; i >= 0; i--) {
				stack.addFirst(figli.get(i));
			}
		}
		return risultato;
	}

	// Visita in ampiezza (BFS) restituisce l'ordine dei nodi.
	// Complessità: O(n^2)
	public LinkedList<NodoVP> visitaAmpiezza() {
		LinkedList<NodoVP> risultato = new LinkedList<>();
		LinkedList<NodoVP> coda = new LinkedList<>(); // Coda per BFS.
		NodoVP radice = getRadice();
		if (radice == null)
			return risultato;

		coda.addLast(radice);// Aggiunge la radice come primo nodo da visitare.

		while (!coda.isEmpty()) {
			NodoVP u = coda.removeFirst(); // Estrae nodo corrente.
			risultato.addLast(u);// Aggiunge il nodo al risultato.

			// Aggiunge tutti i figli alla coda.
			for (NodoVP f : getFigli(u)) {
				coda.addLast(f);
			}
		}
		return risultato;
	}

	// Restituisce una stringa che rappresenta tutto l'albero in notazione
	// A[B,C[D]].
	// Complessità: O(n^2)
	public String stringaAlbero() {
		NodoVP radice = getRadice();
		if (radice == null)
			return "";
		return stringaSottoAlbero(radice); // Chiamata ricorsiva al metodo.
	}

	// Helper ricorsivo per stringaAlbero().
	// Complessità: O(n^2)
	private String stringaSottoAlbero(NodoVP v) {
		if (v == null || !presente(v))
			return "nodo non presente";
		StringBuilder sb = new StringBuilder(); // Aggiunge l'informazione del nodo.
		sb.append(v.getInfo());
		sb.append("[");

		LinkedList<NodoVP> figli = getFigli(v);
		for (int i = 0; i < figli.size(); i++) {
			sb.append(stringaSottoAlbero(figli.get(i))); // Richiama ricorsivamente per ogni figlio.
			if (i < figli.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// Restituisce le sole informazioni dei figli di un nodo come lista di stringhe.
	// Complessità: O(n)
	public LinkedList<String> getInfoFigli(NodoVP v) {
		LinkedList<String> info = new LinkedList<>();
		if (v == null || !presente(v))
			return info;
		for (NodoVP f : getFigli(v)) {
			info.add(f.getInfo()); // Aggiunge solo la stringa associata al figlio.
		}
		return info;
	}

	// Verifica se un nodo è presente nell'albero.
	// Complessità: O(n)
	public boolean presente(NodoVP v) {
		for (int i = 0; i < getNumNodi(); i++) {
			if (nodi[i] != null && nodi[i].equals(v))
				return true;
		}
		return false;
	}
}
