package machine_a_glace;

public enum Contenu {
	Vide("."), Obstacle("O"), Expression("X"), Robot("R"), Joueur("J"),Creer("c"), Bonus_Malus("B");

	private String affichage;

	private Contenu(String s) {
		affichage = s;
	}

	public String toString() {
		return affichage;
	}
}
