package machine_a_glace;

import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private StateBasedGame game;
	private GameContainer container;
	public static boolean joueur_1_gagne = false;
	public static boolean joueur_2_gagne = false;
	
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont uniFont;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		this.game = game;
		this.container = container;
		this.background = new Image("maps/2107.jpg");
		
		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/reveil.ttf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 60.f);

		uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
		uniFont.addAsciiGlyphs();
		uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		uniFont.addAsciiGlyphs();
		uniFont.loadGlyphs();
		

	}

	/**
	 * Contenons nous d'afficher l'image de fond. .
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		if (joueur_1_gagne) {
			
			uniFont.drawString(580, 200, "Le joueur rouge GAGNE la partie !", Color.red);

		} else if (joueur_2_gagne) {
			uniFont.drawString(580, 200, "Le joueur bleu GAGNE la partie !", Color.cyan);
		}
		g.drawString("Appuyer sur Entrée pour lancer la partie", 800, 300);
		g.drawString("Appuyer sur C pour charger une partie", 815, 400);
		g.drawString("Appuyer sur Q pour quitter le jeu", 830, 500);



	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	/**
	 * Passer à l'ecran de jeu à l'appui de n'importe quel touche.
	 */
	@Override
	public void keyReleased(int key, char c) {
		switch(key){
		case Input.KEY_ENTER :
			try {
				this.joueur_1_gagne = false;
				this.joueur_2_gagne = false;
				container.reinit();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.enterState(MapGameState.ID);
			break;
		default : 
			this.container.exit();

		}
			
	}

	/**
	 * L'identifiant permet d'identifier les différentes boucles, pour passer de l'une à l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
}