package machine_a_glace;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+
 *         G- e+++ h+ r- y-
 */
public class MapGameState extends BasicGameState {
	public static final int ID = 2;

	private GameContainer container;
	private TiledMap map;

	static int seconde = 10000;

	static int minute = 5;

	boolean jeufini = false;

	public static ArrayList<Integer> cmptr_robots=new ArrayList<Integer>();
	public static ArrayList<Integer> secsrobots= new ArrayList<Integer>();
	public static ArrayList<Robot> allrobots= new ArrayList<Robot>();
	public static ArrayList<Boolean> canmoverobots = new ArrayList<Boolean>();
	public static ArrayList<String> automaterobot = new ArrayList<String>();


	boolean bool1 = false;
	boolean bool2 = false;
	boolean bool3 = false;

	boolean sauve = false;

	boolean menu = false;

	boolean pause = false;
	public static boolean bonus_malus = false;

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

	private int secs1 = 0;
	private int secs2 = 0;
	

	private final float DEBUT_VIE_ROUGE_X = 1631;
	private final float FIN_VIE_ROUGE_X = 1778;
	private final float VIE_Y = 122;

	private final float NRJ_Y = 145;
	private final float DEBUT_VIE_BLEU_X = 130;
	private final float FIN_VIE_BLEU_X = 277;

	java.awt.Font UIFont3;
	org.newdawn.slick.UnicodeFont uniFont3;

	// private final float DEBUT_NRJ_ROUGE_X = 1631;
	// private final float FIN_NRJ_ROUGE_X = 1778;
	//
	//
	// private final float DEBUT_NRJ_BLEU_X = 130;
	// private final float FIN_NRJ_BLEU_X = 277;

	private boolean canmove = false;
	private boolean canmove2 = false;

	public static Joueur j1, j2;

	public static boolean recolorie_par_dessus = false;

	private boolean moving2 = false;
	private Animation[] animations = new Animation[8];
	private Animation[] animations2 = new Animation[8];
	private Animation[] animations3 = new Animation[8];
	private Animation[] animations4 = new Animation[8];
	private Animation[] animations5 = new Animation[8];

	private ImageIcon icr = new ImageIcon("maps/tete_robot_rouge.png");
	private ImageIcon icb = new ImageIcon("maps/tete_robot_bleu.png");
	private Dimension d = new Dimension(100, 100);

	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont uniFont;

	java.awt.Font UIFont2;
	org.newdawn.slick.UnicodeFont uniFont2;

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		this.container = container;

		this.map = new TiledMap("maps/map/map1.tmx");
		container.setShowFPS(false);

	
		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/reveil.ttf"));

