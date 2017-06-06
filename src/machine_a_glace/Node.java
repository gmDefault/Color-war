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

}
