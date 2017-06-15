package machine_a_glace;

import java.util.ArrayList;
import java.util.Map;

import java.util.LinkedHashMap;

public class Terrain {
	private static int taille = 30;
	public static Case terrain[][] = new Case[taille][taille];
	public static int Nbr_Cp_Op = 30;
	private static ArrayList<Coordonnees> coordonnees_cp_op = new ArrayList<Coordonnees>(Nbr_Cp_Op);
	private static Coordonnees CreationRouge = new Coordonnees(3, 15);
	private static Coordonnees CreationBleu = new Coordonnees(26, 15);
	public static Coordonnees BonusMalus = new Coordonnees(5, 15);
	public static Coordonnees BonusMalu = new Coordonnees(24, 15);
	public static ArrayList<IntCoor> Repop = new ArrayList<IntCoor>(Nbr_Cp_Op);
	public static int Index = 0;

	private Terrain() {

	}

	public static void initialiser() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				Terrain.terrain[i][j] = new Case();
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1)
					terrain[i][j].setCase(Contenu.Obstacle);
			}

		}
		Initialiser_coordonnees_cp_op();
		Initialiser_comportements_operateurs(coordonnees_cp_op);
		Initialiser_cases_creer();
		Initialiser_bonus();

	}

	private static void Initialiser_coordonnees_cp_op() {
		coordonnees_cp_op.add(new Coordonnees(5, 5));
		coordonnees_cp_op.add(new Coordonnees(4, 4));
		coordonnees_cp_op.add(new Coordonnees(5, 25));
		coordonnees_cp_op.add(new Coordonnees(4, 26));
		coordonnees_cp_op.add(new Coordonnees(25, 4));
		coordonnees_cp_op.add(new Coordonnees(24, 5));
		coordonnees_cp_op.add(new Coordonnees(25, 26));
		coordonnees_cp_op.add(new Coordonnees(24, 25));
		coordonnees_cp_op.add(new Coordonnees(18, 28));
		coordonnees_cp_op.add(new Coordonnees(18, 1));
		coordonnees_cp_op.add(new Coordonnees(11, 28));
		coordonnees_cp_op.add(new Coordonnees(11, 1));
		coordonnees_cp_op.add(new Coordonnees(20, 15));
		coordonnees_cp_op.add(new Coordonnees(22, 17));
		coordonnees_cp_op.add(new Coordonnees(22, 13));
		coordonnees_cp_op.add(new Coordonnees(9, 15));
		coordonnees_cp_op.add(new Coordonnees(7, 17));
		coordonnees_cp_op.add(new Coordonnees(7, 13));
		coordonnees_cp_op.add(new Coordonnees(15, 6));
		coordonnees_cp_op.add(new Coordonnees(15, 5));
		coordonnees_cp_op.add(new Coordonnees(14, 6));
		coordonnees_cp_op.add(new Coordonnees(14, 5));
		coordonnees_cp_op.add(new Coordonnees(15, 24));
		coordonnees_cp_op.add(new Coordonnees(15, 23));
		coordonnees_cp_op.add(new Coordonnees(14, 24));
		coordonnees_cp_op.add(new Coordonnees(14, 23));
		coordonnees_cp_op.add(new Coordonnees(1, 1));
		coordonnees_cp_op.add(new Coordonnees(28, 28));
		coordonnees_cp_op.add(new Coordonnees(1, 28));
		coordonnees_cp_op.add(new Coordonnees(28, 1));

	}

	// Fonction qui crée un timer de repop pour une expression qui vient d'être
	// ramassé
	public static void PutTimer(int line, int col,int time) {
		Coordonnees c = new Coordonnees(line, col);
		IntCoor ic = new IntCoor(time, c);
		Repop.add(ic);
		Index++;
	}

	// Fonction qui décompte le temps qu'il reste avant le repop d'un
	// Comportement/Opérateur
	public static boolean ReduceTimer() {
		boolean b = false;
		for (IntCoor i : Repop) {
			i.timer -= 17;
		}
		if (Repop.size() > 0) {
			if (Repop.get(0).timer <= 0) {
				Coordonnees c = Repop.get(0).coord;
				if(( c.getCol()==Terrain.BonusMalu.getCol()&&c.getLigne()==Terrain.BonusMalu.getLigne() )||(c.getCol()==Terrain.BonusMalus.getCol()&&c.getLigne()==Terrain.BonusMalus.getLigne() ) ){
					Terrain.terrain[c.getLigne()][c.getCol()].setCase(Contenu.Bonus_Malus);
					Repop.remove(Repop.get(0));
				}else {
				Expr e = New_Cp_Op();
				while (!e.isOperateur() && !e.isComportement())
					e = New_Cp_Op();
				Terrain.terrain[c.getLigne()][c.getCol()].setCase(Contenu.Expression);
				Terrain.terrain[c.getLigne()][c.getCol()].setExpr(e);
				Repop.remove(Repop.get(0));
				Index--;
				b = true;
			}
			}
		}
		return b;
	}

	public static Expr New_Cp_Op() {
		double rand;
		rand = Math.random();
		if (rand < 0.1)
			return Operateur.Choix;
		else if (rand < 0.2)
			return Operateur.Choixequi;
		else if (rand < 0.3)
			return Operateur.Deuxpoints;
		else if (rand < 0.4)
			return Operateur.PointVirgule;
		else if (rand < 0.5)
			return Operateur.Priorite;
		else if (rand < 0.6)
			return Operateur.Star;
		else if (rand < 0.7)
			return Attack.ATTACK;
		else if (rand < 0.8)
			return Explore.EXPLORE;
		else if (rand < 0.9)
			return Kamikaze.KAMIKAZE;
		else
			return Protect.PROTECT;
	}

	public static void Initialiser_comportements_operateurs(ArrayList<Coordonnees> coord) {
		ArrayList<Operateur> op = new ArrayList<Operateur>((Nbr_Cp_Op / 2) + 1);
		ArrayList<Comportement> cp = new ArrayList<Comportement>((Nbr_Cp_Op / 2) + 1);
		ArrayList<Expr> OpCp = new ArrayList<Expr>(30);
		;
		double rand;
		int indexO = (Nbr_Cp_Op / 2) + 1;
		int indexC = (Nbr_Cp_Op / 2) + 1;
		int taille = (Nbr_Cp_Op / 2) + 1;
		while (taille != 0) {
			rand = Math.random();
			if (rand < 0.2) {
				op.add(Operateur.Choix);
			} else if (rand < 0.4) {
				op.add(Operateur.Choixequi);
			} else if (rand < 0.6) {
				op.add(Operateur.Deuxpoints);
			} else if (rand < 0.8) {
				op.add(Operateur.Priorite);
			} else {
				op.add(Operateur.Star);
			}

			if (rand < 0.3) {
				cp.add(Explore.EXPLORE);
			} else if (rand < 0.53) {
				cp.add(Attack.ATTACK);
			} else if (rand < 0.76) {
				cp.add(Kamikaze.KAMIKAZE);
			} else {
				cp.add(Protect.PROTECT);
			}
			taille--;
		}

		taille = Nbr_Cp_Op;
		while (taille != 0) {

			if (Math.random() > 0.5 && !op.isEmpty()) {
				OpCp.add(op.get(indexO - 1));
				op.remove(indexO - 1);
				indexO--;
			} else if (!cp.isEmpty()) {
				OpCp.add(cp.get(indexC - 1));
				cp.remove(indexC - 1);
				indexC--;
			} else {
				OpCp.add(op.get(indexO - 1));
				op.remove(indexO - 1);
				indexO--;
			}
			taille--;
		}

		int index = coord.size() - 1;
		while (!coord.isEmpty()) {
			
			Terrain.terrain[coord.get(index).getLigne()][coord.get(index).getCol()].setCase(Contenu.Expression);
			Terrain.terrain[coord.get(index).getLigne()][coord.get(index).getCol()].setExpr(OpCp.get(index));
			coord.remove(index);
			index--;
		}

	}

	public static void Initialiser_bonus(){
		int nombreAleatoire = 45000 + (int)(Math.random() * ((115000 - 45000) + 1));

		Terrain.PutTimer(BonusMalus.getLigne(), BonusMalus.getCol(),nombreAleatoire);
		nombreAleatoire = 45000 + (int)(Math.random() * ((115000 - 45000) + 1));
		Terrain.PutTimer(BonusMalu.getLigne(), BonusMalu.getCol(),nombreAleatoire);
	
	}
	public static void Initialiser_cases_creer() {
		Terrain.terrain[CreationBleu.getLigne()][CreationBleu.getCol()].setCase(Contenu.Creer);
		Terrain.terrain[CreationRouge.getLigne()][CreationRouge.getCol()].setCase(Contenu.Creer);
	}

	public static void afficher() {
		for (int i = 0; i < taille; i++) {
			for (Case c : terrain[i]) {
				if (c.isJoueur())
					System.out.print("J");
				else
					System.out.print(c.toString());
				System.out.print("|");

			}
			System.out.println();
			for (int j = 0; j < taille; j++) {
				System.out.print("--");
			}
			System.out.println();
		}
	}

	public static Case casexy(int ligne, int colonne) {
		return terrain[ligne][colonne];
	}

	public static int getTaille() {
		return taille;
	}

	public static Coordonnees spawnRed() {
		if (casexy(4, 15).isAccessible()) {
			return new Coordonnees(4, 15);
		} else if (casexy(3, 16).isAccessible()) {
			return new Coordonnees(3, 16);
		} else if (casexy(3, 14).isAccessible()) {
			return new Coordonnees(3, 14);
		} else {
			int line = 5;
			int col = 14;
			while (!casexy(line, col).isAccessible()) {
				col++;
				if (col == 17) {
					col = 14;
					line++;
				}
			}
			return new Coordonnees(line, col);
		}
	}

	public static Coordonnees spawnBlue() {
		if (casexy(25, 15).isAccessible()) {
			return new Coordonnees(25, 15);
		} else if (casexy(26, 16).isAccessible()) {
			return new Coordonnees(26, 16);
		} else if (casexy(26, 14).isAccessible()) {
			return new Coordonnees(26, 14);
		} else {
			int line = 25;
			int col = 14;
			while (!casexy(line, col).isAccessible()) {
				col++;
				if (col == 17) {
					col = 14;
					line--;
				}
			}
			return new Coordonnees(line, col);
		}
	}
}
