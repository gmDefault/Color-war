package machine_a_glace;

public enum Comportement implements Expr {
	Explore("X"), Attack("A"), Protect("P"), Kamikaze("K"),Suivre("F"),Rapprocher("N"), Zero("0"), Un("1"),Deux("2"),Trois("3"),Quatre("4"),Cinq("5"),Six("6"),Sept("7"),Huit("8"),Neuf("9");

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
