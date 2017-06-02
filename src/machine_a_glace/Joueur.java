package machine_a_glace;

public class Joueur extends Entite {

	public Joueur(int x, int y, Couleur c) {
		super(x, y, c);
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
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
		if (Terrain.casexy(getLine(),getCol()).isOperateur()){
			super.inventaire().add(Terrain.casexy(getLine(),getCol()).op());
			Terrain.casexy(getLine(),getCol()).setOp(null);
		}
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
	}
	
	public boolean isJoueur(){
		return true;
	}

}
