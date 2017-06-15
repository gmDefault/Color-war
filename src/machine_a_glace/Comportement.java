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


	public void Kill(int line, int col, Robot r) {
		Terrain.terrain[line][col].setCase(Contenu.Vide);
		Terrain.terrain[line][col].setEntite(null);
		
		r.getMaitre().remove_robot(r);
		
	}
}
