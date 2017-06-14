package machine_a_glace;

import java.io.*;

public class Sauvegarde {

	public static void Writer() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("w4jr1krkd1042kd42.txt")));
			WrtMap(writer);
			BufferedReader reader = new BufferedReader(new FileReader(new File("w4jr1krkd1042kd42.txt")));
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void WrtMap(BufferedWriter wrt) throws IOException {
		int i, j,k;
		int taille;
		String s;
		Case case_r;
		Joueur[] player = new Joueur[2];
		taille = Terrain.getTaille();
		k = 0;
		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				case_r = Terrain.terrain[i][j];
				try {
					wrt.write(case_r.ContenuToString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			wrt.write("\n");
		}
		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				case_r = Terrain.terrain[i][j];
				try {
					if (case_r.isExpr()) {
						wrt.write(case_r.expr().toString());
						wrt.write(" ");
					}
					else if(case_r.isJoueur()){
						player[k] = (Joueur) case_r.getEntite();
						k++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		wrt.write("\n");
		for(k=0;k<2;k++){
			wrt.write(player[k].getLine() + " " + player[k].getCol() + " " + player[k].getD().toString() + " " + player[k].getCouleur().toString() + " " + player[k].getPdv() + " " + player[k].getNrj());
			for(Expr inv : player[k].inventaire()){
				wrt.write(inv.toString());
			}
			wrt.write("\n");
			
		}
		Terrain.Repop.add(new IntCoor(3, new Coordonnees(3,4)));
		Terrain.Repop.add(new IntCoor(2, new Coordonnees(1,1)));
		for(IntCoor rep : Terrain.Repop){
			Coordonnees x;
			x = rep.coord;
			wrt.write(x.getLigne() + " " + x.getCol() + " " + rep.timer);
			wrt.write("\n");
		}
		
	}
}
