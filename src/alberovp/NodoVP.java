// Autori: Marco Arcuri (7162557), Mario Casalini (7164154), Tommaso Scoti (7164697)
package alberovp;

public class NodoVP {
	// Informazione memorizzata nel nodo.
	private String info;
	// Indice del nodo corrispondente alla posizione negli array di AlberoVP.
	private int indice;

	public NodoVP(String dato) {
		info = dato;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public String toString() {
		return info;
	}
}
