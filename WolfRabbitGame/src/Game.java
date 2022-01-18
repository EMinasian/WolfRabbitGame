
public class Game {
	
	/*
	 * Display:
	 * *: cells of the board (empty)
	 * =: barriers
	 * H: the house
	 * R: the rabbit
	 * W: the wolves
	 */

	public static void main(String[] args) {
	
		int size = 6;
		Board board = new Board(size);

		board.play();
		

		
	}
	

	

}
