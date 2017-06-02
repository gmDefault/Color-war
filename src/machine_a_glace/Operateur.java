package machine_a_glace;

public enum Operateur {
	PointVirgule(";"), Deuxpoints(":");

	String affichage;

	private Operateur(String s) {
		affichage = s;
	}

	public String toString() {
		return affichage;
	}
}
