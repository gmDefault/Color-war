package machine_a_glace;

public enum Couleur {
	Bleu("Bleu"), Rouge("Rouge"), Neutre("Neutre");
	
	String affichage;
	
	private Couleur(String s){
		this.affichage = s;
	}
	
	public String toString(){
		return affichage;
	}
}
