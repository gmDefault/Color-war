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
		if (isPriorite)
			fonctionne = ((Comportement) etat_courant.Gram).execute(this);
		else
			((Comportement) etat_courant.Gram).execute(this);

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
			} else if (a == etat_courant) {
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
						MapGameState.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}

				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
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
						MapGameState.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
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
						MapGameState.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
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
						MapGameState.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
					}
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer) {
						Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
						maitre.setNb_cases_coloriees(maitre.getNb_cases_coloriees() + 1);
					}
				}

			}
			if (Terrain.casexy(getLine(), getCol()).isExpr()&&maitre.inventaire().size()<30) {
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

	public Node etat_courant() {
		return etat_courant;
	}

	public void setJoueur(Joueur j) {
		maitre = j;
		maitre.add_robot(this);
	}

	public void setProtection(boolean b) {
		protection = b;
	}
	
	public Joueur getMaitre(){
		return maitre;
	}
}

