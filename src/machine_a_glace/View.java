package machine_a_glace;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;

public class View extends BasicGame {
	private GameContainer container;
	private TiledMap map;

	int seconde = 35000;

	int minute = 3;

	int cmptr_robot = 1;

	boolean bool1 = false;
	boolean bool2 = false;
	boolean bool3 = false;
	int popup_test_1 = 0;
	int popup_test_2 = 0;
	private int nrj;
	private float x = 976, y = 32 + 16;
	private float PourcentRouge = 0;
	private float PourcentBleu = 0;
	private float xx = 976, yy = 960 - 32 - 16;
	private int direction = 2;
	private int direction2 = 0;
	private boolean moving = false;
	private long lasttime = System.currentTimeMillis();
	private long lasttime2 = System.currentTimeMillis();
	private long lasttime3 = System.currentTimeMillis();

	private int secs1 = 0;
	private int secs2 = 0;
	private int secsrobot = 0;

	private final float DEBUT_VIE_ROUGE_X = 1631;
	private final float FIN_VIE_ROUGE_X = 1778;
	private final float VIE_Y = 122;

	private final float NRJ_Y = 145;
	private final float DEBUT_VIE_BLEU_X = 130;
	private final float FIN_VIE_BLEU_X = 277;

	// private final float DEBUT_NRJ_ROUGE_X = 1631;
	// private final float FIN_NRJ_ROUGE_X = 1778;
	//
	//
	// private final float DEBUT_NRJ_BLEU_X = 130;
	// private final float FIN_NRJ_BLEU_X = 277;

	private boolean canmove = false;
	private boolean canmove2 = false;
	private boolean canmoverobot = false;

	private Joueur j1, j2;
	private Robot r1;

	public static boolean recolorie_par_dessus = false;

	private boolean moving2 = false;
	private Animation[] animations = new Animation[8];
	private Animation[] animations2 = new Animation[8];
	private Animation[] animations3 = new Animation[8];
	private Animation[] animations4 = new Animation[8];

	private String item[] = { "A", "P", "K" };
	private String item2[] = { "Robot1", "Robot2", "Robot3" };
	private JComboBox robot = new JComboBox(item);
	private JComboBox robot2 = new JComboBox(item2);
	private ImageIcon ic = new ImageIcon("maps/robot.png");
	private ImageIcon icr = new ImageIcon("maps/tete_robot_rouge.png");
	private ImageIcon icb = new ImageIcon("maps/tete_robot_bleu.png");
	private Dimension d = new Dimension(100, 100);
	private String tab[] = { "Frapper", "Explorer", "Kamikaze", ";", "*", ">" };
	private String tab2[] = { "Manger", "Fumer", "Rond-Poing" };

	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont uniFont;

	java.awt.Font UIFont2;
	org.newdawn.slick.UnicodeFont uniFont2;

	public View(Joueur j1, Joueur j2) {

		super("ColorWar");
		this.j1 = j1;
		j1.setD(Direction.Sud);
		this.j2 = j2;
		// TODO Auto-generated constructor stub
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.container = arg0;
		this.map = new TiledMap("maps/map/map1.tmx");
		container.setShowFPS(false);

		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/reveil.ttf"));
			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 60.f);

			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
			uniFont.addAsciiGlyphs();
			uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
			uniFont.addAsciiGlyphs();
			uniFont.loadGlyphs();

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			UIFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/Font1.ttf"));
			UIFont2 = UIFont2.deriveFont(java.awt.Font.PLAIN, 50.f);

