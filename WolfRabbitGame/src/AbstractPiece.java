
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPiece implements Piece {
	
	private boolean traversed[][];
	private Cell cell;
	private Cell dest = null;
	
	public AbstractPiece() {}
	public AbstractPiece(int n, Cell d) {
		traversed = new boolean[n][n];
		dest = d;
		}
	
	public Cell getCell() {return cell;}
	public void setCell(Cell c) {
		cell = c;
		traversed[c.getX()][c.getY()] = true;
		}
	
	protected void setDestination(Cell d) {dest = d;}
	protected Cell getDestination() {return dest;}
	
	public boolean move(Board board) {
		if(this instanceof Wolf) updateDestination(board);
		Cell next = findNext(board);
		if(next == null) return false;
		moveTo(getDestination(), getCell(), next, board);
		if(reached()) return true;
		return false;
	}
	
	private void updateDestination(Board board) {dest = board.getRabbit();}
	
	
	private Cell findNext(Board board) {
		List<Cell> nextPotMove = new LinkedList<>();
		nextPotMove = potentialCells(board);
		prioritize(nextPotMove);
		if(!nextPotMove.isEmpty()) return nextPotMove.get(0);
		return null;
	}
	
	protected abstract List<Cell> potentialCells(Board board);
	
	
	protected boolean reached() {return getCell().equals(getDestination());}
	
	protected void moveTo(Cell dest, Cell cur, Cell next, Board board) {
		Cell[][] c = board.getCells();
		this.setCell(c[next.getX()][next.getY()]);
		c[next.getX()][next.getY()].setPiece(this);
		c[cur.getX()][cur.getY()].setPiece(null);
	}
	
	protected void prioritize(List<Cell> potCells) {
		potCells.sort(new DistanceComp());
		potCells.sort(new TraversedComp());
	}
	
	private class DistanceComp implements Comparator<Cell> {

		public int compare(Cell c1, Cell c2) {
			if(distance(c1) < distance(c2)) return -1;
			if(distance(c1) > distance(c2)) return 1;
			return 0;
		}
	}
	
	private class TraversedComp implements Comparator<Cell> {
		
		public int compare(Cell c1, Cell c2) {
			if(traversed[c1.getX()][c1.getY()] && !traversed[c2.getX()][c2.getY()]) return 1;
			if(!traversed[c1.getX()][c1.getY()] && traversed[c2.getX()][c2.getY()]) return -1;
			return 0;
		}
	}
	
	
	private double distance(Cell c) {
		return Math.hypot(Math.abs(c.getX() - dest.getX()), c.getY() - dest.getY());
	}
	
}
