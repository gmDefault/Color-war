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
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;

public class View extends BasicGame {
	private GameContainer container;
	private TiledMap map;
	
	private float x = 976, y = 32+16;
	private float xx = 976, yy = 960-32-16;
	private int direction = 2;
	private int direction2 = 0;
	private boolean moving = false;
	private long lasttime = System.currentTimeMillis();
	private long lasttime2 = System.currentTimeMillis();
	
	private ArrayList<Point> pos_color = new ArrayList<Point>();
	private ArrayList<Point> pos_color_2 = new ArrayList<Point>();


	private boolean canmove = false;
	private boolean canmove2 = false;

	private boolean moving2 = false;
	private Animation[] animations = new Animation[8];
	private Animation[] animations2 = new Animation[8];
	
	private String item[] = {"A","P","K"};
	private String item2[] = {"Z","T","V"};
	private JComboBox robot = new JComboBox(item);
	private JComboBox robot2 = new JComboBox(item2);
	private ImageIcon ic = new ImageIcon("robot.png");
	private Dimension d = new Dimension(100,100);
	private String tab[] = {"Frapper","Explorer", "Kamikaze",";","*",">"};
	private String tab2[] = {"Manger","Fumer", "Rond-Poing"};
	

	public View() {
		super("Fenetre");
		// TODO Auto-generated constructor stub
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.container = arg0;
		this.map = new TiledMap("maps/map/map1.tmx");
		//container.setShowFPS(false);
		SpriteSheet spriteSheet = new SpriteSheet("maps/char.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
		
		
		this.animations2[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations2[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations2[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations2[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations2[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations2[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations2[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations2[7] = loadAnimation(spriteSheet, 1, 9, 3);
		
		robot.setSize(100, 100);
	    robot2.setSize(100, 100);
		
		  Music background = new Music("maps/FoxieEpic.OGG");
		    background.loop();

		
	}
	
	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		//this.map.render(0, 0)
		Image peinture_rouge = new Image("maps/peinture_rouge.png");
		Image peinture_bleu = new Image("maps/peinture_bleu.png");
		
//		Font font = font = new UnicodeFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 12));
//		TextField nb_cases_red = new TextField(this.container,font,10,10,200,200);
//		nb_cases_red.setText("Nombre de cases colorées : ");
		this.map.render(0, 0);

		for(int i = 0; i<this.pos_color.size(); i++) {
			peinture_rouge.drawCentered(this.pos_color.get(i).getX(), this.pos_color.get(i).getY());
		}
		
		for(int i = 0; i<this.pos_color_2.size(); i++) {
			peinture_bleu.drawCentered(this.pos_color_2.get(i).getX(), this.pos_color_2.get(i).getY());
		}
		
//		for (int i = 250; i<800; i=i+(800/30)) {
//			for (int j = 0; j < 600; j = j+(800/30)) {
//
//				//System.out.println("B : " + g.getPixel(i+10, j+10).getBlue() + " V : " + g.getPixel(i+10, j+10).getGreen() + " R : " + g.getPixel(i+10, j+10).getRed());
//			//	System.out.println("B : " + grass_b.getColor(10, 10).getBlue() + " V : " + grass_b.getColor(10, 10).getGreen() + " R : " + grass_b.getColor(10, 10).getRed());
//				g.drawImage(img, i, j);
//
//			}
//		}
	//	img.drawCentered(x, y);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32, y - 60);
		g.drawAnimation(animations2[direction2 + (moving2 ? 4 : 0)], xx - 32, yy - 60);

		
		//g.setColor(new Color(50, 50, 50, .5f));
		//g.fillOval(x - 16, y - 8, 32, 16);

	}

	
	
//	@Override
//	public void render(GameContainer container, Graphics g) throws SlickException {
//		this.map.render(0, 0);
//}
	
	private boolean isCollision(float x, float y) {
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("Collision");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    boolean collision = tile != null;
	    if (collision) {
	        Color color = tile.getColor((int) x % tileW, (int) y % tileH);
	        collision = color.getAlpha() > 0;
	    }
	    return collision;
	}


	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (this.moving) {
			switch (this.direction) {
			case 0:
				if (canmove) {
					if (!isCollision(this.x, this.y - (1024/32))) {
						this.y -= (1024/32);
						
						Point p = new Point(this.x, this.y);
						for(int i = 0; i<this.pos_color.size(); i++){
							if (this.pos_color.get(i).equals(p)) {
								this.pos_color.remove(this.pos_color.get(i));
							}
						}
						
						this.pos_color.add(p);
							
						for(int i = 0; i<this.pos_color_2.size(); i++){
							if (this.pos_color_2.get(i).equals(p)) {
								this.pos_color_2.remove(this.pos_color_2.get(i));
							}
						}
						
						//this.container.getGraphics().drawImage(grass_b, x, y);
						//arg0.getGraphics().drawImage(grass_b, x, y);
						//grass_b.draw(x, y);


					}

				}
				//System.out.println("pos : " + x + " - " + y);
				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 1:
				if (canmove) {
					if (!isCollision(this.x - (1024/32), this.y))
					this.x -= (1024/32);
					Point p = new Point(this.x, this.y);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);

						
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 2:
				if (canmove) {
					if (!isCollision(this.x, this.y + (1024/32)))
					this.y += (1024/32);
					Point p = new Point(this.x, this.y);
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 3:
				if (canmove) {
					if (!isCollision(this.x + (1024/32), this.y))
					this.x += (1024/32);
					
					Point p = new Point(this.x, this.y);
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;

			}
}
		if (this.moving2) {
			switch (this.direction2) {
			case 0:
				if (canmove2) {
					if (!isCollision(this.xx, this.yy - (1024/32)))
					this.yy -= (1024/32);
					Point p = new Point(this.xx, this.yy);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}				
				break;
			case 1:
				if (canmove2) {
					if (!isCollision(this.xx - (1024/32), this.yy))
					this.xx -= (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;
			case 2:
				if (canmove2) {
					if (!isCollision(this.xx, this.yy + (1024/32)))
					this.yy += (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;
			case 3:
				if (canmove2) {
					if (!isCollision(this.xx + (1024/32), this.yy))
					this.xx += (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;

			}
}

	}
	
	
	@Override
	public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
        //System.out.println(key + "- " + c);
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
	        case Input.KEY_UP: this.direction = 0; this.moving = true; break;
	        case Input.KEY_LEFT: this.direction = 1; this.moving = true; break;
	        case Input.KEY_DOWN: this.direction = 2; this.moving = true; break;
	        case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;
	        
	        case Input.KEY_R: 
	        	JOptionPane r = new JOptionPane(); 
	        	r.setSize(d);
	        	String[] bouton ={"Créer","Modifier"};
	        	int retour = r.showOptionDialog(null, "Faite votre choix", "Menu des robots", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.ic, bouton, bouton[0]);
	        	if (retour == 1 ){
	        		JOptionPane.showInputDialog(robot);
	        	}if (retour == 0){
	        		JOptionPane p = new JOptionPane();
	        		JOptionPane.showInputDialog(tab, "Saisissez votre expression");
	        	}
	        	break;
	        
	        
	        case Input.KEY_T: 
	        	
	        	JOptionPane r2 = new JOptionPane(); 
	        	r2.setSize(d);
	        	String[] bouton2 ={"Créer","Modifier"};
	        	int retour2 = r2.showOptionDialog(null, "Faite votre choix", "Menu des robots", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.ic, bouton2, bouton2[0]);
	        	if (retour2 == 1 ){
	        		JOptionPane.showInputDialog(robot2); 
	        	}if (retour2 == 0){
	        		JOptionPane.showInputDialog(tab2, "Saisissez votre expression");
	        	}
	        	break;
	        
	        case Input.KEY_Z:    this.direction2 = 0; this.moving2 = true; break;
	        case Input.KEY_Q:  this.direction2 = 1; this.moving2 = true; break;
	        case Input.KEY_S:  this.direction2 = 2; this.moving2 = true; break;
	        case Input.KEY_D: this.direction2 = 3; this.moving2 = true; break;
	    }
	}

	 public static void main(String[] args) throws SlickException {
		 AppGameContainer game = new AppGameContainer(new View(), 1920, 960, false);
		 //game.setDisplayMode(1920, 1080, true);
		 game.start();
		 //new AppGameContainer(new View(), 1920, 960, false).start();
	 }

}
