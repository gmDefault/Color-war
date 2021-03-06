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

	
	 public static Expr CharToExpr(char s)
	  {
	    switch (s)
	    {
	      case 'X' : return Explore.EXPLORE;
	      case 'A' : return Attack.ATTACK;
	      case 'P' : return Protect.PROTECT;
	      case 'K' : return Kamikaze.KAMIKAZE;
	      case '>' : return Operateur.Priorite;
	      case '|' : return Operateur.Choixequi;
	      case '/' : return Operateur.Choix;
	      case ':' : return Operateur.Deuxpoints;
	      case '*' : return Operateur.Star;
	      case ';' : return Operateur.PointVirgule;

	      default : throw new JeuException("Comportement non declarer");
	    }
	  }
	  
	  public static boolean InventaireOk(String s, Joueur j){
		  int i=0;
		  int nb=0;
		  int nb2=0;
		  char t []=s.toCharArray();
		  ArrayList<Expr> inv_copy = (ArrayList<Expr>) j.inventaire().clone();
		  int nbpp = nbdeuxpoints(inv_copy);
		  for (int k=0;k<t.length;k++){
			  if (t[k]==':'){
				   nb += t[k+1]-'0';
				   nb2++;
				  
			  }
		  }
		  nb-=nb2;
		  if(nbpp<nb){
			  return false;
		  }
//		  for (int k=0;k<nb;k++){
//			  inv_copy.remove(Operateur.Deuxpoints);
//		  }
		  
		  while(i<t.length-1){
			  if (!(t[i]=='{'||t[i]=='}'||t[i]==';'||t[i]=='1'||t[i]=='2'||t[i]=='3'||t[i]=='4'||t[i]=='5'||t[i]=='6'||t[i]=='7'||t[i]=='8'||t[i]=='9')){
//				  if(t[i]==t[i+1]&&t[i]=='|'){
//					  t[i]='/';
//					  t[i+1]='{';
//				  }
				  if(!inv_copy.contains(CharToExpr(t[i]))){
					  return false;
				  }
				  else{
					  inv_copy.remove(CharToExpr(t[i]));
				  }
			  }
			  i++;
			  
		  }
		  
		  j.inventaire().clear();
		  j.inventaire().addAll(inv_copy);
		  return true;
	  }
	  private static int nbdeuxpoints (ArrayList<Expr> pp){
		  int c =0;
		  for (int i=0;i<pp.size();i++){
			  if (pp.get(i)==Operateur.Deuxpoints){
				  c++;
			  }
		  }
		  return c;
	  }
}
