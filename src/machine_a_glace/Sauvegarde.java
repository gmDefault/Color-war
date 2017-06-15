package machine_a_glace;

import java.io.*;
import java.util.Spliterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Sauvegarde {

	public static void Writer(int min, int sec) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("w4jr1krkd1042kd42.txt")));
			WrtMap(writer, min, sec);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String Reader() {
		String str = "";
		String str_1, str_2 = "";
		try {
			MapGameState.allrobots.clear();
			MapGameState.canmoverobots.clear();
			BufferedReader reader = new BufferedReader(new FileReader(new File("w4jr1krkd1042kd42.txt")));
			ReplacePlayer(reader);
			str = reader.readLine();
			ReplaceRobots(reader);
			ReplaceColor(reader);
			str_1 = reader.readLine();
			str_2 = reader.readLine();
			ReplaceExpr(str_1, str_2);
			ReplaceRepop(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void ReplaceRobots(BufferedReader rdr) throws IOException {
		String str;
		String[] inter;
		int i, j;
		int line, col, nbr, pdv;
		Case case_r;
		Node n;
		Direction d = Direction.Nord;
		Couleur c = Couleur.Neutre;
		Robot r;
		str = rdr.readLine();
		nbr = Integer.parseInt(str);
		for (i = 0; i < nbr; i++) {
			str = rdr.readLine();
			inter = str.split(" ");
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
			pdv = Integer.parseInt(inter[3]);
			switch (inter[4]) {
			case "B":
				c = Couleur.Bleu;
				break;
			case "R":
				c = Couleur.Rouge;
				break;
			}
			case_r = Terrain.terrain[line][col];
			if (c == Couleur.Neutre) {
				throw new JeuException("Couleur du robot non initialisé");
			}
			n = Parser.ExpressionCorrecte1(inter[5]);
			r = new Robot(line, col, c, n);
			switch (c) {
			case Rouge:
				r.setJoueur(MapGameState.j2);
				break;
			case Bleu:
				r.setJoueur(MapGameState.j1);
				break;
			default:
				throw new JeuException();
			}
			r.setD(d);
			r.setPdv(pdv);
			case_r.setEntite(r);
			case_r.setCase(Contenu.Robot);
			MapGameState.canmoverobots.add(true);
			MapGameState.automaterobot.add(inter[5]);
			MapGameState.allrobots.add(r);
			// r.modificationRobot(n);

		}
	}

	public static void ReplaceRepop(BufferedReader rdr) throws IOException {
		String str;
		String[] inter;
		int line, col, timer;
		int nbr;
		int i = 0;
		IntCoor intco;
		str = rdr.readLine();
		System.out.println(str);
		nbr = Integer.parseInt(str);
		Terrain.Repop.clear();
		for (i = 0; i < nbr; i++) {
			str = rdr.readLine();
			inter = str.split(" ");
			line = Integer.parseInt(inter[0]);
			col = Integer.parseInt(inter[1]);
			timer = Integer.parseInt(inter[2]);
			intco = new IntCoor(timer, new Coordonnees(line, col));
			Terrain.Repop.add(intco);
		}
	}

	public static void ReplacePlayer(BufferedReader rdr) throws IOException {
		String s;
		String[] inter, invent;
		invent = null;
		int i, j;
		Joueur p1 = null, p2 = null;
		int line, col, pdv, nrj, nbcasecol, nbitem;
		int colact, lineact;
		Case case_r;
		// new AppGameContainer(new ColorWar(), 1920, 960, false).start();
		Direction d = Direction.Nord;
		Couleur c = Couleur.Neutre;
		for (i = 0; i < 2; i++) {
			s = rdr.readLine();
			inter = s.split(" ");
			line = Integer.parseInt(inter[0]);
			col = Integer.parseInt(inter[1]);
			System.out.println(line + " " + col);
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
			nbcasecol = Integer.parseInt(inter[6]);
			nbitem = Integer.parseInt(inter[7]);
			if (nbitem != 0) {
				s = rdr.readLine();
				invent = s.split(" ");
			}
			case_r = Terrain.terrain[line][col];
			if (i == 0) {
				lineact = MapGameState.j1.getLine();
				colact = MapGameState.j1.getCol();
				Terrain.terrain[lineact][colact].setCase(Contenu.Vide);
				MapGameState.j1.setPdv(pdv);
				;
				MapGameState.j1.SetNrj(nrj);
				MapGameState.j1.setD(d);
				MapGameState.j1.setLine(line);
				MapGameState.j1.setCol(col);
				MapGameState.j1.setNb_cases_coloriees(nbcasecol);
				Terrain.terrain[lineact][colact].setEntite(null);
				Terrain.terrain[line][col].setCase(Contenu.Joueur);
				Terrain.terrain[line][col].setEntite(MapGameState.j1);
				MapGameState.j1.tabinv.clear();
				MapGameState.j1.inventaire().clear();
				for (j = 0; j < nbitem; j++) {
					MapGameState.j1.tabinv.add(invent[j]);
					switch (invent[j]) {
					case "/":
						MapGameState.j1.inventaire().add(Operateur.Choix);
						break;
					case ";":
						MapGameState.j1.inventaire().add(Operateur.PointVirgule);
						break;
					case ">":
						MapGameState.j1.inventaire().add(Operateur.Priorite);
						break;
					case ":":
						MapGameState.j1.inventaire().add(Operateur.Deuxpoints);
						break;
					case "|":
						MapGameState.j1.inventaire().add(Operateur.Choixequi);
						break;
					case "*":
						MapGameState.j1.inventaire().add(Operateur.Star);
						break;
					case "K":
						MapGameState.j1.inventaire().add(Kamikaze.KAMIKAZE);
						break;
					case "X":
						MapGameState.j1.inventaire().add(Explore.EXPLORE);
						break;
					case "A":
						MapGameState.j1.inventaire().add(Attack.ATTACK);
						break;
					case "P":
						MapGameState.j1.inventaire().add(Protect.PROTECT);
						break;
					}

				}
			}
			if (i == 1) {
				lineact = MapGameState.j2.getLine();
				colact = MapGameState.j2.getCol();
				Terrain.terrain[lineact][colact].setCase(Contenu.Vide);
				MapGameState.j2.setPdv(pdv);
				;
				MapGameState.j2.SetNrj(nrj);
				MapGameState.j2.setD(d);
				MapGameState.j2.setLine(line);
				MapGameState.j2.setCol(col);
				Terrain.terrain[lineact][colact].setEntite(null);
				Terrain.terrain[line][col].setCase(Contenu.Joueur);
				Terrain.terrain[line][col].setEntite(MapGameState.j2);
				MapGameState.j2.tabinv.clear();
				MapGameState.j2.inventaire().clear();
				for (j = 0; j < nbitem; j++) {
					MapGameState.j2.tabinv.add(invent[j]);
					switch (invent[j]) {
					case "/":
						MapGameState.j2.inventaire().add(Operateur.Choix);
						break;
					case ";":
						MapGameState.j2.inventaire().add(Operateur.PointVirgule);
						break;
					case ">":
						MapGameState.j2.inventaire().add(Operateur.Priorite);
						break;
					case ":":
						MapGameState.j2.inventaire().add(Operateur.Deuxpoints);
						break;
					case "|":
						MapGameState.j2.inventaire().add(Operateur.Choixequi);
						break;
					case "*":
						MapGameState.j2.inventaire().add(Operateur.Star);
						break;
					case "K":
						MapGameState.j2.inventaire().add(Kamikaze.KAMIKAZE);
						break;
					case "X":
						MapGameState.j2.inventaire().add(Explore.EXPLORE);
						break;
					case "A":
						MapGameState.j2.inventaire().add(Attack.ATTACK);
						break;
					case "P":
						MapGameState.j2.inventaire().add(Protect.PROTECT);
						break;
					}
				}
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

	public static void WrtMap(BufferedWriter wrt, int min, int sec) throws IOException {
		int i, j, k, l, m;
		int countExpr = 0;
		int taille;
		String s;
		Case case_r;
		Joueur[] player = new Joueur[2];
		Coordonnees[] expr = new Coordonnees[30];
		String[] auto = new String[8];
		taille = Terrain.getTaille();
		k = 0;
		l = 0;
		m = 0;
		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				case_r = Terrain.terrain[i][j];
				if (case_r.isJoueur()) {
					player[k] = (Joueur) case_r.getEntite();
					k++;
				}
			}
		}
		for (k = 0; k < 2; k++) {
			wrt.write(player[k].getLine() + " " + player[k].getCol() + " " + player[k].getD().toString() + " "
					+ player[k].getCouleur().toString() + " " + player[k].getPdv() + " " + player[k].getNrj() + " "
					+ player[k].getNombre_Case_Coloriees() + " " + player[k].inventaire().size());

			if (player[k].inventaire().size() != 0) {
				wrt.write("\n");
			}
			for (Expr inv : player[k].inventaire()) {
				wrt.write(inv.toString() + " ");
			}
			wrt.write("\n");
		}
		for (String rob : MapGameState.automaterobot) {
			auto[m] = rob;
			m++;
		}
		wrt.write(min + " " + sec);
		wrt.write("\n" + MapGameState.allrobots.size());
		m = 0;
		for (Robot rob : MapGameState.allrobots) {
			wrt.write("\n" + rob.getLine() + " " + rob.getCol() + " " + rob.getD() + " " + rob.getPdv() + " "
					+ rob.getCouleur().toString() + " " + auto[m++]);
		}

		wrt.write("\n");
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
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		wrt.write("\n" + countExpr + "\n" + Terrain.Repop.size() + "\n");

		for (IntCoor rep : Terrain.Repop) {
			Coordonnees x;
			x = rep.coord;
			wrt.write(x.getLigne() + " " + x.getCol() + " " + rep.timer);
			wrt.write("\n");
		}

	}
}
