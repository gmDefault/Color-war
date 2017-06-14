package machine_a_glace;

import java.util.ArrayList;

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
//	 public static Expr ChartoExpr(char s)
//	  {
//	    switch (s)
//	    {
//	      case 'X' : return Comportement.Explore;
//	      case 'A' : return Comportement.Attack;
//	      case 'P' : return Comportement.Protect;
//	      case 'K' : return Comportement.Kamikaze;
//	      case ';' : return Comportement.Un;
//	      case ':' : return Comportement.Deux;
//	      case '>' : return Comportement.Trois;
//	      case '|' : return Comportement.Quatre;
//	      default : throw new JeuException("Comportement non declarer");
//	    }
//	  }
//	public static boolean InventaireOk(String s,Joueur j){
//		int i = 0;
//		char t5[] = s.toCharArray();
//		ArrayList<Expr> inv = (ArrayList<Expr>) j.inventaire().clone();
//		while(inv.contains(ChartoExpr(t5[i]))){
//			
//		}
//		return;
//	}

}
