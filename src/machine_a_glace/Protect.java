package machine_a_glace;

public class Protect extends Comportement {
	
	public static Protect PROTECT = new Protect("maps/P.png");
	
	private Protect(String s){
		super.image=s;
	}
	
	@Override
	public boolean execute(Robot r) {
		// TODO Auto-generated method stub
		r.setProtection(true);
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "P";
	}

}
