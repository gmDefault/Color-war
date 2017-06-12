package machine_a_glace;

import java.util.ArrayList;

public abstract class Entite {

	private Direction d;
	private int col, line;
	private Couleur couleur;
	int pdv;
	/**
	 * Si ajout des bonus/malus : 
	 * 		Ajout des variables Portée et dégâts
	 * 
	 * Bonus : +1 Portée d'attaque, +X dégâts, + ... ?
	 * Malus : -1 Portée (>0), -X dégâts, poison (3/5/7 ticks)
	 */
	
	
	public Entite(int x, int y, Couleur c, int pointsdv) {
		if (Terrain.terrain[x][y].isAccessible()) {
			setLine(x);
			setCol(y);
			setD(Direction.Nord);
			couleur = c;
			Terrain.terrain[line][col].setCase(Contenu.Joueur);
			pdv = pointsdv;
		} else {
			throw new JeuException("entite non creable sur une case non accessible");
		}

	}

	public Direction direction() {
		return getD();
	}


	public abstract void Avancer(int pas);

	public void Tourner(Direction d) {
		this.setD(d);
	}

	public Case next_case() {
		switch (getD()) {
		case Nord:
			return Terrain.terrain[getLine() - 1][getCol()];
		case Est:
			return Terrain.terrain[getLine()][getCol() + 1];
		case Sud:
			return Terrain.terrain[getLine() + 1][getCol()];
		case Ouest:
			return Terrain.terrain[getLine()][getCol() - 1];
		default:
			return Terrain.terrain[getLine()][getCol()];
		}
	}

	public String toString() {
		return "( " + getLine() + "," + getCol() + " )";
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getCol() {
		return col;
	}
	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pv) {
		this.pdv = pv;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Direction getD() {
		return d;
	}

	public void setD(Direction d) {
		this.d = d;
	}

	public boolean isJoueur() {
		return false;
	}

	public boolean isRobot() {
		return false;
	}

	
	
	public Couleur getCouleur(){
		return couleur;
	}
	
	public void Degat(int x){
			pdv -= x;
	}
	
	public Robot robot(){
		return (Robot) this;
	}
	
	public void ChangerDirection(Direction d){
		this.d = d;
	}
	
}
