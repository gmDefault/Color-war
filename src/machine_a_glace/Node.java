package machine_a_glace;

public class Node {

	private Node FG, FD;
	private Expr Gram;
	private Chiffre save;

	public Node(Expr Gram, Node FG, Node FD) {
		this.FG = FG;
		this.FD = FD;
		this.Gram = Gram;
	}

	public Node(Expr Gram) {
		this.Gram = Gram;
	}

	public void SetChildren(Node FG, Node FD) {
		this.FG = FG;
		this.FD = FD;
	}

	public String toString() {

		if (FG != null && FD != null)
			return FG.toString() + Gram.toString() + FD.toString();
		else if (FG == null && FD == null)
			return Gram.toString();
		else if (FG == null) {
			if (Gram == Operateur.Star)
				return Gram.toString() + "{" + FD.toString() + "}";
			return Gram.toString() + FD.toString();
		}

		else if (FD == null)
			return FG.toString() + Gram.toString();
		else
			return "bug";
	}

	public boolean isFeuille() {
		return (FD == null && FG == null);
	}

	public static void SetLeftChild(Node G, Node NG) {

		G.FG = NG;

	}

	public Expr Gram() {
		return Gram;
	}

	public Node FD() {
		return FD;
	}

	public Node FG() {
		return FG;
	}

	public void setSave(Chiffre c) {
		save = c;
	}

	public Chiffre getSave() {
		return save;
	}

	public void decrementer_chiffre() {
		if (Gram.isChiffre())
			switch ((Chiffre) Gram) {
			case Deux:
				Gram = Chiffre.Un;
				break;
			case Trois:
				Gram = Chiffre.Deux;
				break;
			case Quatre:
				Gram = Chiffre.Trois;
				break;
			case Cinq:
				Gram = Chiffre.Quatre;
				break;
			case Six:
				Gram = Chiffre.Cinq;
				break;
			case Sept:
				Gram = Chiffre.Six;
				break;
			case Huit:
				Gram = Chiffre.Sept;
				break;
			case Neuf:
				Gram = Chiffre.Huit;
				break;
			default:
				throw new JeuException("Ce chiffre n'existe pas");
			}
	}
	
	public void setGram(Expr e){
		Gram=e;
	}
	
	public void setFD(Node a){
		FD=a;
	}

}
