package machine_a_glace;

import java.util.ArrayList;

public class Entite {

	private Direction d;
	private int col, line;
	private ArrayList<Operateur> inventaire;

	public Entite(int x, int y) {
		if (Terrain.terrain[x][y].isAccessible()) {
			line = x;
			col = y;
			d = Direction.Nord;
			Terrain.terrain[line][col].setCase(Contenu.Joueur);
			inventaire = new ArrayList<Operateur>();
		} else {
			throw new JeuException("entite non creable sur une case non accessible");
		}

	}

	public Direction direction() {
		return d;
	}

	public void Avancer(int pas) {
		Terrain.terrain[line][col].setCase(Contenu.Vide);
		switch (d) {
		case Nord:
			line -= pas;
			break;
		case Est:
			col += pas;
			break;
		case Sud:
			line += pas;
			break;
		case Ouest:
			col -= pas;

		}
		if (Terrain.casexy(line,col).isOperateur()){
			inventaire.add(Terrain.casexy(line,col).op());
			Terrain.casexy(line,col).setOp(null);
		}
		Terrain.terrain[line][col].setCase(Contenu.Joueur);
	}

	public void Tourner(Direction d) {
		this.d = d;
	}

	public Case next_case() {
		switch (d) {
		case Nord:
			return Terrain.terrain[line - 1][col];
		case Est:
			return Terrain.terrain[line][col + 1];
		case Sud:
			return Terrain.terrain[line + 1][col];
		case Ouest:
			return Terrain.terrain[line][col - 1];
		default:
			return Terrain.terrain[line][col];
		}
	}

	public String toString() {
		return "( " + line + "," + col + " )";
	}
	
	public void afficher_inventaire(){
		System.out.print("Inventaire : ");
		if(!inventaire.isEmpty() ){
			System.out.print(inventaire.toString());
		}
		System.out.println();
		
	}
}
