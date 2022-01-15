import java.util.Random;

public class Board {
	
	
	static Cell[][] cell;
	
	public Board(int n) {
		cell = new Cell[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				cell[i][j] = new Cell(i, j);
		}
		for(int i = 0; i < 6; i++)
			assign(n, new Barrier());
	}
	
	public Cell[][] getCells() {return cell;}
	
	public void initiate(int n, Piece rabbit, Piece house, Piece[] wolf) {
		assign(n, rabbit);
		assign(n, house);	
//		assign(n, wolf);
		for(int i = 0; i < wolf.length; i++)
			assign(n, wolf[i]);
	}
	
	public void play(Piece rabbit, Piece[] wolf) {
		boolean end = false;
		do {
			
			display();
			System.out.println();
			end = nextRound(rabbit, wolf);
			
		}
		while(end == false);
	}
	
	private boolean nextRound(Piece rabbit, Piece[] wolf) {
		boolean rabWon = rabbit.move(cell);
		boolean wolfWon = false;
		for(int i = 0; i < wolf.length; i++) {
			wolfWon = wolf[i].move(cell);
			if(wolfWon == true) break;
		}
			
		if(rabWon == true) System.out.print("\nThe rabbit reached the house!");
		else if(wolfWon == true) System.out.print("\nThe wolves reached the rabbit!");
		return (rabWon || wolfWon);
	}
	
	public void display() {
		for(int i = cell.length - 1; 0 <= i; i--) {
			System.out.println();
			for(int j = 0; j < cell.length; j++) {
				if(cell[j][i].isEmpty()) System.out.print('*');
				else {
					cell[j][i].getPiece().display();
				}
			}
		}
	}
	
	
	
	private static void assign(int n, Piece p) {
		Random rand =  new Random();
		int x, y;
		do {
			x = rand.nextInt(n);
			y = rand.nextInt(n);
		}
		while(!cell[x][y].isEmpty());
		cell[x][y].setPiece(p);
		p.setCell(cell[x][y]);
	}

}
