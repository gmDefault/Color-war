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
	
	public static void SetLeftChild (Node G, Node NG){
		
		G.FG=NG;
		
	}
	public String toString(){
		if (FG != null && FD !=null) return "("+FG.toString()+Gram.toString()+FD.toString()+")";
		else if(FG ==null && FD == null) return "("+Gram.toString()+")";
		else if(FG == null) return "("+Gram.toString()+FD.toString()+")";
		else if(FD == null) return "("+FG.toString()+Gram.toString()+")";
		else return "bug";
	}

}
