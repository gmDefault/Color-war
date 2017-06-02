package machine_a_glace;

public abstract class Entite {

	private Direction d;
	private int col, line;
	Couleur couleur;

	public Entite(int x, int y, Couleur c) {
		if (Terrain.terrain[x][y].isAccessible()) {
			setLine(x);
			setCol(y);
			setD(Direction.Nord);
			couleur = c;

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

	public void setCol(int col) {
		this.col = col;
	}

	public Direction getD() {
		return d;
	}

	public void setD(Direction d) {
		this.d = d;
	}
}
