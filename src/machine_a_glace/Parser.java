package machine_a_glace;

public class Parser {

	public static boolean ExpressionCorrecte(String s) {
		Node a = new Node(null);

			try {
				a = Reader.read(s);
			}
			catch (JeuException e) {
				return false;
			
			}
		return true;
	}
	public static Node ExpressionCorrecte1(String s) {
		Node a = new Node(null);
		a = Reader.read(s);	
		return a;
	}

}