			uniFont2 = new org.newdawn.slick.UnicodeFont(UIFont2);
			uniFont2.addAsciiGlyphs();
			uniFont2.getEffects().add(new ColorEffect(java.awt.Color.white));
			uniFont2.addAsciiGlyphs();
			uniFont2.loadGlyphs();

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SpriteSheet spriteSheet = new SpriteSheet("maps/char_2.png", 64, 64);
		SpriteSheet spriteSheet2 = new SpriteSheet("maps/char_1.png", 64, 64);
		SpriteSheet spriteSheet3 = new SpriteSheet("maps/robot_red.png", 64, 64);
		SpriteSheet spriteSheet4 = new SpriteSheet("maps/bonus.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);

		this.animations2[0] = loadAnimation(spriteSheet2, 0, 1, 0);
		this.animations2[1] = loadAnimation(spriteSheet2, 0, 1, 1);
		this.animations2[2] = loadAnimation(spriteSheet2, 0, 1, 2);
		this.animations2[3] = loadAnimation(spriteSheet2, 0, 1, 3);
		this.animations2[4] = loadAnimation(spriteSheet2, 1, 9, 0);
		this.animations2[5] = loadAnimation(spriteSheet2, 1, 9, 1);
		this.animations2[6] = loadAnimation(spriteSheet2, 1, 9, 2);
		this.animations2[7] = loadAnimation(spriteSheet2, 1, 9, 3);

		this.animations3[0] = loadAnimation(spriteSheet3, 0, 1, 0);
		this.animations3[1] = loadAnimation(spriteSheet3, 0, 1, 1);
		this.animations3[2] = loadAnimation(spriteSheet3, 0, 1, 2);
		this.animations3[3] = loadAnimation(spriteSheet3, 0, 1, 3);
		this.animations3[4] = loadAnimation(spriteSheet3, 1, 9, 0);
		this.animations3[5] = loadAnimation(spriteSheet3, 1, 9, 1);
		this.animations3[6] = loadAnimation(spriteSheet3, 1, 9, 2);
		this.animations3[7] = loadAnimation(spriteSheet3, 1, 9, 3);

		this.animations4[0] = loadAnimation(spriteSheet4, 0, 1, 0);
		this.animations4[1] = loadAnimation(spriteSheet4, 0, 1, 1);
		this.animations4[2] = loadAnimation(spriteSheet4, 0, 1, 2);
		this.animations4[3] = loadAnimation(spriteSheet4, 0, 1, 3);
		this.animations4[4] = loadAnimation(spriteSheet4, 1, 9, 0);
		this.animations4[5] = loadAnimation(spriteSheet4, 1, 9, 1);
		this.animations4[6] = loadAnimation(spriteSheet4, 1, 9, 2);
		this.animations4[7] = loadAnimation(spriteSheet4, 1, 9, 3);

		robot.setSize(100, 100);
		robot2.setSize(100, 100);

		Node test = Reader.read("{K > X}");
		test = new Node(Operateur.Star, null, test);
		r1 = new Robot(5, 15, Couleur.Bleu, test);
		r1.setJoueur(j2);

		int tileW = this.map.getTileWidth();
		int tileH = this.map.getTileHeight();
		int logicLayer = this.map.getLayerIndex("Collision");
		Image tile;

		// for (int i = 15*32; i < )
		for (int i = 16 * 32; i <= 56 * 32; i += 32) {
			for (int j = 32; j <= 28 * 32; j += 32) {
				tile = this.map.getTileImage((int) i / tileW, (int) j / tileH, logicLayer);
				if (tile != null) {
					Terrain.terrain[j / tileW][i / tileH - 15].setCase(Contenu.Obstacle);
				}
			}
		}

		// Terrain.afficher();

		// System.out.println("get line : "+j1.getLine());

		// this.e = new Entite(15, 20);
		// this.e2 = new Entite(16, 19);

		// Music background = new Music("maps/FoxieEpic.OGG");
		// background.loop();

	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		Image peinture_rouge = new Image("maps/peinture_rouge.png");
		Image peinture_bleu = new Image("maps/peinture_bleu.png");
		Image hud_bleu = new Image("maps/hud_bleu.png");
		Image hud_rouge = new Image("maps/hud_rouge.png");

		Image inventaire = new Image("maps/inventaire.png");
		Image robots_inv = new Image("maps/robots_inv.png");
		Image robots_inv2 = new Image("maps/robots_inv2.png");


		
		this.map.render(0, 0);
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				if (Terrain.terrain[i][j].getCouleur() == Couleur.Bleu) {
					peinture_bleu.drawCentered((15 * 32 + j * 32 + 16), (i * 32 + 16));
				} else if (Terrain.terrain[i][j].getCouleur() == Couleur.Rouge) {
					peinture_rouge.drawCentered((15 * 32 + j * 32 + 16), (i * 32 + 16));
				}
			}
		}

		afficher_expr();
		
		hud_bleu.draw(15, 15);
		hud_rouge.draw(1920 - 300, 15);

		afficher_pdv(j1);
		afficher_pdv(j2);

		afficher_nrj(j1);
		afficher_nrj(j2);

		inventaire.draw(100, 195);
		inventaire.draw(1560, 195);
		afficher_inventaire(j1);
		afficher_inventaire(j2);
		
