import java.util.LinkedList;
import java.util.List;

public class Wolf extends AbstractPiece {

	public Wolf(int n, Cell d) {super(n, d);}
	

	public void display() {System.out.print('W');}
	

	public List<Cell> potentialCells(Board board) {
		
		int curX = this.getCell().getX();
		int curY = this.getCell().getY();
		List<Cell> potCells = new LinkedList<>();
		if(canMoveTo(curX + 1, curY, board)) potCells.add(new Cell(curX + 1, curY));
		if(canMoveTo(curX - 1, curY, board)) potCells.add(new Cell(curX - 1, curY));
		if(canMoveTo(curX, curY + 1, board)) potCells.add(new Cell(curX, curY + 1));
		if(canMoveTo(curX, curY -1 , board)) potCells.add(new Cell(curX, curY - 1));
		return potCells;
	}
	
	
	private boolean canMoveTo(int x, int y, Board board) {
		Cell[][] c = board.getCells();
		if(x < 0 || y < 0 || x >= c.length || y >= c.length) return false;
		if(board.isHouse(c[x][y]) || board.isBarrier(c[x][y])) return false;
		if(c[x][y].isEmpty() || c[x][y].isRabbit()) return true;
		return false;
	}
	



}

