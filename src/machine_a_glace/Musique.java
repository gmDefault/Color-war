package machine_a_glace;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Musique {
	private Music musique;
	public  Musique() {
		try {
			this.musique = new Music("maps/Rainshadow.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		musique.loop();
	}
	public void restart() {
		musique.resume();
	}
}
