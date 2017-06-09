package machine_a_glace;

import java.util.concurrent.TimeUnit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ColorWar {

	public static void main(String[] args) throws SlickException {

		Joueur j1 = new Joueur(15, 1, Couleur.Rouge);
		Joueur j2 = new Joueur(15, 28, Couleur.Bleu);

		j1.setD(Direction.Sud);
		Robot r = new Robot(3, 3, Couleur.Rouge, null);
		int[] borne = new int[4];
		borne = r.BorneDistance(1, 1, 3);
		for (int i = 0; i < 4; i++) {
			System.out.println(borne[i]);
		}
		System.out.println();

		View.launch_game(j1, j2);
	}
}