package machine_a_glace;

public class Parser {

	public boolean ExpressionCorrecte(String s, Node n) {
		Node a = new Node(null);
		try {
			a = Reader.read(s);
		} catch (JeuException e) {
			return false;
		}
		n=a;
		return true;
	}

}
