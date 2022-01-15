import java.util.LinkedList;
import java.util.List;

public class Rabbit extends AbstractPiece {
	
	

	public Rabbit(int n, Piece d) {super(n, d);}

	public void display() {System.out.print('R');}
	
	
	public List<Cell> potentialCells(Cell[][] cells) {
		int curX = this.getCell().getX();
		int curY = this.getCell().getY();
		List<Cell> potCells = new LinkedList<>();
		
		if(curX + 1 < cells.length) {
			if(canMoveTo(curX + 1, curY, cells)) potCells.add(new Cell(curX + 1, curY));
		}
		else {
			if(canMoveTo(0, curY, cells)) potCells.add(new Cell(0, curY));
		}
		if(curX - 1 >= 0) {
			if(canMoveTo(curX - 1, curY, cells)) potCells.add(new Cell(curX - 1, curY));
		}
		else {
			if(canMoveTo(cells.length - 1 , curY, cells)) potCells.add(new Cell(cells.length - 1, curY));
		}
		if(canMoveTo(curX, curY + 1, cells)) potCells.add(new Cell(curX, curY + 1));
		if(canMoveTo(curX, curY -1 , cells)) potCells.add(new Cell(curX, curY - 1));
		return potCells;
	}
	
	
	private boolean canMoveTo(int x, int y, Cell[][] c) {
		if(y < 0 || y >= c.length) return false;
		if(c[x][y].isEmpty() || c[x][y].isHouse()) return true;
		return false;
	}
	
//	private boolean canMoveTo(Cell cell, Cell[][] c) {
//		if(cell.isEmpty() || cell.isHouse()) return true;
//		else return false;
//	}




}
