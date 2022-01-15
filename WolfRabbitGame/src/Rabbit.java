import java.util.LinkedList;
import java.util.List;

public class Rabbit extends AbstractPiece {
	

	public Rabbit(int n) {super(n);}
	
	//returns true if rabbit reaches the house
	public boolean move(Piece dest, Cell[][] cells) {
		Cell destination = dest.getCell();
		Cell current = this.getCell();
		if(destination.equals(current)) return true;
		Cell next = findNext(destination, cells);
		moveTo(destination, current, next, cells);
		return false;
	}
	

	public void display() {System.out.print('R');}
	
	
	private Cell findNext(Cell dest, Cell[][] cells) {
		List<Cell> nextPotMove = potentialCells(cells);
		nextPotMove = potentialCells(cells);
		prioritize(nextPotMove, dest);
		return nextPotMove.get(0);
	}
	
	private List<Cell> potentialCells(Cell[][] cells) {
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
		return canMoveTo(c[x][y], c);
	}
	
	private boolean canMoveTo(Cell cell, Cell[][] c) {
		if(cell.isEmpty() || cell.isHouse()) return true;
		else return false;
	}



}
