
public class Game {

	public static void main(String[] args) {
		
		int size = 6;
		Board board = new Board(size);
		Piece house = new House();
		Piece rabbit = new Rabbit(size, house);
		
		
		start(6, board, rabbit, house);
		
		board.play(rabbit, house);
		
		
		
		

	}
	
	public static void start(int n, Board board, Piece rabbit, Piece house) {
		board.initiate(n, rabbit, house);
	}
	

}
