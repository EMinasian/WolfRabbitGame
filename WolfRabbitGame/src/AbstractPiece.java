
import java.util.Comparator;
import java.util.List;

public abstract class AbstractPiece implements Piece {
	
	private boolean traversed[][];
	private Cell cell;
	private Piece dest = null;
	
	public AbstractPiece() {}
	public AbstractPiece(int n, Piece d) {
		traversed = new boolean[n][n];
		dest = d;
		}
	
	public Cell getCell() {return cell;}
	public void setCell(Cell c) {
		cell = c;
		if(!(this instanceof House || this instanceof Barrier))
			traversed[c.getX()][c.getY()] = true;
		}
	
	protected Cell getDestination() {return dest.getCell();}
	
	public boolean move(Cell[][] cells) {
		Cell next = findNext(cells);
		moveTo(getDestination(), getCell(), next, cells);
		if(reached()) return true;
		return false;
	}
	
	
	private Cell findNext(Cell[][] cells) {
		List<Cell> nextPotMove = potentialCells(cells);
		nextPotMove = potentialCells(cells);
		prioritize(nextPotMove);
		return nextPotMove.get(0);
	}
	
	protected abstract List<Cell> potentialCells(Cell[][] cells);
	
	
	protected boolean reached() {return getCell().equals(getDestination());}
	
	protected void moveTo(Cell dest, Cell cur, Cell next, Cell[][] c) {
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
		return Math.hypot(Math.abs(c.getX() - dest.getCell().getX()), c.getY() - dest.getCell().getY());
	}
	
}
