import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Board {
	
	Random rand =  new Random();
	
	//To maintain safety, instance variables are private (adjustable through methods).
	private Cell[][] cell;
	private Cell house;
	private List<Cell> barrier = new ArrayList<>(6);
	private Piece rabbit;
	private Piece[] wolf;
	
	/*
	 * The constructor of the board.
	 * Construction of the board includes putting pieces (rabbit, wolf) and blocks (barrier, house) on the board, too.
	 */
	public Board(int n) {
		cell = new Cell[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				cell[i][j] = new Cell(i, j);
		}
		
		house = generateBlock(n);
		
		for(int i = 0; i < 6; i++)
			barrier.add(generateBlock(n));

		rabbit = new Rabbit(n, house);
		assign(n, rabbit);
		
		wolf = new Wolf[4];
		for(int i = 0; i < wolf.length; i++) {
			wolf[i] = new Wolf(n, getRabbit());
			assign(n, wolf[i]);
		}	
	}
	
	public int size() {return cell.length;}
	public Cell[][] getCells() {return cell;}
	public boolean isHouse(Cell c) {return c.equals(house);}
	public boolean isBarrier(Cell c) {return barrier.contains(c);}
	public Cell getRabbit() {return rabbit.getCell();}
	
	public void play() {
		boolean end = false;
		do {
			display();
			end = nextRound(rabbit, wolf);
		}
		while(end == false);
		display();
	}
	
	
	private boolean nextRound(Piece rabbit, Piece[] wolf) {
		boolean rabWon = rabbit.move(this);
		boolean wolfWon = false;
		for(int i = 0; i < wolf.length; i++) {
			wolfWon = wolf[i].move(this);
			if(wolfWon == true) break;
		}
		if(rabWon == true) System.out.print("\nThe rabbit reached the house!");
		else if(wolfWon == true) System.out.print("\nThe wolves reached the rabbit!");
		return (rabWon || wolfWon);
	}
	
	private void display() {
		for(int i = cell.length - 1; 0 <= i; i--) {
			System.out.println();
			for(int j = 0; j < cell.length; j++) {
				if(cell[j][i].equals(house)) System.out.print('H');
				else if(barrier.contains(cell[j][i])) System.out.print('=');
				else if(cell[j][i].isEmpty()) System.out.print('*');
				else {
					cell[j][i].getPiece().display();
				}
			}
		}
		System.out.println();
	}
	
	private Cell generateBlock(int n) {
		int x, y;
		do {
			x = rand.nextInt(n);
			y = rand.nextInt(n);
		}
		while(!cell[x][y].isEmpty() || (house != null && cell[x][y].equals(house))||barrier.contains(cell[x][y]));
		return cell[x][y];
	}
	
	
	private void assign(int n, Piece p) {
		
		int x, y;
		do {
			x = rand.nextInt(n);
			y = rand.nextInt(n);
		}
		while(!cell[x][y].isEmpty() || cell[x][y].equals(house) || barrier.contains(cell[x][y]));
		cell[x][y].setPiece(p);
		p.setCell(cell[x][y]);
	}
	

}
