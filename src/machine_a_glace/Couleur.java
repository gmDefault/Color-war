package machine_a_glace;

public enum Couleur {
	Bleu("B"), Rouge("R"), Neutre("N");
	
	String affichage;
	
	private Couleur(String s){
		this.affichage = s;
	}
	
	public String toString(){
		return affichage;
	}
}
