package machine_a_glace;

public class Case {
	private Couleur coul;
	private Contenu cont;
	private Expr expr;
	private Entite ent;

	public Case() {
		coul = Couleur.Neutre;
		cont = Contenu.Vide;
	}

	public boolean isAccessible() {
		return (cont != Contenu.Obstacle && cont != Contenu.Joueur && cont != Contenu.Robot);
	}

	
	public boolean isCreer(){
		return (cont == Contenu.Creer);
	}
	public boolean isExpr() {
		return (cont == Contenu.Expression);
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
	
	public Expr expr() {

		return expr;
	}

	public String toString() {
		if (isExpr())
			return expr.toString();
		else
			return cont.toString();
	}
	
	public String ContenuToString(){
		return cont.toString();
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
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
	
	public boolean isEnnemi(Entite ent) {
		if (ent == null || this.getEntite() == null)
			return false;
		else {
			if (ent.getCouleur() == Couleur.Bleu && this.getEntite().getCouleur() == Couleur.Rouge || ent.getCouleur() == Couleur.Rouge && this.getEntite().getCouleur() == Couleur.Bleu)
				return true;
			else
				return false;
		}
	}

	public Contenu getCont() {
		return cont;
	}
	

}
