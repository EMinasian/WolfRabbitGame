import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Board {
	
	Random rand =  new Random();
	
	private Cell[][] cell;
	private Cell house;
	private List<Cell> barrier = new ArrayList<>(6);
	private Cell rabbitCell;
	private Piece rabbit;
	private Piece[] wolf;
	
	
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
		rabbitCell = assign(n, rabbit);
		
		wolf = new Wolf[2];
		for(int i = 0; i < wolf.length; i++) {
			wolf[i] = new Wolf(n, rabbitCell);
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
			

			end = nextRound(rabbit, wolf);
			
		}
		while(end == false);
	}
	
	private boolean nextRound(Piece rabbit, Piece[] wolf) {
		display();
		System.out.println();
		boolean rabWon = rabbit.move(this);
		rabbitCell = rabbit.getCell();
		display();
		System.out.println();
		System.out.println("Rabbit moved to " + rabbitCell.getX() + " " + rabbitCell.getY());
		
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
	
	
	private Cell assign(int n, Piece p) {
		
		int x, y;
		do {
			x = rand.nextInt(n);
			y = rand.nextInt(n);
		}
		while(!cell[x][y].isEmpty() || cell[x][y].equals(house) || barrier.contains(cell[x][y]));
		cell[x][y].setPiece(p);
		p.setCell(cell[x][y]);
		return cell[x][y];
	}
	

}
