package machine_a_glace;

public class Node {

	public Node FG, FD;
	public Expr Gram;

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
		else if (FG == null)
			return Gram.toString() + FD.toString();
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

	public void decremente_comp() {
		switch ((Comportement) Gram) {
		case Un:
			Gram = Comportement.Zero;
			break;
		case Deux:
			Gram = Comportement.Un;
			break;
		case Trois:
			Gram = Comportement.Deux;
			break;
		case Quatre:
			Gram = Comportement.Trois;
			break;
		case Cinq:
			Gram = Comportement.Quatre;
			break;
		case Six:
			Gram = Comportement.Cinq;
			break;
		case Sept:
			Gram = Comportement.Six;
			break;
		case Huit:
			Gram = Comportement.Sept;
			break;
		case Neuf:
			Gram = Comportement.Huit;
			break;
		default:
			break;
		}
	}

}
