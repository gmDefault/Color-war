package machine_a_glace;

public enum Chiffre implements Expr {
	Un("1"), Deux("2"), Trois("3"), Quatre("4"), Cinq("5"), Six("6"), Sept("7"), Huit("8"), Neuf("9");
	
	String affichage;
	
	private Chiffre(String s){
		affichage=s;
	}
	
	public String toString(){
		return affichage;
	}
	
	@Override
	public boolean isComportement() {
		return false;
	}

	@Override
	public boolean isOperateur() {
		return false;
	}

	@Override
	public boolean isChiffre() {
		return true;
	}

}
