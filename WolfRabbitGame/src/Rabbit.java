import java.util.LinkedList;
import java.util.List;

public class Rabbit extends AbstractPiece {
	
	

	public Rabbit(int n, Cell d) {super(n, d);}

	public void display() {System.out.print('R');}
	
	
	public List<Cell> potentialCells(Board board) {
		int curX = this.getCell().getX();
		int curY = this.getCell().getY();
		List<Cell> potCells = new LinkedList<>();
		
		if(curX + 1 < board.size()) {
			if(canMoveTo(curX + 1, curY, board)) potCells.add(new Cell(curX + 1, curY));
		}
		else {
			if(canMoveTo(0, curY, board)) potCells.add(new Cell(0, curY));
		}
		if(curX - 1 >= 0) {
			if(canMoveTo(curX - 1, curY, board)) potCells.add(new Cell(curX - 1, curY));
		}
		else {
			if(canMoveTo(board.size() - 1 , curY, board)) potCells.add(new Cell(board.size() - 1, curY));
		}
		if(canMoveTo(curX, curY + 1, board)) potCells.add(new Cell(curX, curY + 1));
		if(canMoveTo(curX, curY -1 , board)) potCells.add(new Cell(curX, curY - 1));
		return potCells;
	}
	
	
	private boolean canMoveTo(int x, int y, Board board) {
		Cell[][] c = board.getCells();
		if(y < 0 || y >= board.size()) return false;
		if(c[x][y].isEmpty() && !board.isBarrier(c[x][y])) return true;
		return false;
	}
	
}
