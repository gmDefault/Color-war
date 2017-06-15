package machine_a_glace;

import java.util.ArrayList;

public class Joueur extends Entite {
	private int nb_cases_coloriees = 0;
	private int nrj;
	private int nb_robot = 0;
	
	private ArrayList<Expr> inventaire;
	public ArrayList<String> tabinv = new ArrayList<String>();
	private ArrayList<Robot> robots = new ArrayList<Robot>();

	public Joueur(int x, int y, Couleur c, int pdv,int nrj) {
		super(x, y, c, pdv);
		this.nrj=nrj;
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
		inventaire = new ArrayList<Expr>();
	}
	
	public Joueur(int x, int y, Couleur c, Direction d, int pdv,int nrj) {
		super(x, y, c, pdv);
		this.nrj=nrj;
		this.setD(d);
		Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
		Terrain.terrain[getLine()][getCol()].setEntite(this);
		inventaire = new ArrayList<Expr>();
	}

	public int getNombre_Case_Coloriees() {
		return nb_cases_coloriees;
	}
	
	public int getNrj() {
		return nrj;
	}
	public int getrb(){
		return nb_robot;
	}
	public void setRb(int n){
		nb_robot = n;
	}
	public void SetNrj(int n) {
		 nrj=n;
	}

	public boolean isNrj(){
		return nrj>=70;
		
	}


	public void Avancer(int pas) {
//		Terrain.afficher();
		if (next_case().isAccessible()) {
			if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer){
				Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
			}
			Terrain.terrain[getLine()][getCol()].setEntite(null);
			switch (getD()) {
			case Nord:
				setLine(getLine() - pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge ) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				}
				
				break;
			case Est:
				setCol(getCol() + pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				}
				break;
			case Sud:
				setLine(getLine() + pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				}
				break;
			case Ouest:
				setCol(getCol() - pas);
				if (this.getCouleur() == Couleur.Bleu
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Bleu) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Rouge) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Bleu);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				} else if (this.getCouleur() == Couleur.Rouge
						&& Terrain.terrain[getLine()][getCol()].getCouleur() != Couleur.Rouge) {
					if (Terrain.terrain[getLine()][getCol()].getCouleur() == Couleur.Bleu) {
						MapGameState.recolorie_par_dessus = true;
					}
					Terrain.terrain[getLine()][getCol()].setCouleur(Couleur.Rouge);
					if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
					this.nb_cases_coloriees++;
				}

			}
			if (Terrain.terrain[getLine()][getCol()].getCont() == Contenu.Bonus_Malus) {
				Terrain.terrain[getLine()][getCol()].setCase(Contenu.Vide);
				MapGameState.bonus_malus = true;
				double rand = 45000 + (int)(Math.random() * (115000 - 45000 -1));
				Terrain.PutTimer(getLine(), getCol(),(int)rand);
				
				
			}
			if (Terrain.casexy(getLine(), getCol()).isExpr()&&inventaire.size()<30) {
				
				inventaire().add(Terrain.casexy(getLine(), getCol()).expr());
				Terrain.casexy(getLine(), getCol()).setExpr(null);
				Terrain.PutTimer(getLine(), getCol(),30000);
			}else if (Terrain.casexy(getLine(), getCol()).isExpr()){
				Terrain.PutTimer(getLine(), getCol(),30000);
			}
			
			
			if (Terrain.terrain[getLine()][getCol()].getCont() != Contenu.Creer)
				Terrain.terrain[getLine()][getCol()].setCase(Contenu.Joueur);
			
			Terrain.terrain[getLine()][getCol()].setEntite(this);
			

			
		
		}

	}

	public boolean isJoueur() {
		return true;
	}

	public int getNb_cases_coloriees() {
		return nb_cases_coloriees;
	}

	public void setNb_cases_coloriees(int nb_cases_coloriees) {
		this.nb_cases_coloriees = nb_cases_coloriees;
	}
	

	public ArrayList<Expr> inventaire() {
		return inventaire;
	}
	
	public void add_inventaire(Expr e){
	
		inventaire.add(e);
	}
	
	public String toString(int j){
		return inventaire.get(j).toString();
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
	public ArrayList<String> inventaire_toString(){
		
		int i = inventaire.size();
		for (int j = 0; j<i ; j++){
			tabinv.add(toString(j));
		}
		
		return tabinv;
	}
	
	public void add_robot(Robot r){
		robots.add(r);
	}
	
	public void remove_robot(Robot r){
		robots.remove(r);
	}
	
	public ArrayList<Robot> robots(){
		return robots;
	}
	
	public String[] arrayRobotExptoString(){
		String[] robot = new String[robots.size()];
		for (int i = 0;i<robots.size();i++){
			robot[i] = robots.get(i).getAutomate().toString();
		}
		return robot;
	}
	
	public String[] arrayRobottoString(){
		String[] robot = new String[robots.size()];
		for (int i = 0;i<robots.size();i++){
			robot[i] = "robot "+ (i+1) + " => " + robots.get(i).getAutomate().toString() ;
		}
		return robot;
	}

}
