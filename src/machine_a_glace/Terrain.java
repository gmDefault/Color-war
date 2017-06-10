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
		
		

	    Terrain.terrain[3][15].setCase(Contenu.Expression);
	    Terrain.terrain[3][15].setExpr(Operateur.PointVirgule);
	    Terrain.terrain[26][15].setCase(Contenu.Expression);
	    Terrain.terrain[26][15].setExpr(Operateur.Deuxpoints);
	    Terrain.terrain[4][15].setCase(Contenu.Expression);
	    Terrain.terrain[4][15].setExpr(Comportement.Attack);

	    Terrain.terrain[5][15].setCase(Contenu.Expression);
	    Terrain.terrain[5][15].setExpr(Operateur.Deuxpoints);
	    Terrain.terrain[6][15].setCase(Contenu.Expression);
	    Terrain.terrain[6][15].setExpr(Operateur.PointVirgule);
	    Terrain.terrain[7][15].setCase(Contenu.Expression);
	    Terrain.terrain[7][15].setExpr(Comportement.Kamikaze);
	    Terrain.terrain[8][15].setCase(Contenu.Expression);
	    Terrain.terrain[8][15].setExpr(Operateur.PointVirgule);
	    Terrain.terrain[9][15].setCase(Contenu.Expression);
	    Terrain.terrain[9][15].setExpr(Operateur.Deuxpoints);

	    Terrain.terrain[4][16].setCase(Contenu.Expression);
	    Terrain.terrain[4][16].setExpr(Comportement.Attack);

	    Terrain.terrain[5][16].setCase(Contenu.Expression);
	    Terrain.terrain[5][16].setExpr(Operateur.Deuxpoints);
	    Terrain.terrain[6][16].setCase(Contenu.Expression);
	    Terrain.terrain[6][16].setExpr(Operateur.PointVirgule);
	    Terrain.terrain[7][16].setCase(Contenu.Expression);
	    Terrain.terrain[7][16].setExpr(Comportement.Kamikaze);
	    Terrain.terrain[8][16].setCase(Contenu.Expression);
	    Terrain.terrain[8][16].setExpr(Operateur.PointVirgule);
	    Terrain.terrain[9][16].setCase(Contenu.Expression);
	    Terrain.terrain[9][16].setExpr(Operateur.Deuxpoints);
	    
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
