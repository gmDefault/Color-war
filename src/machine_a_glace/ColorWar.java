package machine_a_glace;

import java.util.concurrent.TimeUnit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ColorWar {

	public static void main(String[] args) throws SlickException {
		Terrain.initialiser();
		Joueur j1 = new Joueur(4, 15, Couleur.Rouge);
		Joueur j2 = new Joueur(28, 15, Couleur.Bleu);

		

		View.launch_game(j1, j2);
	}
}