package machine_a_glace;

import java.io.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Sauvegarde {

	public static void Writer(int min, int sec) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("w4jr1krkd1042kd42.txt")));
			writer.write(min + " " + sec + "\n");
			WrtMap(writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String Reader() {
		String str = "";
		String str_1, str_2 = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("w4jr1krkd1042kd42.txt")));
			str = reader.readLine();
			ReplaceColor(reader);
			str_1 = reader.readLine();
			str_2 = reader.readLine();
			ReplaceExpr(str_1, str_2);
			ReplacePlayer(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void ReplacePlayer(BufferedReader rdr) throws IOException {
		String s;
		String[] inter;
		int i;
		Joueur j1 = null, j2 = null;
		int line, col, pdv, nrj;
		Case case_r;
		// new AppGameContainer(new ColorWar(), 1920, 960, false).start();
		Direction d = Direction.Nord;
		Couleur c = Couleur.Neutre;
		for (i = 0; i < 2; i++) {
			s = rdr.readLine();
			inter = s.split(" ");
			line = Integer.parseInt(inter[0]);
			col = Integer.parseInt(inter[1]);
			switch (inter[2]) {
			case "Ouest":
				d = Direction.Ouest;
				break;
			case "Nord":
				d = Direction.Nord;
				break;
			case "Sud":
				d = Direction.Sud;
				break;
			case "Est":
				d = Direction.Est;
				break;
			}
			switch (inter[3]) {
			case "B":
				c = Couleur.Bleu;
				break;
			case "R":
				c = Couleur.Rouge;
				break;
			}
			pdv = Integer.parseInt(inter[4]);
			nrj = Integer.parseInt(inter[5]);
			case_r = Terrain.terrain[line][col];
			if (i == 0) {
				j1 = new Joueur(line, col, c, d, pdv, nrj);
				MapGameState.j1 = j1;
			}
			if (i == 1) {
				j1 = new Joueur(line, col, c, d, pdv, nrj);
				MapGameState.j2 = j2;
			}
		}

	}

	public static void ReplaceColor(BufferedReader rdr) throws IOException {
		int i, j;
		int taille = Terrain.getTaille();
		String s;
		String[] inter;

		for (i = 0; i < taille; i++) {
			s = rdr.readLine();
			inter = s.split(" ");
			for (j = 0; j < taille; j++) {
				switch (inter[j]) {
				case "B":
					Terrain.terrain[i][j].setCouleur(Couleur.Bleu);
					break;
				case "R":
					Terrain.terrain[i][j].setCouleur(Couleur.Rouge);
					break;
				case "N":
					Terrain.terrain[i][j].setCouleur(Couleur.Neutre);
					break;
				}
			}
		}
	}

	public static void ReplaceExpr(String s1, String s2) {
		int i;
		int line, col;
		Case case_r;
		String[] expr = s1.split(" ");
		int nb_expr = Integer.parseInt(s2);
		for (i = 0; i < nb_expr * 3; i += 3) {
			line = Integer.parseInt(expr[i]);
			col = Integer.parseInt(expr[i + 1]);
			case_r = Terrain.terrain[line][col];
			case_r.setCase(Contenu.Expression);
			switch (expr[i + 2]) {
			case "/":
				case_r.setExpr(Operateur.Choix);
				break;
			case ";":
				case_r.setExpr(Operateur.PointVirgule);
				break;
			case ">":
				case_r.setExpr(Operateur.Priorite);
				break;
			case ":":
				case_r.setExpr(Operateur.Deuxpoints);
				break;
			case "|":
				case_r.setExpr(Operateur.Choixequi);
				break;
			case "*":
				case_r.setExpr(Operateur.Star);
				break;
			case "K":
				case_r.setExpr(Kamikaze.KAMIKAZE);
				break;
			case "X":
				case_r.setExpr(Explore.EXPLORE);
				break;
			case "A":
				case_r.setExpr(Attack.ATTACK);
				break;
			case "P":
				case_r.setExpr(Protect.PROTECT);
				break;
			}
		}

	}

	public static void WrtMap(BufferedWriter wrt) throws IOException {
		int i, j, k, l;
		int countExpr = 0;
		int taille;
		String s;
		Case case_r;
		Joueur[] player = new Joueur[2];
		Coordonnees[] expr = new Coordonnees[30];
		taille = Terrain.getTaille();
		k = 0;
		l = 0;
		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				case_r = Terrain.terrain[i][j];
				try {
					wrt.write(case_r.getCouleur().toString() + " ");
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
						countExpr++;
						wrt.write(i + " " + j + " " + case_r.expr().toString());
						wrt.write(" ");
					} else if (case_r.isJoueur()) {
						player[k] = (Joueur) case_r.getEntite();
						k++;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		wrt.write("\n" + countExpr);
		wrt.write("\n");
		for (k = 0; k < 2; k++) {
			wrt.write(player[k].getLine() + " " + player[k].getCol() + " " + player[k].getD().toString() + " "
					+ player[k].getCouleur().toString() + " " + player[k].getPdv() + " " + player[k].getNrj() + " ");

			for (Expr inv : player[k].inventaire()) {
				wrt.write(inv.toString() + " ");
			}
			wrt.write("\n");
		}

		for (IntCoor rep : Terrain.Repop) {
			Coordonnees x;
			x = rep.coord;
			wrt.write(x.getLigne() + " " + x.getCol() + " " + rep.timer);
			wrt.write("\n");
		}

	}
}
