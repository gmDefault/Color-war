package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class Terrain {
	private static int taille = 30;
	public static Case terrain[][] = new Case[taille][taille];

	private Terrain() {

	}

	public static void initialiser() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				Terrain.terrain[i][j] = new Case();
				if (i == 0 || i == taille - 1)	Terrain.terrain[i][j].setCase(Contenu.Obstacle);
			}
		}
	}

	public static void afficher() {
		for (int i = 0; i < taille; i++) {
			for (Case c : terrain[i]) {
				if (c.isJoueur())
					System.out.print("J");
				else
					System.out.print(c.toString());
				System.out.print("|");

			}
			System.out.println();
			for (int j = 0; j < taille; j++) {
				System.out.print("--");
			}
			System.out.println();
		}
	}

	public static int getTaille() {
		return taille;
	}

	public static Case casexy(int ligne, int colonne) {
		return terrain[ligne][colonne];
	}

}
