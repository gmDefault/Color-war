package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class ColorWar {

	public static void main(String[] args) throws InterruptedException {
		Terrain.initialiser();
		Terrain.afficher();

		String expr = "{ K; P}";
		Node a;

		Joueur j = new Joueur(4, 4, Couleur.Bleu);
		// Robot r = new Robot(3, 3, Couleur.Rouge);

		// while (true) {
		// if (j.next_case().isAccessible()) {
		// j.Avancer(1);
		// } else {
		// if (Math.random() < 0.5) {
		// switch (j.direction()) {
		// case Nord:
		// j.Tourner(Direction.Est);
		// break;
		// case Est:
		// j.Tourner(Direction.Sud);
		// break;
		// case Sud:
		// j.Tourner(Direction.Ouest);
		// break;
		// case Ouest:
		// j.Tourner(Direction.Nord);
		// break;
		// }
		// } else {
		// switch (j.direction()) {
		// case Nord:
		// j.Tourner(Direction.Ouest);
		// break;
		// case Est:
		// j.Tourner(Direction.Nord);
		// break;
		// case Sud:
		// j.Tourner(Direction.Est);
		// break;
		// case Ouest:
		// j.Tourner(Direction.Sud);
		// break;
		// }
		// }
		// }
		//
		// if (r.next_case().isAccessible()) {
		// r.Avancer(1);
		// } else {
		// if (Math.random() < 0.5) {
		// switch (r.direction()) {
		// case Nord:
		// r.Tourner(Direction.Est);
		// break;
		// case Est:
		// r.Tourner(Direction.Sud);
		// break;
		// case Sud:
		// r.Tourner(Direction.Ouest);
		// break;
		// case Ouest:
		// r.Tourner(Direction.Nord);
		// break;
		// }
		// } else {
		// switch (r.direction()) {
		// case Nord:
		// r.Tourner(Direction.Ouest);
		// break;
		// case Est:
		// r.Tourner(Direction.Nord);
		// break;
		// case Sud:
		// r.Tourner(Direction.Est);
		// break;
		// case Ouest:
		// r.Tourner(Direction.Sud);
		// break;
		// }
		// }
		// }
		// Terrain.afficher();
		// j.afficher_inventaire();
		// r.afficher_inventaire();
		// TimeUnit.SECONDS.sleep(1);
		//
		// }
		try{
			a = Reader.read(expr);

			System.out.println(a.toString());
			a = new Node(Operateur.Star, null, a);
			Automate auto = new Automate(a);
			System.out.println("block");
		}catch(Exception e){
			System.out.println("Syntaxe Incorrect");
		}
		
		
	}
}