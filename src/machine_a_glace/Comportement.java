package machine_a_glace;

public abstract class Comportement implements Expr {
	
	protected String image;

	public boolean isComportement() {
		return true;
	}

	public boolean isOperateur() {
		return false;
	}
	
	public boolean isChiffre(){
		return false;
	}
	
	public abstract boolean execute(Robot r);
	
	public abstract String toString();

}
