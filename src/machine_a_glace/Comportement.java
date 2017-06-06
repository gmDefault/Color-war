package machine_a_glace;

public enum Comportement implements Expr {
	Explore("X"), Attack("A"), Protect("P"), Kamikaze("K");

	String affichage;

	private Comportement(String s) {
		affichage = s;
	}
	
	public String toString(){
		return affichage;
	}
	
	public boolean isComportement() {
		return true;
	}

	public boolean isOperateur() {
		return false;
	}

}
