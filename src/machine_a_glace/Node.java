package machine_a_glace;

public class Node{

public Node FG, FD;
public Expr Gram;
	
	public Node(Expr Gram, Node FG, Node FD){
		this.FG = FG;
		this.FD = FD;
		this.Gram = Gram;
	}
	
	public Node(Expr Gram){
		this.Gram = Gram;
	}
	
	
	public void SetChildren (Node FG, Node FD)
	{
		this.FG = FG;
		this.FD = FD;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
	Node a = new Node(null, null, null);
	a.SetChildren(null, null);
	if(a.FG == null){
		System.out.print("nn");
	}
}
		
		
}
