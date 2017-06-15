package machine_a_glace;

public enum Operateur implements Expr {
	PointVirgule(";"), Deuxpoints(":"),Priorite(">"),Choixequi("|"),Choix("/"),Star("*"),Bonus("B");

	String affichage;

	private Operateur(String s) {
		affichage = s;
	}

	public String toString() {
		return affichage;
	}

	@Override
	public boolean isComportement() {
		return false;
	}

	@Override
	public boolean isOperateur() {
		return true;
	}

	@Override
	public boolean isChiffre() {
		return false;
	}
}
