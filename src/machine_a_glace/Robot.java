package machine_a_glace;

public class Robot extends Entite {

	private Node automate;

	public Robot(int x, int y, Couleur c, Node auto) {
		super(x, y, c);
		automate = auto;
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
	}

	public void Avancer(int pas) {

		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
		switch (getD()) {
		case Nord:
			setLine(getLine() - pas);
			break;
		case Est:
			setCol(getCol() + pas);
			break;
		case Sud:
			setLine(getLine() + pas);
			break;
		case Ouest:
			setCol(getCol() - pas);

		}
		if (Terrain.casexy(getLine(), getCol()).isOperateur()) {
			super.inventaire().add(Terrain.casexy(getLine(), getCol()).op());
			Terrain.casexy(getLine(), getCol()).setOp(null);
		}
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
	}

	public boolean isRobot() {
		return true;
	}

	public void Kill(int line, int col) {
		Terrain.terrain[line][col].setCase(Contenu.Vide);
	}

	public void Kill2Case(int line, int col) {

	}

	public boolean EnnemiPresentNCase(int line, int col, int portee, Couleur ennemi) {
		int min;
		int max;
		int i, j;
		int borneColG, borneColD, borneLigH, borneLigB;
		boolean present = false;
		int[] borne = new int[4];
		borne = BorneDistance(line, col, portee);
		borneLigH = borne[0];
		borneLigB = borne[1];
		borneColG = borne[2];
		borneColD = borne[3];
		min = borneColG;
		max = borneColD;
		i = 0;
		while (i >= borneLigH && present == false) {

			for (j = min; j <= max; j++) {
				if (Terrain.terrain[getLine() + i][getCol() + j].isRobot()
						&& Terrain.terrain[getLine() + i][getCol() + j].getCouleur() == ennemi)
					present = true;
			}
			if (min <= 0) {
				min++;
			}
			if (max >= 0) {
				max--;
			}
			i--;
		}
		min = borneColG;
		max = borneColD;
		i = 1;
		while (i <= borneLigB && present == false) {
			for (j = min; j <= max; j++) {
				if (Terrain.terrain[getLine() + i][getCol() + j].isRobot()
						&& Terrain.terrain[getLine() + i][getCol() + j].getCouleur() == ennemi)
					present = true;
			}
			if (min <= 0) {
				min++;
			}
			if (max >= 0) {
				max++;
			}
		}
		return present;
	}

	/**
	 * WORK IN PROGRESS
	 */

	public int[] BorneDistance(int line, int col, int portee) {
		int[] Borne = new int[4];
		int bound = 0;
		int i = 0;
		boolean ok = false;
		final int taille = Terrain.getTaille();
		// Cas Haut :
		bound = -portee;
		while (!ok && bound <= 0) {
//			System.out.print(line+bound);
			if (line + bound >= 0 && line + bound < taille) {
				ok = true;
				Borne[0] = bound;
			}
			bound++;
		}
		ok = false;
		bound = portee;

		// Cas Bas :
		while (!ok && bound >= 0) {
			if (line + bound >= 0 && line + bound < taille) {
				ok = true;
				Borne[1] = bound;
			}
			bound--;
		}
		ok = false;
		bound = -portee;

		// Cas Gauche
		while (!ok && bound <= 0) {
			if (col + bound >= 0 && col + bound < taille) {
				ok = true;
				Borne[2] = bound;
			}
			bound++;
		}
		ok = false;
		bound = portee;

		// Cas droite
		while (!ok && bound >= 0) {
			if (col + bound >= 0 && col + bound < taille) {
				ok = true;
				Borne[3] = bound;
			}
			bound--;
		}
		return Borne;
	}
	
	
	/**
	 * Autre implémentation possible avec minimum et maximum pour ligne et pour
	 * colonne
	 */
}
