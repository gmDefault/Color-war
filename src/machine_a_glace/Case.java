package machine_a_glace;

public class Case {
	private Couleur coul;
	private Contenu cont;
	private Operateur op;
	private Entite ent;

	public Case() {
		coul = Couleur.Neutre;
		cont = Contenu.Vide;
	}

	public boolean isAccessible() {
		return (cont != Contenu.Obstacle && cont != Contenu.Joueur && cont != Contenu.Robot);
	}

	
	public boolean isOperateur() {
		return (cont == Contenu.Opérateur);
	}

	public boolean isJoueur() {
		return (cont == Contenu.Joueur);
	}

	public boolean isRobot() {
		return (cont == Contenu.Robot);
	}

	public void setCase(Contenu c) {
		cont = c;
	}
	
	public void setEntite(Entite ent){
		this.ent = ent;
	}

	public void setCouleur(Couleur c) {
		coul = c;
	}

	public Entite getEntite(){
		return ent;
	}
	
	public Operateur op() {

		return op;
	}

	public String toString() {
		if (isOperateur())
			return op.toString();
		else
			return cont.toString();
	}

	public void setOp(Operateur op) {
		this.op = op;
	}
	
	public Couleur getCouleur(){
		return coul;
	}
	
	public Couleur getCouleurInverse(){
		if(coul == Couleur.Rouge){
			return Couleur.Bleu;
		}
		else if(coul == Couleur.Bleu){
			return Couleur.Rouge;
		}
		else return Couleur.Neutre;
	}
}
