package machine_a_glace;

public class Robot extends Entite {

	private Node automate;
	private Node etat_courant;
	private int nb_tour;
	private boolean fonctionne;
	private boolean isPriorite=false;

	public Robot(int x, int y, Couleur c, Node auto) {
		super(x, y, c, 20);
		automate = auto;
		etat_courant = automate;
		nb_tour = 0;
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
		Terrain.terrain[getLine()][getCol()].setEntite(this);

	}

	public void next_etat() {
		next_etat_recur(automate, false);
	}

	private boolean next_etat_recur(Node a, boolean b) {
		if (a != null) {
			if (a == etat_courant) {
				b = true;
			} else if (a.Gram.isComportement() && b) {
				etat_courant = a;
				b = false;
			}

			if (a.Gram.isOperateur() && a.Gram == Operateur.Star) {
				b = next_etat_recur(a.FD, b);
				if (b) {
					nb_tour++;
					next_etat_recur(a.FD, b);
				}

			} else if (a.Gram.isOperateur() && a.Gram == Operateur.Choixequi) {
				if (nb_tour % 2 == 0)
					b = next_etat_recur(a.FG, b);
				else
					b = next_etat_recur(a.FD, b);
			} else if (a.Gram.isOperateur() && a.Gram == Operateur.Choix) {
				if (Math.random() < 0.5)
					b = next_etat_recur(a.FG, b);
				else
					b = next_etat_recur(a.FD, b);
			} else if (a.Gram.isOperateur() && a.Gram == Operateur.Priorite) {
				if(b) isPriorite = true;
				b = next_etat_recur(a.FG, b);
				if(b) isPriorite = true;
				if (b && !fonctionne || !b)
					b = next_etat_recur(a.FD, b);
				if(b) isPriorite = false;
				 
			} else {
				b = next_etat_recur(a.FG, b);
				b = next_etat_recur(a.FD, b);
			}

			return b;
		}
		return b;
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

	// public boolean isEnnemi(int line, int col, Entite ent) {
	// if(ent.getCouleur() != getCol()){
	//
	// }
	// return ;
	// }

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
		boolean present = EnnemiPresentNCase(line, col, 2, Terrain.terrain[line][col].getCouleurInverse());
		if (present)
			Explosion(line, col);
	}

	// Renvoi True si l'attaque est concluante, false sinon
	public boolean Attack() {
		/**
		 * Possibilité de rajouter un paramètre entité afin de ne pas check à
		 * chaque fois le tableau. Choix.
		 */
		int line, col;
		line = getLine();
		col = getCol();
		int taille_t = Terrain.getTaille();
		Direction d = direction();
		switch (d) {

		case Ouest:
			if (col - 1 > 0) {
				Terrain.terrain[line][col - 1].getEntite().Degat(30);
				return true;
			} else
				return false;
		case Nord:
			if (line - 1 > 0) {
				Terrain.terrain[line - 1][col].getEntite().Degat(30);
				return true;
			} else
				return false;
		case Sud:
			if (line + 1 < taille_t) {
				Terrain.terrain[line + 1][col].getEntite().Degat(30);
				return true;
			} else
				return false;
		case Est:
			if (col + 1 < taille_t) {
				Terrain.terrain[line][col + 1].getEntite().Degat(30);
				return true;
			} else
				return false;

		default:

			return false;

		}
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
				if ((Terrain.terrain[getLine() + i][getCol() + j].isRobot()
						|| Terrain.terrain[getLine() + i][getCol() + j].isJoueur())
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