//		System.out.println(r1.etat_courant());

		robots_inv.draw(120, 670);
		robots_inv2.draw(1580, 670);

		// for (int i = 0; i < this.pos_color.size(); i++) {
		// peinture_rouge.drawCentered(this.pos_color.get(i).getX(),
		// this.pos_color.get(i).getY());
		// }
		//
		// for (int i = 0; i < this.pos_color_2.size(); i++) {
		// peinture_bleu.drawCentered(this.pos_color_2.get(i).getX(),
		// this.pos_color_2.get(i).getY());
		// }

		// peinture_rouge.draw(15*32+e.getLine()*32, e.getCol()*32);

		// if(e.next_case().isAccessible()){
		// e.Avancer(1);
		//
		// }else{
		// if(Math.random()<0.5){
		// switch (e.direction()){
		// case Nord:e.Tourner(Direction.Est);
		// break;
		// case Est:e.Tourner(Direction.Sud);
		// break;
		// case Sud:e.Tourner(Direction.Ouest);
		// break;
		// case Ouest:e.Tourner(Direction.Nord);
		// break;
		// }
		// }else{
		// switch (e.direction()){
		// case Nord:e.Tourner(Direction.Ouest);
		// break;
		// case Est:e.Tourner(Direction.Nord);
		// break;
		// case Sud:e.Tourner(Direction.Est);
		// break;
		// case Ouest:e.Tourner(Direction.Sud);
		// break;
		// }
		// }
		// }
		//
		//
		//
		//
		// //peinture_bleu.draw(15*32+e2.getLine()*32, e2.getCol()*32);
		//
		//
		// if(e2.next_case().isAccessible()){
		// e2.Avancer(1);
		// }else{
		// if(Math.random()<0.5){
		// switch (e2.direction()){
		// case Nord:e2.Tourner(Direction.Est);
		// break;
		// case Est:e2.Tourner(Direction.Sud);
		// break;
		// case Sud:e2.Tourner(Direction.Ouest);
		// break;
		// case Ouest:e2.Tourner(Direction.Nord);
		// break;
		// }
		// }else{
		// switch (e2.direction()){
		// case Nord:e2.Tourner(Direction.Ouest);
		// break;
		// case Est:e2.Tourner(Direction.Nord);
		// break;
		// case Sud:e2.Tourner(Direction.Est);
		// break;
		// case Ouest:e2.Tourner(Direction.Sud);
		// break;
		// }
		// }
		// }
		//
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// System.out.println(e.toString());

		g.drawAnimation(animations[j1.getD().entier() + (moving ? 4 : 0)], (15 * 32 + this.j1.getCol() * 32 + 16) - 32,
				(this.j1.getLine() * 32 + 16) - 60);
		g.drawAnimation(animations2[j2.getD().entier() + (moving2 ? 4 : 0)],
				(15 * 32 + this.j2.getCol() * 32 + 16) - 32, (this.j2.getLine() * 32 + 16) - 60);

		g.drawAnimation(animations3[r1.getD().entier() + (true ? 4 : 0)], (15 * 32 + r1.getCol() * 32 + 16) - 32,
				(this.r1.getLine() * 32 + 16) - 60);

		g.drawAnimation(animations4[0 + (true ? 4 : 0)], (15 * 32 + 15 * 32 + 16) - 32, (5 * 32 + 16) - 60);

		if (this.popup_test_1 == 25) {

			this.popup_test_1 = 0;
			if (Terrain.terrain[this.j1.getLine()][this.j1.getCol()].isCreer() && j1.isNrj()) {
				Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCase(Contenu.Joueur);
				Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCouleur(Couleur.Neutre);
				j1.SetNrj(j1.getNrj() - 75);
				this.container.pause();

				int t = 0;
				while (t < 5) {
					JOptionPane r = new JOptionPane();
					r.setSize(d);
					String[] bouton = { "Créer", "Modifier", "Annuler" };
					int retour = r.showOptionDialog(null, "Faite votre choix", "Menu des robots",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, this.icr, bouton, bouton[0]);
					if (retour == 1) {
						String inputrm = JOptionPane.showInputDialog(robot);

						if (inputrm == null) {
							int k = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la création/modification",
									null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
							if (k == 1) {
								t = 5;
							}
						}
					}
					if (retour == 0) {
						JOptionPane p = new JOptionPane();
						String inputrc = p.showInputDialog(tab, "Saisissez votre expression");
						// System.out.println(inputrc);

						if (inputrc == null) {
							int k = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la création/modification",
									null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
							if (k == 1) {
								t = 5;
							}
						}
					}
					if (retour == 2 || retour == -1) {
						t = 5;
					}
					t++;
				}

			}
		} else if (Terrain.terrain[this.j1.getLine()][this.j1.getCol()].isCreer() && !j1.isNrj()) {
			Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCouleur(Couleur.Neutre);

			if (bool1) {
				bool1 = false;
				this.j1.setNb_cases_coloriees(this.j1.getNb_cases_coloriees() - 1);
			}

		} else {
			this.popup_test_1++;

		}

		if (this.popup_test_2 == 25) {
			this.popup_test_2 = 0;
			if (Terrain.terrain[this.j2.getLine()][this.j2.getCol()].isCreer() && j2.isNrj()) {
				Terrain.terrain[this.j2.getLine()][this.j2.getCol()].setCase(Contenu.Joueur);
				Terrain.terrain[this.j2.getLine()][this.j2.getCol()].setCouleur(Couleur.Neutre);

				j2.SetNrj(j2.getNrj() - 75);
				this.container.pause();
				int t2 = 0;
				while (t2 < 5) {
					JOptionPane r2 = new JOptionPane();
					r2.setSize(d);
					String[] bouton2 = { "Créer", "Modifier", "Annuler" };
					int retour2 = r2.showOptionDialog(null, "Faite votre choix", "Menu des robots",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.icb, bouton2, bouton2[0]);
					if (retour2 == 1) {
						String inputbm = JOptionPane.showInputDialog(robot2);

						if (inputbm == null) {
							int k2 = JOptionPane.showOptionDialog(null,
									"Voulez-vous continuer la création/modification", null, JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							if (k2 == 1) {
								t2 = 5;
							}
						}
					}
					if (retour2 == 0) {
						JOptionPane rbc = new JOptionPane();
						String inputbc = rbc.showInputDialog(tab2, "Saisissez votre expression");

						if (inputbc == null) {
							int k2 = JOptionPane.showOptionDialog(null,
									"Voulez-vous continuer la création/modification", null, JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							if (k2 == 1) {
								t2 = 5;
							}
						}
					}

					if (retour2 == 2 || retour2 == -1) {
						t2 = 5;
					}
					t2++;
				}
			}
		} else if (Terrain.terrain[this.j2.getLine()][this.j2.getCol()].isCreer() && !j2.isNrj()) {
			Terrain.terrain[this.j2.getLine()][this.j2.getCol()].setCouleur(Couleur.Neutre);
			if (bool2) {
				bool2 = false;
				this.j2.setNb_cases_coloriees(this.j2.getNb_cases_coloriees() - 1);
			}
		
		} else {
			this.popup_test_2++;
			
		}
		// System.out.println("( "+ x + " , " + y + " ) ");
		// g.drawString(minute + " m " + seconde / 1000 + " s", 945, 470);

		g.setColor(new Color(255, 255, 255));
		uniFont2.drawString(290, 10, (int) (PourcentBleu * 100) + " % ", Color.blue);
		g.drawString("" + j2.getPdv(), 190, 120);
		g.drawString("" + j1.getPdv(), 1700, 120);
		g.drawString(" Inventaire ", 187, 170);
		g.drawString(" Inventaire ", 1617, 170);

		g.drawString(" Etat des robots ", 140, 650);
		g.drawString(" Etat des robots  ", 1617, 650);

		uniFont2.drawString(1472, 10, (int) (PourcentRouge * 100) + " % ", Color.red);
		if (minute < 10) {
			if (seconde / 1000 < 30) {
				if (minute == 0 && seconde / 1000 >= 10) {
					uniFont.drawString(921, 440, "0" + minute + ":" + seconde / 1000, Color.red);
				} else if (minute == 0 && seconde / 1000 < 10)
					uniFont.drawString(921, 440, "0" + minute + ":0" + seconde / 1000, Color.red);
				else {
					if (seconde / 1000 >= 10)
						uniFont.drawString(921, 440, "0" + minute + ":" + seconde / 1000, Color.darkGray);
					else
						uniFont.drawString(921, 440, "0" + minute + ":0" + seconde / 1000, Color.darkGray);
				}
			} else
				uniFont.drawString(921, 440, "0" + minute + ":" + seconde / 1000, Color.darkGray);
		} else
			uniFont.drawString(921, 440, minute + ":" + seconde / 1000, Color.darkGray);

	}

	// @Override
	// public void render(GameContainer container, Graphics g) throws
	// SlickException {
	// this.map.render(0, 0);
	// }

	// private boolean isCollision(float x, float y) {
	// int tileW = this.map.getTileWidth();
	// int tileH = this.map.getTileHeight();
	// int logicLayer = this.map.getLayerIndex("Collision");
	// Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH,
	// logicLayer);
	// boolean collision = tile != null;
	// if (collision) {
	// Color color = tile.getColor((int) x % tileW, (int) y % tileH);
	// collision = color.getAlpha() > 0;
	// }
	// return collision;
	// }

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {

		if (Terrain.Index > 0)
			bool3 = Terrain.ReduceTimer();
			if(bool3){
				afficher_expr();
		}
		
		if ((j1.getNombre_Case_Coloriees() + j2.getNombre_Case_Coloriees()) != 0) {
			PourcentBleu = (float) j2.getNombre_Case_Coloriees()
					/ ((float) j1.getNombre_Case_Coloriees() + (float) j2.getNombre_Case_Coloriees());
			PourcentRouge = 1 - PourcentBleu;
		}

		else {
			PourcentBleu = 0;
			PourcentRouge = 0;
		}


		if ((int) (seconde) % 30 == 0) {
			if (j1.getNrj() < 100)
				j1.SetNrj(j1.getNrj() + 1);
			if (j2.getNrj() < 100)

				j2.SetNrj(j2.getNrj() + 1);
		}

		if (seconde > -delta && seconde < delta) {
			seconde = 60000;
			if (minute == 0) {
				this.container.exit();
			} else
				minute--;
		}
		seconde -= delta;
		this.secs1 += delta;
		this.secs2 += delta;
		secsrobot += delta;
		this.container.resume();

		if (this.moving) {
			// switch (this.j1.getD()) {
			// case Nord:
			if (canmove) {
				// if (!isCollision(15*32+this.j1.getCol()*32+16,
				// this.j1.getLine()*32+16 - (1024/32))) {
				// this.j1.setD(Direction.Ouest);
				// this.y -= (1024/32);
				this.j1.Avancer(1);
				Terrain.Initialiser_cases_creer();

				if (this.recolorie_par_dessus == true) {
					this.j2.setNb_cases_coloriees(this.j2.getNb_cases_coloriees() - 1);
					this.recolorie_par_dessus = false;
				}
				// this.container.pause();
				// this.container.setPaused(true);
				// arg0.pause();

				// this.container.resume();

				// this.container.resume();
				// arg0.resume();

				// System.out.println("passe ici");
				// Terrain.afficher();

				// this.container.getGraphics().drawImage(grass_b, x, y);
				// arg0.getGraphics().drawImage(grass_b, x, y);
				// grass_b.draw(x, y);

				// }

			}
			// System.out.println("pos : " + x + " - " + y);
			if (this.secs1 < 500) {
				canmove = false;
			} else {
				this.secs1 = 0;
				canmove = true;
			}
		}
		// break;
		// case Ouest:
		// if (canmove) {
		// if (!isCollision(this.x - (1024/32), this.y))
		// this.x -= (1024/32);
		// Point p = new Point(this.x, this.y);
		//
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		// this.pos_color.add(p);
		//
		//
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		//
		// }
		// //System.out.println("pos : " + x + " - " + y);
		//
		// if (System.currentTimeMillis()-lasttime<500) {
		// canmove = false;
		// } else {
		// lasttime = System.currentTimeMillis();
		// canmove = true;
		// }
		// break;
		// case Sud:
		// if (canmove) {
		// if (!isCollision(this.x, this.y + (1024/32)))
		// this.y += (1024/32);
		// Point p = new Point(this.x, this.y);
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		// this.pos_color.add(p);
		//
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		// }
		// //System.out.println("pos : " + x + " - " + y);
		//
		// if (System.currentTimeMillis()-lasttime<500) {
		// canmove = false;
		// } else {
		// lasttime = System.currentTimeMillis();
		// canmove = true;
		// }
		// break;
		// case Est:
		// if (canmove) {
		// if (!isCollision(this.x + (1024/32), this.y))
		// this.x += (1024/32);
		//
		// Point p = new Point(this.x, this.y);
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		// this.pos_color.add(p);
		//
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		// }
		// //System.out.println("pos : " + x + " - " + y);
		//
		// if (System.currentTimeMillis()-lasttime<500) {
		// canmove = false;
		// } else {
		// lasttime = System.currentTimeMillis();
		// canmove = true;
		// }
		// break;
		//
		// }
		// }
		if (this.moving2) {
			// switch (this.j1.getD()) {
			// case Nord:
			if (canmove2) {
				// if (!isCollision(15*32+this.j1.getCol()*32+16,
				// this.j1.getLine()*32+16 - (1024/32))) {
				// this.j1.setD(Direction.Ouest);
				// this.y -= (1024/32);
				this.j2.Avancer(1);
				Terrain.Initialiser_cases_creer();

				if (this.recolorie_par_dessus == true) {
					this.j1.setNb_cases_coloriees(this.j1.getNb_cases_coloriees() - 1);
					this.recolorie_par_dessus = false;
				}

				// System.out.println("passe ici");
				// Terrain.afficher();

				// this.container.getGraphics().drawImage(grass_b, x, y);
				// arg0.getGraphics().drawImage(grass_b, x, y);
				// grass_b.draw(x, y);

				// }

			}
			// System.out.println("pos : " + x + " - " + y);
			if (this.secs2 < 500) {
				canmove2 = false;
			} else {
				this.secs2 = 0;
				canmove2 = true;
			}
		}
		// case 1:
		// if (canmove2) {
		// if (!isCollision(this.xx - (1024/32), this.yy))
		// this.xx -= (1024/32);
		// Point p = new Point(this.xx, this.yy);
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		//
		// this.pos_color_2.add(p);
		//
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		// }
		// if (System.currentTimeMillis()-lasttime2<500) {
		// canmove2 = false;
		// } else {
		// lasttime2 = System.currentTimeMillis();
		// canmove2 = true;
		// }
		// break;
		// case 2:
		// if (canmove2) {
		// if (!isCollision(this.xx, this.yy + (1024/32)))
		// this.yy += (1024/32);
		// Point p = new Point(this.xx, this.yy);
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		//
		// this.pos_color_2.add(p);
		//
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		// }
		// if (System.currentTimeMillis()-lasttime2<500) {
		// canmove2 = false;
		// } else {
		// lasttime2 = System.currentTimeMillis();
		// canmove2 = true;
		// }
		// break;
		// case 3:
		// if (canmove2) {
		// if (!isCollision(this.xx + (1024/32), this.yy))
		// this.xx += (1024/32);
		// Point p = new Point(this.xx, this.yy);
		// for(int i = 0; i<this.pos_color_2.size(); i++){
		// if (this.pos_color_2.get(i).equals(p)) {
		// this.pos_color_2.remove(this.pos_color_2.get(i));
		// }
		// }
		//
		// this.pos_color_2.add(p);
		//
		// for(int i = 0; i<this.pos_color.size(); i++){
		// if (this.pos_color.get(i).equals(p)) {
		// this.pos_color.remove(this.pos_color.get(i));
		// }
		// }
		//
		//
		// }
		// if (System.currentTimeMillis()-lasttime2<500) {
		// canmove2 = false;
		// } else {
		// lasttime2 = System.currentTimeMillis();
		// canmove2 = true;
		// }
		// break;
		//
		// }
		// }

		if (canmoverobot)
			r1.execute();
		
		if(secsrobot > 5000){
			r1.next_etat();
			System.out.println("CHANGEMENT " + secsrobot +" " +r1.etat_courant());
			secsrobot=0;
			cmptr_robot=1;
			
		}else if(secsrobot > 500*cmptr_robot){
			canmoverobot= true;
			cmptr_robot++;
		}else{
			canmoverobot = false;
		}
		
	
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		// System.out.println(key + "- " + c);
		switch (key) {
		case Input.KEY_UP:

			this.moving = false;
			break;
		case Input.KEY_LEFT:
			this.moving = false;

			break;
		case Input.KEY_DOWN:
			this.moving = false;

			break;
		case Input.KEY_RIGHT:
			this.moving = false;

			break;
		case Input.KEY_Z:
			this.moving2 = false;

			break;
		case Input.KEY_Q:
			this.moving2 = false;

			break;
		case Input.KEY_S:
			this.moving2 = false;

			break;
		case Input.KEY_D:
			this.moving2 = false;

			break;
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		// case Input.KEY_UP: this.direction = 0; this.moving = true; break;
		// case Input.KEY_LEFT: this.direction = 1; this.moving = true; break;
		// case Input.KEY_DOWN: this.direction = 2; this.moving = true; break;
		// case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;

		case Input.KEY_UP:
			j1.setD(Direction.Nord);
			this.direction = 0;
			this.moving = true;
			break;
		case Input.KEY_LEFT:
			j1.setD(Direction.Ouest);
			this.direction = 1;
			this.moving = true;
			break;
		case Input.KEY_DOWN:
			j1.setD(Direction.Sud);
			this.direction = 2;
			this.moving = true;
			break;
		case Input.KEY_RIGHT:

			j1.setD(Direction.Est);
			this.direction = 3;
			this.moving = true;
			break;

		// case Input.KEY_R:
		// int t = 0;
		// while (t < 5) {
		// JOptionPane r = new JOptionPane();
		// r.setSize(d);
		// String[] bouton = { "Créer", "Modifier" };
		// int retour = r.showOptionDialog(null, "Faite votre choix", "Menu des
		// robots",
		// JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		// this.icr, bouton, bouton[0]);
		// if (retour == 1) {
		// String inputrm = JOptionPane.showInputDialog(robot);
		//
		// if (inputrm == null) {
		// int k = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la
		// création/modification",
		// null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		// null, null);
		// if (k == 1) {
		// t = 5;
		// }
		// }
		// }
		// if (retour == 0) {
		// JOptionPane p = new JOptionPane();
		// String inputrc = p.showInputDialog(tab, "Saisissez votre
		// expression");
		// // System.out.println(inputrc);
		//
		// if (inputrc == null) {
		// int k = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la
		// création/modification",
		// null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		// null, null);
		// if (k == 1) {
		// t = 5;
		// }
		// }
		// }
		// t++;
		// }
		// break;
		//
		// case Input.KEY_T:
		//
		// int t2 = 0;
		// while (t2 < 5) {
		// JOptionPane r2 = new JOptionPane();
		// r2.setSize(d);
		// String[] bouton2 = { "Créer", "Modifier" };
		// int retour2 = r2.showOptionDialog(null, "Faite votre choix", "Menu
		// des robots",
		// JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.icb,
		// bouton2, bouton2[0]);
		// if (retour2 == 1) {
		// String inputbm = JOptionPane.showInputDialog(robot2);
		//
		// if (inputbm == null) {
		// int k2 = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la
		// création/modification",
		// null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		// null, null);
		// if (k2 == 1) {
		// t2 = 5;
		// }
		// }
		// }
		// if (retour2 == 0) {
		// JOptionPane rbc = new JOptionPane();
		// String inputbc = rbc.showInputDialog(tab2, "Saisissez votre
		// expression");
		//
		// if (inputbc == null) {
		// int k2 = JOptionPane.showOptionDialog(null, "Voulez-vous continuer la
		// création/modification",
		// null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		// null, null);
		// if (k2 == 1) {
		// t2 = 5;
		// }
		// }
		// }
		// t2++;
		// }
		// break;

		case Input.KEY_P:
			// this.container.pause();
			JOptionPane pause = new JOptionPane();
			String[] boutonP = { "Reprendre" };
			pause.showOptionDialog(null, "Reprendre le jeu ?", "Jeu en pause", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, boutonP, null);
			break;

		case Input.KEY_Z:
			// this.container.resume();
			this.j2.setD(Direction.Nord);
			this.direction2 = 0;
			this.moving2 = true;
			break;
		case Input.KEY_Q:
			this.j2.setD(Direction.Ouest);
			this.direction2 = 1;
			this.moving2 = true;
			break;
		case Input.KEY_S:
			this.j2.setD(Direction.Sud);
			this.direction2 = 2;
			this.moving2 = true;
			break;
		case Input.KEY_D:
			this.j2.setD(Direction.Est);
			this.direction2 = 3;
			this.moving2 = true;
			break;
		}
	}

	public void afficher_expr() throws SlickException {

		Image e;

		for (int i = 0; i < Terrain.getTaille(); i++) {
			for (int j = 0; j < Terrain.getTaille(); j++) {
				if (Terrain.casexy(i, j).isExpr() && Terrain.casexy(i, j).expr().isOperateur()) {
					switch ((Operateur) Terrain.casexy(i, j).expr()) {
					case Deuxpoints:
						e = new Image("maps/deuxpoints.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case PointVirgule:
						e = new Image("maps/pointvirgule.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Choix:
						e = new Image("maps/doublepipe.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Choixequi:
						e = new Image("maps/pipe.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Star:
						e = new Image("maps/etoile.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Priorite:
						e = new Image("maps/sup.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					default:
						break;
					}
				} else if (Terrain.casexy(i, j).isExpr() && Terrain.casexy(i, j).expr().isComportement()) {
					switch ((Comportement) Terrain.casexy(i, j).expr()) {
					case Attack:
						e = new Image("maps/A.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Kamikaze:
						e = new Image("maps/K.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Protect:
						e = new Image("maps/P.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Explore:
						e = new Image("maps/X.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					case Suivre:
						e = new Image("maps/F.png");
						e.draw(32 * (15 + j), (32 * i));
						break;
					default:
						break;
					}
				}

			}
		}
	}

	public void afficher_pdv(Joueur j) throws SlickException {
		Image deb_v_r = new Image("maps/debut_vie_red.png");
		Image mil_v_r = new Image("maps/milieu_vie_red.png");
		Image fin_v_r = new Image("maps/fin_vie_red.png");

		Image deb_v_b = new Image("maps/debut_vie_bleu.png");
		Image mil_v_b = new Image("maps/milieu_vie_bleu.png");
		Image fin_v_b = new Image("maps/fin_vie_bleu.png");

		float ratio = (float) (j.getPdv() * 0.01 * 21 * 7);

		if (j.getCouleur() == Couleur.Bleu && j.getPdv() > 0) {
			deb_v_b.draw(this.DEBUT_VIE_BLEU_X, this.VIE_Y);

			for (float i = this.DEBUT_VIE_BLEU_X + 7; i < this.DEBUT_VIE_BLEU_X + ratio; i += 7) {
				mil_v_b.draw(i, this.VIE_Y);
			}
			if (j.getPdv() == 100)
				fin_v_b.draw(this.FIN_VIE_BLEU_X, this.VIE_Y);
		} else if (j.getPdv() > 0) {
			deb_v_r.draw(this.DEBUT_VIE_ROUGE_X, this.VIE_Y);
			for (float i = this.DEBUT_VIE_ROUGE_X + 7; i < this.DEBUT_VIE_ROUGE_X + ratio; i += 7) {
				mil_v_r.draw(i, this.VIE_Y);
			}
			if (j.getPdv() == 100)
				fin_v_r.draw(this.FIN_VIE_ROUGE_X, this.VIE_Y);

		}
	}

	public void afficher_nrj(Joueur j) throws SlickException {
		Image deb = new Image("maps/debut_nrj.png");
		Image mil = new Image("maps/milieu_nrj.png");
		Image fin = new Image("maps/fin_nrj.png");

		float ratio = (float) (j.getNrj() * 0.01 * 21 * 7);

		if (j.getCouleur() == Couleur.Bleu && j.getNrj() > 0) {
			deb.draw(this.DEBUT_VIE_BLEU_X, this.NRJ_Y);

			for (float i = this.DEBUT_VIE_BLEU_X + 7; i < this.DEBUT_VIE_BLEU_X + ratio; i += 7) {
				mil.draw(i, this.NRJ_Y);
			}
			if (j.getNrj() >= 100)
				fin.draw(this.FIN_VIE_BLEU_X, this.NRJ_Y);
		} else if (j.getNrj() > 0) {
			deb.draw(this.DEBUT_VIE_ROUGE_X, this.NRJ_Y);
			for (float i = this.DEBUT_VIE_ROUGE_X + 7; i < this.DEBUT_VIE_ROUGE_X + ratio; i += 7) {
				mil.draw(i, this.NRJ_Y);
			}

			if (j.getNrj() >= 100) {
				fin.draw(this.FIN_VIE_ROUGE_X, this.NRJ_Y);
			}

		}
	}

	public void afficher_inventaire(Joueur j) throws SlickException {

		Image e;

		int colonne = 0, ligne = 0;
		for (int i = 0; i < j.inventaire().size(); i++) {
			if (j.inventaire().get(i).isOperateur()) {
				switch ((Operateur) j.inventaire().get(i)) {
				case Deuxpoints:
					e = new Image("maps/deuxpoints.png");
					break;
				case PointVirgule:
					e = new Image("maps/pointvirgule.png");
					break;
				case Choix:
					e = new Image("maps/doublepipe.png");
					break;
				case Choixequi:
					e = new Image("maps/pipe.png");
					break;
				case Star:
					e = new Image("maps/etoile.png");
					break;
				case Priorite:
					e = new Image("maps/sup.png");
					break;
				default:
					e = new Image("maps/deuxpoints.png");
					break;
				}
				if (j.getCouleur() == Couleur.Bleu)
					e.draw(162 + (45 * colonne), 205 + (44 * ligne));
				else
					e.draw(1622 + (45 * colonne), 205 + (44 * ligne));

			} else if (j.inventaire().get(i).isComportement()) {
				switch ((Comportement) j.inventaire().get(i)) {
				case Attack:
					e = new Image("maps/A.png");
					break;
				case Kamikaze:
					e = new Image("maps/K.png");
					break;
				case Protect:
					e = new Image("maps/P.png");
					break;
				case Explore:
					e = new Image("maps/X.png");
					break;
				case Suivre:
					e = new Image("maps/F.png");
					break;
				default:
					e = new Image("maps/deuxpoints.png");
					break;
				}

				if (j.getCouleur() == Couleur.Bleu)
					e.draw(162 + (45 * colonne), 205 + (44 * ligne));
				else
					e.draw(1622 + (45 * colonne), 205 + (44 * ligne));
			}

			colonne++;
			if (colonne > 2) {
				ligne++;
				colonne = 0;
			}
		}

	}

	public static void launch_game(Joueur j1, Joueur j2) {
		AppGameContainer game;
		try {
			game = new AppGameContainer(new View(j1, j2), 1920, 960, false);
			game.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// game.setFullscreen(true);
		// game.setDisplayMode(1920, 1080, true);

	}

}