package machine_a_glace;

public class Attack extends Comportement {

	public static Attack ATTACK = new Attack("maps/A.png");

	private Attack(String s) {
		super.image = s;
	}

	@Override
	public boolean execute(Robot r) {
		// TODO Auto-generated method stub
		r.setProtection(false);
		int line, col;
		line = r.getLine();
		col = r.getCol();
		int taille_t = Terrain.getTaille();
		int degat = 30;
		Direction d = r.direction();
		Case case_r;
		switch (d) {
		case Ouest:
			// Attaque l'ennemi en face
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			return false;
		case Nord:
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			return false;
		case Sud:
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			case_r = Terrain.terrain[line][col - 1];
			if (col - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			return false;
		case Est:

			case_r = Terrain.terrain[line][col + 1];
			if (col + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			// Attaque l'ennemi à sa gauche
			case_r = Terrain.terrain[line - 1][col];
			if (line - 1 > 0 && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			// Attaque l'ennemi à sa droite
			case_r = Terrain.terrain[line + 1][col];
			if (line + 1 < taille_t && case_r.isEnnemi(r)) {
				if (case_r.getEntite().isRobot() && case_r.getEntite().robot().isProtected()) {
					degat = 10;
				}
				if (case_r.getEntite().getPdv() - degat > 0)
					case_r.getEntite().Degat(degat);
				else if (case_r.isRobot())
					Kill(case_r.getEntite().getLine(), case_r.getEntite().getCol(), (Robot) case_r.getEntite());
				else if (case_r.isJoueur()){
					case_r.getEntite().Degat(degat);
				}
				return true;
			}
			return false;

		default:

			return false;

		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A";
	}

}
