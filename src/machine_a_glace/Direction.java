package machine_a_glace;

public enum Direction {
	Nord (0), Est(3), Sud(2), Ouest(1);
	
	private int entier;
	
	private Direction(int x){
		entier=x;
	}
	
	public int entier(){
		return entier;
	}
}
