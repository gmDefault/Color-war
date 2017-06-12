package machine_a_glace;

public class Joueur extends Entite {
	private int nb_cases_coloriees = 0;

	
	public Joueur(int x, int y, Couleur c,int pdv) {
		super(x, y, c,pdv);
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
	}
	public int getNombre_Case_Coloriees(){
		return nb_cases_coloriees;
	}
	public void Avancer(int pas) {
		
		if(next_case().isAccessible()){
			Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
			Terrain.terrain[getLine()][getCol()].setEntite(null);
			switch (getD()) {
			case Nord:
				setLine(getLine() - pas);
				if (this.getCouleur() == Couleur.Bleu && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					this.nb_cases_coloriees++;
				}
				break;
			case Est:
				setCol(getCol() + pas);
				if (this.getCouleur() == Couleur.Bleu && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					this.nb_cases_coloriees++;
				}
				break;
			case Sud:
				setLine(getLine() + pas);
				if (this.getCouleur() == Couleur.Bleu && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					this.nb_cases_coloriees++;
				}
				break;
			case Ouest:
				setCol(getCol() - pas);
				if (this.getCouleur() == Couleur.Bleu && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge && Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						View.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					this.nb_cases_coloriees++;
				}

			}
			if (Terrain.casexy(getLine(),getCol()).isExpr()){
				super.inventaire().add(Terrain.casexy(getLine(),getCol()).expr());
				Terrain.casexy(getLine(),getCol()).setExpr(null);
			}
			Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
		}
		
	}
	
	public boolean isJoueur(){
		return true;
	}

	public int getNb_cases_coloriees() {
		return nb_cases_coloriees;
	}

	public void setNb_cases_coloriees(int nb_cases_coloriees) {
		this.nb_cases_coloriees = nb_cases_coloriees;
	}

}
