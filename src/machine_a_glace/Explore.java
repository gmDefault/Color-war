package machine_a_glace;

public class Explore extends Comportement {
	
	public static Explore EXPLORE = new Explore("maps/X.png");
	
	private Explore(String s){
		super.image=s;
	}
	
	@Override
	public boolean execute(Robot r) {
		Direction d;
		int line, col;
		line = r.getLine();
		col = r.getCol();
		// Case case_r = Terrain.terrain[line][col];
		d = r.direction();
		double random;
		r.setProtection(false);
		random = Math.random();
		r.RandomChangeDirection();
		if (r.next_case().isAccessible())
			r.Avancer(1);
		else {
			random = Math.random();
			if (random < 0.5) {
				r.TournerGauche();
				if (r.next_case().isAccessible())
					r.Avancer(1);
			} else {
				r.TournerDroite();
				if (r.next_case().isAccessible())
					r.Avancer(1);
				else
					r.TournerDroite();
			}
		}

		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "X";
	}

}
