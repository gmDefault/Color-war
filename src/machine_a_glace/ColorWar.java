package machine_a_glace;

import java.util.concurrent.TimeUnit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ColorWar {

	public static void main(String[] args) throws SlickException {
		Terrain.initialiser();
		Joueur j1 = new Joueur(15, 1, Couleur.Rouge);
		Joueur j2 = new Joueur(15, 28, Couleur.Bleu);

		

		View.launch_game(j1, j2);
	}
}