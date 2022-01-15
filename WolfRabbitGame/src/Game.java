
public class Game {

	public static void main(String[] args) {
		
		int size = 6;
		Board board = new Board(size);
		Piece house = new House();
		Piece rabbit = new Rabbit(size, house);
//		Piece wolf = new Wolf(size, rabbit);
		Piece[] wolf = new Wolf[2];
		for(int i = 0; i < wolf.length; i++) 
			wolf[i] = new Wolf(size, rabbit);
		
		
//		start(6, board, rabbit, house);
		start(6, board, rabbit, house, wolf);
		
		board.play(rabbit, wolf);
		
		
		
		

	}
	
	public static void start(int n, Board board, Piece rabbit, Piece house, Piece[] wolf) {
		board.initiate(n, rabbit, house, wolf);
	}
	
//	public static void start(int n, Board board, Piece rabbit, Piece house, Piece[] wolf) {
//		board.initiate(n, rabbit, house, wolf);
//	}
	

}
