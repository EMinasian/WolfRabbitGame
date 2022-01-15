import java.util.LinkedList;
import java.util.List;

public class Wolf extends AbstractPiece {

	public Wolf(int n, Piece d) {super(n, d);}
	

	public void display() {System.out.print('W');}
	

	public List<Cell> potentialCells(Cell[][] cells) {
		int curX = this.getCell().getX();
		int curY = this.getCell().getY();
		List<Cell> potCells = new LinkedList<>();
		if(canMoveTo(curX + 1, curY, cells)) potCells.add(new Cell(curX + 1, curY));
		if(canMoveTo(curX - 1, curY, cells)) potCells.add(new Cell(curX - 1, curY));
		if(canMoveTo(curX, curY + 1, cells)) potCells.add(new Cell(curX, curY + 1));
		if(canMoveTo(curX, curY -1 , cells)) potCells.add(new Cell(curX, curY - 1));
		return potCells;
	}
	
	
	private boolean canMoveTo(int x, int y, Cell[][] c) {
		if(x < 0 || y < 0 || x >= c.length || y >= c.length) return false;
		if(c[x][y].isEmpty() || c[x][y].isRabbit()) return true;
		return false;
	}


}

