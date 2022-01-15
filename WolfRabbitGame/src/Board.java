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
	
	public void initiate(int n, Piece rabbit, Piece house) {
		assign(n, rabbit);
		assign(n, house);	
	}
	
	public void play(Piece rabbit, Piece house) {
		boolean end = false;
		do {
			
			display();
			System.out.println();
			end = nextRound(rabbit, house);
			
		}
		while(end == false);
	}
	
	private boolean nextRound(Piece rabbit, Piece house) {
		boolean rabWon = rabbit.move(cell);
		if(rabWon == true) System.out.print("\nThe rabbit reached the house!");
		return rabWon;
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