			UIFont3 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("maps/cartoon.ttf"));

			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 60.f);

			uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
			uniFont.addAsciiGlyphs();
			uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
			uniFont.addAsciiGlyphs();
			uniFont.loadGlyphs();

			UIFont3 = UIFont3.deriveFont(java.awt.Font.BOLD, 60.f);

			uniFont3 = new org.newdawn.slick.UnicodeFont(UIFont3);
			uniFont3.addAsciiGlyphs();
			uniFont3.getEffects().add(new ColorEffect(java.awt.Color.cyan));
			uniFont3.addAsciiGlyphs();
			uniFont3.loadGlyphs();

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

		SpriteSheet spriteSheet5 = new SpriteSheet("maps/bonus.png", 64, 64);
		SpriteSheet spriteSheet4 = new SpriteSheet("maps/robot_blue.png", 64, 64);

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

		this.animations5[0] = loadAnimation(spriteSheet5, 0, 1, 0);
		this.animations5[1] = loadAnimation(spriteSheet5, 0, 1, 1);
		this.animations5[2] = loadAnimation(spriteSheet5, 0, 1, 2);
		this.animations5[3] = loadAnimation(spriteSheet5, 0, 1, 3);
		this.animations5[4] = loadAnimation(spriteSheet5, 1, 9, 0);
		this.animations5[5] = loadAnimation(spriteSheet5, 1, 9, 1);
		this.animations5[6] = loadAnimation(spriteSheet5, 1, 9, 2);
		this.animations5[7] = loadAnimation(spriteSheet5, 1, 9, 3);

		int tileW = this.map.getTileWidth();
		int tileH = this.map.getTileHeight();
		int logicLayer = this.map.getLayerIndex("Collision");
		Image tile;

		for (int i = 16 * 32; i <= 56 * 32; i += 32) {
			for (int j = 32; j <= 28 * 32; j += 32) {
				tile = this.map.getTileImage((int) i / tileW, (int) j / tileH, logicLayer);
				if (tile != null) {
					Terrain.terrain[j / tileW][i / tileH - 15].setCase(Contenu.Obstacle);
				}
			}
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Image peinture_rouge = new Image("maps/peinture_rouge.png");
		Image peinture_bleu = new Image("maps/peinture_bleu.png");
		Image hud_bleu = new Image("maps/hud_bleu.png");
		Image hud_rouge = new Image("maps/hud_rouge.png");

		Image inventaire = new Image("maps/inventaire.png");
		Image robots_inv = new Image("maps/robots_inv.png");
		Image robots_inv2 = new Image("maps/robots_inv2.png");

		Image j1g = new Image("maps/j1g.jpg");
		Image j2g = new Image("maps/j2g.jpg");

		Image pauseimg = new Image("maps/pause.png");
		Image menuimg = new Image("maps/menuaide.png");

		if (this.jeufini == true) {

			if (this.j1.getNombre_Case_Coloriees() > this.j2.getNb_cases_coloriees()) {
				MainScreenGameState.joueur_1_gagne = true;
				game.enterState(MainScreenGameState.ID);
			} else if (this.j1.getNombre_Case_Coloriees() < this.j2.getNb_cases_coloriees()) {
				MainScreenGameState.joueur_2_gagne = true;
				game.enterState(MainScreenGameState.ID);
			} else {
				MainScreenGameState.egalite = true;
				game.enterState(MainScreenGameState.ID);
			}

		} else if (this.j1.getPdv() <= 0) {
			MainScreenGameState.joueur_2_gagne = true;
			game.enterState(MainScreenGameState.ID);
		} else if (this.j2.getPdv() <= 0) {
			MainScreenGameState.joueur_1_gagne = true;
			game.enterState(MainScreenGameState.ID);

		} else if (pause == true) {
			if (menu == true) {
				menuimg.draw(0, 0, container.getWidth(), container.getHeight());

			} else if (sauve == true) {
				pauseimg.draw(0, 0, container.getWidth(), container.getHeight());
				uniFont3.drawString(500, 350, "Vous venez de sauvegarder la partie,", Color.cyan);
				uniFont3.drawString(100, 400, "Appuyez sur \"C\" pour la charger au prochain lancement du jeu",
						Color.cyan);

				uniFont3.drawString(450 - 40, 650, "Appuyer sur \"P\" pour relancer la partie", Color.cyan);
			} else {
				pauseimg.draw(0, 0, container.getWidth(), container.getHeight());
				uniFont3.drawString(450 - 40, 450, "Appuyer sur \"P\" pour relancer la partie", Color.cyan);
			}

		} else {

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

			if (bonus_malus == true) {
				double a = Math.random();
				if (a < 0.20) {
					BonusMalus.inversionCouleur(j1, j2);
				} else if (a < 0.40) {
					BonusMalus.inversionInventaire(j1, j2);
				} else if (a < 0.60) {
					BonusMalus.pvAdd(j1, j2);
				} else if (a < 0.80){
					BonusMalus.pvLost(j1, j2);
				} else {
					BonusMalus.swapPlayer(j1, j2);
				}
				bonus_malus = false;
			}

			afficher_expr();

			PrintEntity(g);

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

			// System.out.println(r1.etat_courant());

			robots_inv.draw(120, 670);
			robots_inv2.draw(1580, 670);

			afficher_robots(g);

			// this.popup_test_1 = 0;
			// if
			// (Terrain.terrain[this.j1.getLine()][this.j1.getCol()].isCreer()
			// && j1.isNrj()) {
			// Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCase(Contenu.Joueur);
			// Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCouleur(Couleur.Neutre);
			// j1.SetNrj(j1.getNrj() - 75);
			// this.container.pause();

			if (this.popup_test_1 == 25) {

				this.popup_test_1 = 0;
				if (Terrain.terrain[this.j1.getLine()][this.j1.getCol()].isCreer() && j1.isNrj()) {
					Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCase(Contenu.Joueur);
					Terrain.terrain[this.j1.getLine()][this.j1.getCol()].setCouleur(Couleur.Neutre);
					j1.SetNrj(j1.getNrj() - 75);
					this.container.pause();

					int t = 0;
					while (t < 1) {
						JOptionPane r = new JOptionPane();
						r.setSize(d);
						String[] bouton = { "Créer", "Modifier", "Annuler" };
						int retour = r.showOptionDialog(null, "Faite votre choix", "Menu des robots",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, this.icr, bouton,
								bouton[0]);
						if (retour == 1 && j1.robots().size() > 0) {

							String[] robot = j1.arrayRobottoString();
							JComboBox robots = new JComboBox(robot);
							String inputrm = JOptionPane.showInputDialog(null, robots, "Saisissez votre expression");
							if (inputrm != null) {
								Node n = new Node(null);
								boolean annuler = true;
								while (annuler
										&& (!Parser.ExpressionCorrecte(inputrm) || !Parser.InventaireOk(inputrm, j1))) {
									if (inputrm != null) {
										inputrm = JOptionPane.showInputDialog(null, robots,
												"Saisissez votre expression");
									}
									if (inputrm == null) {
										int z = JOptionPane.showOptionDialog(null,
												"Voulez-vous continuer la création/modification", null,
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
												null);
										if (z == 1 || z == -1) {
											t = 1;
											annuler = false;
										} else {
											inputrm = "ddg";
										}
									}
								}
								if (annuler) {
									String p = (String) robots.getSelectedItem();
									// System.out.println(p);
									n = Parser.ExpressionCorrecte1(inputrm);
									n = new Node(Operateur.Star, null, n);
									int i = p.charAt(6) - '0';
									// System.out.println(i);
									j1.robots().get(i - 1).modificationRobot(n);
									automaterobot.set(i - 1, inputrm);
								}
							}
							if (inputrm == null && t != 1) {
								int k = JOptionPane.showOptionDialog(null,
										"Voulez-vous continuer la création/modification", null,
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								if (k == 1) {
									t = 1;
								} else {
									t--;
								}
							}
						}
						if (retour == 0 && j1.robots().size() < 4) {
							JOptionPane p = new JOptionPane();
							ArrayList<String> tab5 = j1.inventaire_toString();

							String inputrc = p.showInputDialog(tab5, "Saisissez votre expression");
							if (inputrc != null) {
								// System.out.println(inputrc);
								Node m = new Node(null);
								boolean annuler = true;
								while (annuler
										&& (!Parser.ExpressionCorrecte(inputrc) || !Parser.InventaireOk(inputrc, j1))) {
									if (inputrc != null) {
										inputrc = p.showInputDialog(tab5, "Saisissez votre expression");
									}
									if (inputrc == null) {
										int z = JOptionPane.showOptionDialog(null,
												"Voulez-vous continuer la création/modification", null,
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
												null);
										if (z == 1 || z == -1) {
											t = 1;
											annuler = false;
										} else {
											inputrc = "ddg";
										}
									}
								}
								// char t5[] = inputrc.toCharArray();
								// ArrayList<Expr> inv = (ArrayList<Expr>)
								// j1.inventaire().clone();
								// System.out.println(t5);
								if (annuler) {
									m = Parser.ExpressionCorrecte1(inputrc);
									m = new Node(Operateur.Star, null, m);
									Coordonnees c = Terrain.spawnRed();
									Robot rob = new Robot(c.getLigne(), c.getCol(), j1.getCouleur(), m);

									rob.setD(Direction.Sud);
									rob.setJoueur(j1);
									allrobots.add(rob);
									canmoverobots.add(false);
									cmptr_robots.add(1);
									secsrobots.add(0);
									automaterobot.add(inputrc);
								}

							}
							tab5.clear();
							if (inputrc == null && t != 1) {
								int k = JOptionPane.showOptionDialog(null,
										"Voulez-vous continuer la création/modification", null,
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								if (k == 1 || k == -1) {
									t = 1;
								} else {
									t--;
								}

							}

						}

						else if (retour == 0 && j1.robots().size() == 4) {
							t--;
						} else if (retour == 1 && j1.robots().size() == 0) {
							t--;
						}
						if (retour == 2 || retour == -1) {
							t = 1;
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
				// System.out.println(popup_test_1);

			}

			if (this.popup_test_2 == 25) {
				this.popup_test_2 = 0;
				if (Terrain.terrain[this.j2.getLine()][this.j2.getCol()].isCreer() && j2.isNrj()) {
					Terrain.terrain[this.j2.getLine()][this.j2.getCol()].setCase(Contenu.Joueur);
					Terrain.terrain[this.j2.getLine()][this.j2.getCol()].setCouleur(Couleur.Neutre);
					j2.SetNrj(j2.getNrj() - 75);
					this.container.pause();
					int t2 = 0;
					while (t2 < 1) {
						JOptionPane r2 = new JOptionPane();
						r2.setSize(d);
						String[] bouton2 = { "Créer", "Modifier", "Annuler" };
						int retour2 = r2.showOptionDialog(null, "Faite votre choix", "Menu des robots",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.icb, bouton2,
								bouton2[0]);
						if (retour2 == 1 && j2.robots().size() > 0) {
							String[] robot = j2.arrayRobottoString();
							JComboBox robots = new JComboBox(robot);
							String inputbm = JOptionPane.showInputDialog(null, robots, "Saisissez votre expression");
							if (inputbm != null) {
								Node n = new Node(null);
								boolean annuler = true;
								while (annuler
										&& (!Parser.ExpressionCorrecte(inputbm) || !Parser.InventaireOk(inputbm, j2))) {
									if (inputbm != null) {
										inputbm = JOptionPane.showInputDialog(null, robots,
												"Saisissez votre expression");
									}
									if (inputbm == null) {
										int z = JOptionPane.showOptionDialog(null,
												"Voulez-vous continuer la création/modification", null,
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
												null);
										if (z == 1 || z == -1) {
											t2 = 1;
											annuler = false;
										} else {
											inputbm = "ddg";
										}
									}
								}
								if (annuler) {
									String k = (String) robots.getSelectedItem();
									System.out.println(k);
									n = Parser.ExpressionCorrecte1(inputbm);
									n = new Node(Operateur.Star, null, n);
									int i = k.charAt(6) - '0';
									// System.out.println(i);
									j2.robots().get(i - 1).modificationRobot(n);

									automaterobot.set(i - 1, inputbm);
								}
							}
							if (inputbm == null && t2 != 1) {
								int k2 = JOptionPane.showOptionDialog(null,
										"Voulez-vous continuer la création/modification", null,
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								if (k2 == 1 || k2 == -1) {
									t2 = 1;
								} else {
									t2--;
								}
							}
						}
						if (retour2 == 0 && j2.robots().size() < 4) {
							JOptionPane rbc = new JOptionPane();

							ArrayList<String> tab4 = j2.inventaire_toString();
							String inputbc = rbc.showInputDialog(tab4, "Saisissez votre expression");
							if (inputbc != null) {
								Node n = new Node(null);
								boolean annuler = true;
								while (annuler
										&& (!Parser.ExpressionCorrecte(inputbc) || !Parser.InventaireOk(inputbc, j2))) {
									if (inputbc != null) {
										inputbc = rbc.showInputDialog(tab4, "Saisissez votre expression");
									}
									if (inputbc == null) {
										int z = JOptionPane.showOptionDialog(null,
												"Voulez-vous continuer la création/modification", null,
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
												null);
										if (z == 1 || z == -1) {
											t2 = 1;
											annuler = false;
										} else {
											inputbc = "ddg";
										}
									}
								}
								if (annuler) {
									n = Parser.ExpressionCorrecte1(inputbc);
									n = new Node(Operateur.Star, null, n);
									Coordonnees c = Terrain.spawnBlue();
									Robot rob = new Robot(c.getLigne(), c.getCol(), j2.getCouleur(), n);

									rob.setD(Direction.Nord);
									rob.setJoueur(j2);
									allrobots.add(rob);
									canmoverobots.add(false);
									cmptr_robots.add(1);
									secsrobots.add(0);
									automaterobot.add(inputbc);
								}
							}
							tab4.clear();
							if (inputbc == null && t2 != 1) {
								int k2 = JOptionPane.showOptionDialog(null,
										"Voulez-vous continuer la création/modification", null,
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								if (k2 == 1 || k2 == -1) {
									t2 = 1;
								} else {
									t2--;
								}
							}
						} else if (retour2 == 0 && j1.robots().size() == 4) {
							t2--;
						} else if (retour2 == 1 && j1.robots().size() == 0) {
							t2--;
						}
						if (retour2 == 2 || retour2 == -1) {
							t2 = 1;
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
			// g.drawString(minute + " m " + seconde / 1000 + " s", 945,
			// 470);
			
			afficher_bonus(g);
			
			g.setColor(new Color(255, 255, 255));
			int PB, PR;
			PB = (int) (PourcentBleu * 100);
			PR = (int) (PourcentRouge * 100);
			if(PB + PR == 99){
				if(PB > PR) PR++;
				else PB++;
			}
			uniFont2.drawString(290, 10, PB + " % ", Color.blue);
			g.drawString("" + j2.getPdv(), 190, 120);
			g.drawString("" + j1.getPdv(), 1700, 120);
			g.drawString(" Inventaire ", 187, 170);
			g.drawString(" Inventaire ", 1617, 170);

			g.drawString(" Etat des robots ", 140, 650);
			g.drawString(" Etat des robots  ", 1617, 650);

			uniFont2.drawString(1472, 10, PR + " % ", Color.red);
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
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (Terrain.Index > 0)
			bool3 = Terrain.ReduceTimer();
		if (bool3) {
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

		if ((int) (seconde) % 2 == 0) {

			if (j1.getNrj() < 100)
				j1.SetNrj(j1.getNrj() + 1);
			if (j2.getNrj() < 100)

				j2.SetNrj(j2.getNrj() + 1);
		}

		if (seconde > -delta && seconde < delta) {
			seconde = 60000;
			if (minute == 0) {
				// this.container.exit();
				this.jeufini = true;
			} else
				minute--;
		}
		seconde -= delta;
		this.secs1 += delta;
		this.secs2 += delta;

		for (int i = 0; i < secsrobots.size(); i++) {
			secsrobots.set(i, secsrobots.get(i) + delta);
		}

		if (pause == false) {
			this.container.resume();
		}

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

			}
			// System.out.println("pos : " + x + " - " + y);
			if (this.secs1 < 500) {
				canmove = false;
			} else {
				this.secs1 = 0;
				canmove = true;
			}
		}
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

		for (int i = 0; i < allrobots.size(); i++) {
			Robot r = allrobots.get(i);
			if (r != null && Terrain.casexy(r.getLine(), r.getCol()).getEntite() != null
					&& Terrain.casexy(r.getLine(), r.getCol()).getEntite().isRobot()) {
				if (canmoverobots.get(i))
					r.execute();
			}

			if (secsrobots.get(i) > 5000) {
				r.next_etat();
				r.execute();
				secsrobots.set(i, 0);
				cmptr_robots.set(i, 1);
			} else if (secsrobots.get(i) > 500 * cmptr_robots.get(i)) {
				canmoverobots.set(i, true);
				cmptr_robots.set(i, cmptr_robots.get(i) + 1);
			} else {
				canmoverobots.set(i, false);
			}

		}

		update_arrays();

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
			if (pause == false) {
				j1.setD(Direction.Nord);
				this.direction = 0;
				this.moving = true;
			}
			break;
		case Input.KEY_LEFT:
			if (pause == false) {
				j1.setD(Direction.Ouest);
				this.direction = 1;
				this.moving = true;
			}
			break;
		case Input.KEY_DOWN:
			if (pause == false) {
				j1.setD(Direction.Sud);
				this.direction = 2;
				this.moving = true;
			}
			break;

		case Input.KEY_RIGHT:
			if (pause == false) {
				j1.setD(Direction.Est);
				this.direction = 3;
				this.moving = true;
			}
			break;

		case Input.KEY_P:
			if (pause == false) {
				this.container.pause();
				this.pause = true;

			} else {
				this.pause = false;
				this.sauve = false;
			}

			// JOptionPane pause = new JOptionPane();
			// String[] boutonP = { "Reprendre" };
			// pause.showOptionDialog(null, "Reprendre le jeu ?", "Jeu en
			// pause", JOptionPane.DEFAULT_OPTION,
			// JOptionPane.QUESTION_MESSAGE, null, boutonP, null);
			break;

		case Input.KEY_H:
			if (pause == false) {
				this.container.pause();
				this.pause = true;
				this.menu = true;

			} else {
				this.pause = false;
				this.menu = false;
			}

			// JOptionPane pause = new JOptionPane();
			// String[] boutonP = { "Reprendre" };
			// pause.showOptionDialog(null, "Reprendre le jeu ?", "Jeu en
			// pause", JOptionPane.DEFAULT_OPTION,
			// JOptionPane.QUESTION_MESSAGE, null, boutonP, null);
			break;

		case Input.KEY_Z:
			// this.container.resume();
			if (pause == false) {
				this.j2.setD(Direction.Nord);
				this.direction2 = 0;
				this.moving2 = true;
			}
			break;
		case Input.KEY_Q:
			if (pause == false) {
				this.j2.setD(Direction.Ouest);
				this.direction2 = 1;
				this.moving2 = true;
			}
			break;
		case Input.KEY_S:
			if (pause == false) {
				this.j2.setD(Direction.Sud);
				this.direction2 = 2;
				this.moving2 = true;
			}
			break;
		case Input.KEY_D:
			if (pause == false) {
				this.j2.setD(Direction.Est);
				this.direction2 = 3;
				this.moving2 = true;
			}
			break;

		case Input.KEY_N:
			this.pause = true;
			this.sauve = true;
			Sauvegarde.Writer(minute, seconde);
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
					e = new Image(((Comportement) Terrain.casexy(i, j).expr()).image);
					e.draw(32 * (15 + j), (32 * i));

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

				e = new Image(((Comportement) j.inventaire().get(i)).image);

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

	public void PrintEntity(Graphics g) {
		int i, j;
		int taille = Terrain.getTaille();
		Entite ent;
		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				if (Terrain.terrain[i][j].getEntite() != null) {
					ent = Terrain.terrain[i][j].getEntite();
					if (Terrain.terrain[i][j].getEntite().isRobot()) {
						if (ent.getCouleur() == Couleur.Rouge) {
							g.drawAnimation(animations3[ent.getD().entier() + (true ? 4 : 0)],
									(15 * 32 + ent.getCol() * 32 + 16) - 32, (ent.getLine() * 32 + 16) - 60);
						} else {
							g.drawAnimation(animations4[ent.getD().entier() + (true ? 4 : 0)],
									(15 * 32 + ent.getCol() * 32 + 16) - 32, (ent.getLine() * 32 + 16) - 60);
						}
					} else if (Terrain.terrain[i][j].getEntite().isJoueur()) {
						if (ent.getCouleur() == Couleur.Rouge) {
							g.drawAnimation(animations[ent.getD().entier() + (moving ? 4 : 0)],
									(15 * 32 + ent.getCol() * 32 + 16) - 32, (ent.getLine() * 32 + 16) - 60);
						} else {
							g.drawAnimation(animations2[ent.getD().entier() + (moving2 ? 4 : 0)],
									(15 * 32 + ent.getCol() * 32 + 16) - 32, (ent.getLine() * 32 + 16) - 60);
						}
					}
				}

			}
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	public void update_arrays() {
		for (int i = 0; i < allrobots.size(); i++) {
			if (allrobots.get(i).getCouleur() == Couleur.Rouge && (!j1.robots().contains(allrobots.get(i)))) {
				allrobots.remove(i);
				automaterobot.remove(i);
				canmoverobots.remove(i);
				cmptr_robots.remove(i);
				secsrobots.remove(i);
			}

			else if (allrobots.get(i).getCouleur() == Couleur.Bleu && (!j2.robots().contains(allrobots.get(i)))) {
				allrobots.remove(i);
				automaterobot.remove(i);
				canmoverobots.remove(i);
				cmptr_robots.remove(i);
				secsrobots.remove(i);
			}

		}

	}

	public void afficher_robots(Graphics g) throws SlickException {
		Image e;
		int nb_robot_rouge = 0;
		int nb_robot_bleu = 0;
		for (int i = 0; i < allrobots.size(); i++) {
			if (allrobots.get(i).getCouleur() == Couleur.Rouge) {
				e = new Image("maps/tete_robot_rouge.png");
				e.draw(1729, 690 + (nb_robot_rouge * 42), 32, 32);
				g.drawString("*" + automaterobot.get(i), 1605, 698 + (nb_robot_rouge * 42));
				nb_robot_rouge++;
			} else {
				e = new Image("maps/tete_robot_bleu.png");
				e.draw(140, 690 + (nb_robot_bleu * 42), 32, 32);
				g.drawString("*" + automaterobot.get(i), 180, 698 + (nb_robot_bleu * 42));
				nb_robot_bleu++;

			}

		}
	}

	public void afficher_bonus(Graphics g) {
		if (Terrain.terrain[Terrain.BonusMalus.getLigne()][Terrain.BonusMalus.getCol()]
				.getCont() == Contenu.Bonus_Malus) {
			g.drawAnimation(animations5[0 + (true ? 4 : 0)], (Terrain.BonusMalus.getCol() * 32 + 15 * 32 + 16) - 32,
					(Terrain.BonusMalus.getLigne() * 32 + 16) - 60);
		}
		if (Terrain.terrain[Terrain.BonusMalu.getLigne()][Terrain.BonusMalu.getCol()]
				.getCont() == Contenu.Bonus_Malus) {
			g.drawAnimation(animations5[0 + (true ? 4 : 0)], (Terrain.BonusMalu.getCol() * 32 + 15 * 32 + 16) - 32,
					(Terrain.BonusMalu.getLigne() * 32 + 16) - 60);
		}
	}

}
