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
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1)
					terrain[i][j].setCase(Contenu.Obstacle);
			}

		}
		
		

	    Terrain.terrain[15][2].setCase(Contenu.Opérateur);
	    Terrain.terrain[15][2].setOp(Operateur.PointVirgule);
	    Terrain.terrain[15][27].setCase(Contenu.Opérateur);
	    Terrain.terrain[15][27].setOp(Operateur.Deuxpoints);
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

	public static Case casexy(int ligne, int colonne) {
		return terrain[ligne][colonne];
	}

	public static int getTaille() {
		return taille;
	}

}
