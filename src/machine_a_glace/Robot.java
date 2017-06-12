package machine_a_glace;

public class Robot extends Entite {

	private Node automate;
	private boolean protection = false;

	public Robot(int x, int y, Couleur c, Node auto) {
		super(x, y, c, 20);
		automate = auto;
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
	}

	public void Avancer(int pas) {

		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
		Terrain.terrain[getLine()][getCol()].setEntite(null);
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
		if (Terrain.casexy(getLine(), getCol()).isExpr()) {
			super.inventaire().add(Terrain.casexy(getLine(), getCol()).expr());
			Terrain.casexy(getLine(), getCol()).setExpr(null);
		}
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
	}

	public boolean isRobot() {
		return true;
	}

	public boolean isProtected() {
		return protection;
	}

	public void Kill(int line, int col) {
		Terrain.terrain[line][col].setCase(Contenu.Vide);
		Terrain.terrain[line][col].setEntite(null);
	}

	public void Explosion(int line, int col) {
		int min;
		int max;
		int i, j;
		int borneColG, borneColD, borneLigH, borneLigB;
		int[] borne = new int[4];
		borne = BorneDistance(line, col, 2);
		borneLigH = borne[0];
		borneLigB = borne[1];
		borneColG = borne[2];
		borneColD = borne[3];
		min = borneColG;
		max = borneColD;
		i = 0;
		Terrain.terrain[line][col].setCase(Contenu.Vide); // On tue le robot qui
															// explose
		while (i >= borneLigH) {

			for (j = min; j <= max; j++) {
				if (Terrain.terrain[line + i][col + j].isRobot())
					Kill(line + i, col + j);

				else if (Terrain.terrain[getLine() + i][getCol() + j].isJoueur()) {
					Terrain.terrain[line + i][col + j].getEntite().Degat(40);
				}

			}
			if (min <= 0) {
				min++;
			}
			if (max >= 0) {
				max--;
			}
			i--;
		}
		i = 0;
		min = borneColG;
		max = borneColD;
		i = 1;
		while (i <= borneLigB) {
			for (j = min; j <= max; j++) {
				if (Terrain.terrain[line + i][col + j].isRobot())
					Kill(line + i, col + j);
				else if (Terrain.terrain[getLine() + i][getCol() + j].isJoueur()) {
					Terrain.terrain[line + i][col + j].getEntite().Degat(40);
				}

			}
			if (min <= 0) {
				min++;
			}
			if (max >= 0) {
				max++;
			}
		}
		/*
		 * Penser à mettre à jour le tableau de robot dans l'interface lors de
		 * l'explosion (et l'interface PDV joueur en cas de dégâts).
		 */

	}

	public void Kamikaze() {
		int line, col;
		line = getLine();
		col = getCol();
		boolean present = EnnemiPresentNCase(line, col, 2);
		if (present)
			Explosion(line, col);
	}

	// Renvoi True si l'attaque est concluante, false sinon
	public boolean Attack() {
		int line, col;
		line = getLine();
		col = getCol();
		int taille_t = Terrain.getTaille();
		int coeff_degat = 1;
		Direction d = direction();
		Case case_r;
		switch (d) {
		case Ouest:
			// Attaque l'ennemi en face
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Nord:
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Sud:
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Est:
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(line, col, case_r.getEntite())) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;

		default:

			return false;

		}
	}

	/**
	 * Little save for EnnmiPresentNCase // (Terrain.terrain[line + i][col +
	 * j].isRobot() // || Terrain.terrain[line + i][col + j].isJoueur()) // &&
	 * Terrain.terrain[line + i][col + j].getCouleur() == ennemi
	 * 
	 */
	public boolean EnnemiPresentNCase(int line, int col, int portee) {
		int min;
		int max;
		int i, j;
		int borneColG, borneColD, borneLigH, borneLigB;
		boolean present = false;
		int[] borne = new int[4];
		Case case_r = Terrain.terrain[line][col];
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
				if (case_r.isEnnemi(line, col, Terrain.terrain[line + i][col + j].getEntite()))
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
				if (case_r.isEnnemi(line, col, Terrain.terrain[line + i][col + j].getEntite()))
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

	public int[] BorneDistance(int line, int col, int portee) {
		int[] Borne = new int[4];
		int bound = 0;
		boolean ok = false;
		final int taille = Terrain.getTaille();
		// Cas Haut :
		bound = -portee;
		while (!ok && bound <= 0) {
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

}
