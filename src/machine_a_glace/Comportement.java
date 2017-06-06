package machine_a_glace;

public enum Comportement implements Expr {
	Explore("X"), Attack("A");

	String affichage;

	private Comportement(String s) {
		affichage = s;
	}

	public boolean isComportement() {
		return true;
	}

	public boolean isOperateur() {
		return false;
	}

}
