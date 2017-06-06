package machine_a_glace;

public enum Operateur implements Expr {
	PointVirgule(";"), Deuxpoints(":");

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
}
