package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class Terrain {
	private static int taille = 10;
	public static Case terrain[][] = new Case[taille][taille];

	private Terrain() {

	}

	public static void initialiser() {
		for (int i = 0; i < taille; i++) {
			if (i == 0 || i == taille - 1) {
				for (int j = 0; j < taille; j++) {
					terrain[i][j] = new Case();
					terrain[i][j].setCase(Contenu.Obstacle);
				}
			} else {
				for (int j = 0; j < taille; j++) {
					if (j == 0 || j == taille - 1) {
						terrain[i][j] = new Case();
						terrain[i][j].setCase(Contenu.Obstacle);
					} else {
						terrain[i][j] = new Case();
						double r = Math.random();
						if (r < 0.20) {
							terrain[i][j].setCase(Contenu.Obstacle);
						} else if (r < 0.40) {
							terrain[i][j].setCase(Contenu.Opérateur);
							terrain[i][j].setOp(Operateur.PointVirgule);
						} else if (r < 0.60) {
							terrain[i][j].setCase(Contenu.Opérateur);
							terrain[i][j].setOp(Operateur.Deuxpoints);
						}
					}

				}
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
	
	public static Case casexy(int ligne, int colonne){
		return terrain[ligne][colonne];
	}

	
}
