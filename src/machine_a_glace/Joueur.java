package machine_a_glace;

public class Joueur extends Entite {

	
	public Joueur(int x, int y, Couleur c,int pdv) {
		super(x, y, c,pdv);
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
	}

	public void Avancer(int pas) {
		
		if(next_case().isAccessible()){
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

}
