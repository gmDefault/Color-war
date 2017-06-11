package machine_a_glace;

public class Coordonnees {
	private int ligne;
	private int colonne;

	public Coordonnees(int ligne, int col) {
		this.ligne = ligne;
		this.colonne = col;
	}

	public int getLigne() {
		return this.ligne;
	}

	public int getCol() {
		return this.colonne;
	}
}
