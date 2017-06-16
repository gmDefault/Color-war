package machine_a_glace;

import java.util.ArrayList;

public class BonusMalus {
	
	
	public static void inversionCouleur(Joueur j1, Joueur j2){
		int a = j1.getNb_cases_coloriees();
		j1.setNb_cases_coloriees(j2.getNb_cases_coloriees());
		j2.setNb_cases_coloriees(a);
		for (int i = 0; i< 30; i++) {
			for (int j = 0; j < 30; j++) {
				if (Terrain.terrain[i][j].getCouleur() == Couleur.Bleu) {
					Terrain.terrain[i][j].setCouleur(Couleur.Rouge);
				} else if (Terrain.terrain[i][j].getCouleur() == Couleur.Rouge) {
					Terrain.terrain[i][j].setCouleur(Couleur.Bleu);
				}
			}
		}
	}
	
	public static void inversionInventaire(Joueur j1, Joueur j2){
		ArrayList<Expr> g = (ArrayList<Expr>)j1.inventaire().clone();
		j1.inventaire().clear();
		j1.inventaire().addAll(j2.inventaire());
		j2.inventaire().clear();
		j2.inventaire().addAll(g);
	}
	
	public static void pvAdd(Joueur j1,Joueur j2){
		double a = Math.random();
		if (a<=0.5){
			if (j1.getPdv()<=85) {
				j1.setPdv(j1.getPdv()+15);
			}else{
				j1.setPdv(100);
			}
		}else{
			if (j2.getPdv()<=85) {
				j2.setPdv(j2.getPdv()+15);
			}else{
				j1.setPdv(100);
			}
		}
		
	}
	
	public static void pvLost(Joueur j1,Joueur j2){
		double a = Math.random();
		if (a<=0.5){
			j1.setPdv(j1.getPdv()-15);
		}else{
			j2.setPdv(j2.getPdv()-15);
		}
	}
	public static void swapPlayer(Joueur j1, Joueur j2){
		int l = j1.getLine();
		int c = j1.getCol();
		j1.setCol(j2.getCol());
		j1.setLine(j2.getLine());
		Terrain.terrain[j2.getLine()][j2.getCol()].setEntite(j1);
		j2.setCol(c);
		j2.setLine(l);
		Terrain.terrain[l][c].setEntite(j2);
	}
}
