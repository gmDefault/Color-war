package machine_a_glace;

import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+
 *         G- e+++ h+ r- y+
 */
public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private Image menuimg;
	private StateBasedGame game;
	private GameContainer container;
	public static boolean joueur_1_gagne = false;
	public static boolean joueur_2_gagne = false;
	public static boolean egalite = false;
	public static boolean aide = false;
	private int timer = 0;
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont uniFont, uniFont2;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		this.game = game;
		this.container = container;
		this.background = new Image("maps/colorwar.png");
		this.menuimg = new Image("maps/menuaide.png");

		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/cartoon.ttf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIFont1 = UIFont1.deriveFont(java.awt.Font.BOLD, 80.f);

		uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
		uniFont.addAsciiGlyphs();
		uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		uniFont.addAsciiGlyphs();
		uniFont.loadGlyphs();

		UIFont1 = UIFont1.deriveFont(java.awt.Font.BOLD, 60.f);

		uniFont2 = new org.newdawn.slick.UnicodeFont(UIFont1);
		uniFont2.addAsciiGlyphs();
		uniFont2.getEffects().add(new ColorEffect(java.awt.Color.white));
		uniFont2.addAsciiGlyphs();
		uniFont2.loadGlyphs();

	}

	/**
	 * Contenons nous d'afficher l'image de fond. .
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (this.aide == false) {
			background.draw(0, 0, container.getWidth(), container.getHeight());
			if (joueur_1_gagne) {

				uniFont.drawString(450 - 40, 200, "Le joueur rouge gagne la partie !", Color.pink);

			} else if (joueur_2_gagne) {
				uniFont.drawString(450 - 40, 200, "Le joueur bleu gagne la partie !", Color.cyan);
			} else if (egalite) {
				uniFont.drawString(780, 200, "EGALITE !", Color.orange);
			}
			if (timer < 25) {
				uniFont2.drawString(450 - 40, 350, "Appuyer sur \"Entree\" pour lancer la partie", Color.white);
				uniFont2.drawString(475 - 40, 450, "Appuyer sur \"C\" pour charger une partie", Color.white);
				uniFont2.drawString(420-40, 550, "Appuyer sur \"T\" pour lancer un entrainement", Color.white);
				
				uniFont2.drawString(480 - 40, 750, "Appuyer sur \"Q\" pour quitter le jeu", Color.white);
				uniFont2.drawString(440 - 40, 650, "Appuyer sur \"H\" pour lancer le menu d'aide", Color.white);
				timer++;
			} else {
				uniFont2.drawString(450 - 40, 350, "Appuyer sur \"Entree\" pour lancer la partie", Color.cyan);
				uniFont2.drawString(475 - 40, 450, "Appuyer sur \"C\" pour charger une partie", Color.cyan);
				uniFont2.drawString(420-40, 550, "Appuyer sur \"T\" pour lancer un entrainement", Color.cyan);
				
				uniFont2.drawString(480 - 40, 750, "Appuyer sur \"Q\" pour quitter le jeu", Color.cyan);
				uniFont2.drawString(440 - 40, 650, "Appuyer sur \"H\" pour lancer le menu d'aide", Color.cyan);
				timer++;
				if (timer == 50) {
					timer = 0;
				}
			}
		} else {
			menuimg.draw(0, 0, container.getWidth(), container.getHeight());
		}

		// g.drawString("Appuyer sur Entrée pour lancer la partie", 800, 300);
		// g.drawString("Appuyer sur C pour charger une partie", 815, 400);
		// g.drawString("Appuyer sur Q pour quitter le jeu", 830, 500);

		// musique.loop();

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	/**
	 * Passer à l'ecran de jeu à l'appui de n'importe quel touche.
	 */
	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			try {
				if (this.joueur_1_gagne || this.joueur_2_gagne || this.egalite) {
					Terrain.initialiser();
					this.container.reinit();
					MapGameState.j1 = new Joueur(1, 15, Couleur.Rouge, 50, 100);
					MapGameState.j1.setD(Direction.Sud);
					MapGameState.j2 = new Joueur(28, 15, Couleur.Bleu, 100, 100);
				}
				this.joueur_1_gagne = false;
				this.joueur_2_gagne = false;
				this.egalite = false;

			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.enterState(MapGameState.ID);
			break;
		case Input.KEY_Q:
			this.container.exit();
			break;
		case Input.KEY_H:
			if (this.aide == true) {
				this.aide = false;
			} else {
				this.aide = true;
			}
			break;
		case Input.KEY_C:

			try {
				if (this.joueur_1_gagne || this.joueur_2_gagne || this.egalite) {
					Terrain.initialiser();
					this.container.reinit();
					MapGameState.j1 = new Joueur(1, 15, Couleur.Rouge, 100, 100);
					MapGameState.j1.setD(Direction.Sud);
					MapGameState.j2 = new Joueur(28, 15, Couleur.Bleu, 100, 100);
				}
				this.joueur_1_gagne = false;
				this.joueur_2_gagne = false;
				this.egalite = false;

			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.enterState(MapGameState.ID);

			String s;
			s = Sauvegarde.Reader();
			String[] mots = s.split(" ");
			MapGameState.minute = Integer.parseInt(mots[0]);
			MapGameState.seconde = Integer.parseInt(mots[1]);
			break;

		case Input.KEY_T:
			try {
				Terrain.initialiser();
				this.container.reinit();
				
				TrainGameState.j1 = new Joueur(1, 15, Couleur.Rouge, 100, 100);
				TrainGameState.j1.setD(Direction.Sud);
				
				TrainGameState.allrobots.add(new Robot(26,16,Couleur.Bleu,new Node(Operateur.Star,null,Reader.read("{A>X}"))));
				TrainGameState.allrobots.get(0).setJoueur(TrainGameState.j2);
				TrainGameState.cmptr_robots.add(1);;
				TrainGameState.secsrobots.add(0);
				TrainGameState.canmoverobots.add(false);
				TrainGameState.automaterobot.add("{A>X}");
				
				TrainGameState.allrobots.add(new Robot(26,14,Couleur.Bleu,new Node(Operateur.Star,null,Reader.read("{K>X}"))));
				TrainGameState.allrobots.get(1).setJoueur(TrainGameState.j2);
				TrainGameState.cmptr_robots.add(1);;
				TrainGameState.secsrobots.add(0);
				TrainGameState.canmoverobots.add(false);
				TrainGameState.automaterobot.add("{K>X}");
				
				TrainGameState.allrobots.add(new Robot(25,15,Couleur.Bleu,new Node(Operateur.Star,null,Reader.read("{P}"))));
				TrainGameState.allrobots.get(2).setJoueur(TrainGameState.j2);
				TrainGameState.cmptr_robots.add(1);;
				TrainGameState.secsrobots.add(0);
				TrainGameState.canmoverobots.add(false);
				TrainGameState.automaterobot.add("{P}");
				
				this.joueur_1_gagne = false;
				this.joueur_2_gagne = false;
				this.egalite = false;

			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.enterState(TrainGameState.ID);

			break;
		}

	}

	/**
	 * L'identifiant permet d'identifier les différentes boucles, pour passer de
	 * l'une à l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
}