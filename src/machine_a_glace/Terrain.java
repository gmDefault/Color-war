package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class Terrain {
	private static int taille = 30;
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
						if (Math.random() < 0.20) {
							terrain[i][j].setCase(Contenu.Obstacle);
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
					System.out.print("J|");
				else
					System.out.print(c.toString() + "|");
			}
			System.out.println();
			for (int j = 0; j < taille; j++) {
				System.out.print("----");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		Terrain.initialiser();
		Terrain.afficher();

		Entite e = new Entite(4, 4);

		while (true) {
			count++;
			if (e.next_case().isAccessible()) {
				e.Avancer(1);
			} else {
				if (Math.random() < 0.5) {
					switch (e.direction()) {
					case Nord:
						e.Tourner(Direction.Est);
						break;
					case Est:
						e.Tourner(Direction.Sud);
						break;
					case Sud:
						e.Tourner(Direction.Ouest);
						break;
					case Ouest:
						e.Tourner(Direction.Nord);
						break;
					}
				} else {
					switch (e.direction()) {
					case Nord:
						e.Tourner(Direction.Ouest);
						break;
					case Est:
						e.Tourner(Direction.Nord);
						break;
					case Sud:
						e.Tourner(Direction.Est);
						break;
					case Ouest:
						e.Tourner(Direction.Sud);
						break;
					}
				}
			}
			Terrain.afficher();
			TimeUnit.SECONDS.sleep(1);

		}
	}
}
