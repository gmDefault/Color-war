package machine_a_glace;

import java.util.ArrayList;

public abstract class Entite {

	private Direction d;
	private int col, line;
	private ArrayList<Operateur> inventaire;
	Couleur couleur;

	public Entite(int x, int y, Couleur c) {
		if (Terrain.terrain[x][y].isAccessible()) {
			setLine(x);
			setCol(y);
			setD(Direction.Nord);
			couleur = c;
			Terrain.terrain[line][col].setCase(Contenu.Joueur);
			inventaire = new ArrayList<Operateur>();

		} else {
			throw new JeuException("entite non creable sur une case non accessible");
		}

	}

	public Direction direction() {
		return getD();
	}

	public ArrayList<Operateur> inventaire() {
		return inventaire;
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

	public void afficher_inventaire() {
		System.out.print("Inventaire ");
		if (isRobot()) {
			System.out.print("robot : ");
		} else {
			System.out.print("joueur : ");
		}

		if (!inventaire.isEmpty()) {
			System.out.print(inventaire.toString());
		}
		System.out.println();

	}
}
