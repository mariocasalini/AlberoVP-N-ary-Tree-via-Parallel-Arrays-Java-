// Marco Arcuri 7162557, Mario Casalini 7164154, Tommaso Scoti 7164697
package alberovp;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {

		AlberoVP albero = new AlberoVP();

		// 1) setRadice e getRadice
		NodoVP root = albero.setRadice("A");
		System.out.println("Radice: " + albero.getRadice().getInfo()); // A

		// 2) setNuovaRadice
		NodoVP newRoot = albero.setNuovaRadice("R");
		System.out.println("Nuova radice: " + newRoot.getInfo()); // R
		System.out.println("Vecchia radice diventa figlia di R: " + albero.getFigli(newRoot).get(0).getInfo()); // A

		// 3) addFiglio
		NodoVP nB = albero.addFiglio(newRoot, "B");
		NodoVP nC = albero.addFiglio(newRoot, "C");
		albero.addFiglio(nB, "D");
		albero.addFiglio(nB, "E");
		albero.addFiglio(nC, "F");

		// 4) getNumNodi
		System.out.println("Numero di nodi: " + albero.getNumNodi()); // 6

		// 5) getPadre / getLivello
		System.out.println("Padre di B: " + albero.getPadre(nB).getInfo()); // R
		System.out.println("Livello di E: " + albero.getLivello(albero.getFigli(nB).get(1)) + "\n"); // 2

		// 6) getFigli / getInfoFigli
		LinkedList<NodoVP> figliR = albero.getFigli(newRoot);
		System.out.print("Figli di R: ");
		for (NodoVP v : figliR)
			System.out.print(v.getInfo() + " ");
		System.out.println();

		System.out.println("InfoFigli di B: " + albero.getInfoFigli(nB));

		// 7) getInfoNodo
		System.out.println(
				"Info nodo F: " + albero.getInfoNodo(nC.getIndice() >= 0 ? albero.getFigli(newRoot).get(1) : null));

		// 8) getAltezza e getNumFoglie
		System.out.println("Altezza albero: " + albero.getAltezza()); // 3
		System.out.println("Num foglie da R: " + albero.getNumFoglie(newRoot)); // 3 (D,E,F)

		// 9) setInfoNodoAlbero
		System.out.println("Cambiamo info di C in 'C2'");
		albero.setInfoNodoAlbero(nC, "C2");
		System.out.println("Nuova info C: " + albero.getInfoNodo(nC));

		// 10) visite: profondità e ampiezza
		System.out.print("Visita in profondità: ");
		for (NodoVP v : albero.visitaProfondita())
			System.out.print(v.getInfo() + " ");
		System.out.println();

		System.out.print("Visita in ampiezza: ");
		for (NodoVP v : albero.visitaAmpiezza())
			System.out.print(v.getInfo() + " ");
		System.out.println();

		// 11) stringaAlbero
		System.out.println("Stringa intero albero: " + albero.stringaAlbero());

	}
}
