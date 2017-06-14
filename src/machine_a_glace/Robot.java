package machine_a_glace;

public class Robot extends Entite {

	private Node automate;
	private Node etat_courant;
	private int nb_tour;
	private boolean fonctionne = true;
	private boolean isPriorite = false;
	private boolean protection = false;
	private Joueur maitre;

	public Robot(int x, int y, Couleur c, Node auto) {
		super(x, y, c, 30);
		automate = auto;
		etat_courant = automate;
		nb_tour = 0;
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
		next_etat();
	}

	public void execute() {
		switch ((Comportement) etat_courant.Gram) {
		case Attack:
			if (isPriorite)
				fonctionne = Attack();
			else
				Attack();
			break;
		case Explore:
			Explorer();
			break;
		case Kamikaze:
			if (isPriorite)
				fonctionne = Kamikaze();
			else
				Kamikaze();
			break;
		case Protect:
			Protect();
			break;
		default:
			break;
		}

		if (isPriorite && !fonctionne) {
			next_etat();
			execute();

		}
	}

	public void next_etat() {
		next_etat_recur(automate, false);
	}

	private boolean next_etat_recur(Node a, boolean b) {
		if (a != null) {
			if (a.Gram.isComportement() && b) {
				etat_courant = a;
				b = false;
			}else if(a == etat_courant) {
				b = true;
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
				if (!b) {
					b = next_etat_recur(a.FG, b);
					if (!b)
						b = next_etat_recur(a.FD, b);
				} else {
					if (Math.random() < 0.5)
						b = next_etat_recur(a.FG, b);
					else
						b = next_etat_recur(a.FD, b);
				}

			} else if (a.Gram.isOperateur() && a.Gram == Operateur.Priorite) {
				if (b)
					isPriorite = true;
				b = next_etat_recur(a.FG, b);
				if (b)
					isPriorite = false;
				if (b && !fonctionne || !b)
					b = next_etat_recur(a.FD, b);
				if (b)
					isPriorite = false;

			} else {
				b = next_etat_recur(a.FG, b);
				b = next_etat_recur(a.FD, b);
			}

			return b;
		}
		return b;
	}

	public void Avancer(int pas) {
		protection = false;
		if (next_case().isAccessible()) {
			if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
				Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
			Terrain.terrain[getLine()][getCol()].setEntite(null);
			switch (getD()) {
			case Nord:
				setLine(getLine() - pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}

				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}

				}
				break;
			case Est:
				setCol(getCol() + pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				}
				break;
			case Sud:
				setLine(getLine() + pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				}
				break;
			case Ouest:
				setCol(getCol() - pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				}

			}
			if (Terrain.casexy(getLine(), getCol()).isExpr()) {
				maitre.add_inventaire(Terrain.casexy(getLine(), getCol()).expr());
				Terrain.casexy(getLine(), getCol()).setExpr(null);
				Terrain.PutTimer(getLine(), getCol());
			}
			if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
				Terrain.terrain[getLine()][getCol()].setCase(Contenu.Robot);

			Terrain.terrain[getLine()][getCol()].setEntite(this);

		}
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
		this.Kill(line,col); // On tue le robot qui explose
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
				max--;
			}
			i++;
		}
		/*
		 * Penser à mettre à jour le tableau de robot dans l'interface lors de
		 * l'explosion (et l'interface PDV joueur en cas de dégâts).
		 */

	}

	public Node etat_courant() {
		return etat_courant;
	}

	public boolean Kamikaze() {
		int line, col;
		line = getLine();
		col = getCol();

		protection = false;

		boolean present = EnnemiPresentNCase(line, col, 2);
		if (present) {
			Explosion(line, col);
			return true;
		} else
			return false;
	}

	public void Explorer() {
		Direction d;
		int line, col;
		line = getLine();
		col = getCol();
		// Case case_r = Terrain.terrain[line][col];
		d = this.direction();
		double random;
		protection = false;

		if (next_case().isAccessible())
			Avancer(1);
		else {
			random = Math.random();
			if (random < 0.5) {
				TournerGauche();
				if (next_case().isAccessible())
					Avancer(1);
			} else {
				TournerDroite();
				if (next_case().isAccessible())
					Avancer(1);
				else
					TournerDroite();
			}
		}

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
			if (col - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Nord:
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Sud:
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			return false;
		case Est:

			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(this)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					coeff_degat = 1 / 3;
				}
				case_r.getEntite().Degat(30 * coeff_degat);
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(this)) {
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
				if (case_r.isEnnemi(Terrain.terrain[line + i][col + j].getEntite()))
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
				if (case_r.isEnnemi(Terrain.terrain[line + i][col + j].getEntite()))
					present = true;
			}
			if (min <= 0) {
				min++;
			}
			if (max >= 0) {
				max--;
			}
			i++;
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

	public void Protect() {
		protection = true;
	}

	public void setJoueur(Joueur j) {
		maitre = j;
	}
}
