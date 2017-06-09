package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class ColorWar {

	public static void main(String[] args) throws InterruptedException {
		Terrain.initialiser();
		Terrain.afficher();

		String expr = "{ K; P}";
		Node a;

		Joueur j1 = new Joueur(15,1,Couleur.Rouge);
		Joueur j2 = new Joueur(15,28,Couleur.Bleu);

		    	
		    	j1.setD(Direction.Sud);
		Robot r = new Robot(3, 3, Couleur.Rouge,null);
		int[] borne = new int[4];
		borne = r.BorneDistance(1, 1, 3);
		for(int i = 0; i<4; i++){
			System.out.println(borne[i]);
		}
		System.out.println();
		
		View.launch_game(j1, j2);
//		while (true) {
//			if (j.next_case().isAccessible()) {
//				j.Avancer(1);
//			} else {
//				if (Math.random() < 0.5) {
//					switch (j.direction()) {
//					case Nord:
//						j.Tourner(Direction.Est);
//						break;
//					case Est:
//						j.Tourner(Direction.Sud);
//						break;
//					case Sud:
//						j.Tourner(Direction.Ouest);
//						break;
//					case Ouest:
//						j.Tourner(Direction.Nord);
//						break;
//					}
//				} else {
//					switch (j.direction()) {
//					case Nord:
//						j.Tourner(Direction.Ouest);
//						break;
//					case Est:
//						j.Tourner(Direction.Nord);
//						break;
//					case Sud:
//						j.Tourner(Direction.Est);
//						break;
//					case Ouest:
//						j.Tourner(Direction.Sud);
//						break;
//					}
//				}
//			}
//
//			if (r.next_case().isAccessible()) {
//				r.Avancer(1);
//			} else {
//				if (Math.random() < 0.5) {
//					switch (r.direction()) {
//					case Nord:
//						r.Tourner(Direction.Est);
//						break;
//					case Est:
//						r.Tourner(Direction.Sud);
//						break;
//					case Sud:
//						r.Tourner(Direction.Ouest);
//						break;
//					case Ouest:
//						r.Tourner(Direction.Nord);
//						break;
//					}
//				} else {
//					switch (r.direction()) {
//					case Nord:
//						r.Tourner(Direction.Ouest);
//						break;
//					case Est:
//						r.Tourner(Direction.Nord);
//						break;
//					case Sud:
//						r.Tourner(Direction.Est);
//						break;
//					case Ouest:
//						r.Tourner(Direction.Sud);
//						break;
//					}
//				}
//			}
//			Terrain.afficher();
//			j.afficher_inventaire();
//			r.afficher_inventaire();
//			TimeUnit.SECONDS.sleep(1);
//
//		}
//		a = Reader.read(expr);
//		System.out.println(a.toString());
	}
}