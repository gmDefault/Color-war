package machine_a_glace;

import java.util.concurrent.TimeUnit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ColorWar {

	 public static void main(String[] args) throws SlickException {
		 AppGameContainer game = new AppGameContainer(new View(), 1920, 960, false);
		 //game.setFullscreen(true);
		 //game.setDisplayMode(1920, 1080, true);
		 game.start();
		 //new AppGameContainer(new View(), 1920, 960, false).start();
	 }
}