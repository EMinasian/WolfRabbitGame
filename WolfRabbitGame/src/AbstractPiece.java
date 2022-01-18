
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPiece implements Piece {
	
	private boolean traversed[][]; //the pieces remember the cells they have already traversed, in order to avoid falling into loop
	private Cell cell; //The pieces know where they are!
	private Cell dest = null; //The pieces know where they should go to.
	
	//The constructor method
	public AbstractPiece(int n, Cell d) {
		traversed = new boolean[n][n];
		dest = d;
		}
	
	public Cell getCell() {return cell;}
	public void setCell(Cell c) {
		cell = c;
		traversed[c.getX()][c.getY()] = true;
		}
	
	/*
	 * Moving is a rather complex act in the game.
	 * The pieces should consider various factors to select the next cell to move to.
	 * To correctly incorporate the complexity, various utility methods are included in the class.
	 * This method returns true if the piece has reached its destination.
	 */
	public boolean move(Board board) {
		if(this instanceof Wolf) updateDestination(board);
		Cell next = findNext(board);
		if(next == null) return false;
		moveTo(getDestination(), getCell(), next, board);
		if(reached()) return true;
		return false;
	}
	
	private Cell getDestination() {return dest;}
	
	private void updateDestination(Board board) {dest = board.getRabbit();}
	
	
	/*
	 * After prioritizing the cells based on their distance and being already traversed,
	 * the first cell is the most desirable.
	 * To avoid exceptions, when a piece is stuck, it simply does not move.
	 */
	private Cell findNext(Board board) {
		List<Cell> nextPotMove = new LinkedList<>();
		nextPotMove = potentialCells(board);
		prioritize(nextPotMove);
		if(!nextPotMove.isEmpty()) return nextPotMove.get(0);
		return null;
	}
	
	/*
	 * The rabbit and the wolves have different policies to make their moves.
	 * This abstract method has enabled the correct division of the policies.
	 */
	protected abstract List<Cell> potentialCells(Board board);
	
	//Each object checks if it has reached its destination or not.
	private boolean reached() {return getCell().equals(getDestination());}
	
	
	/*
	 * moving includes various updates, so a decision was made to separate it as a method.
	 */
	private void moveTo(Cell dest, Cell cur, Cell next, Board board) {
		Cell[][] c = board.getCells();
		this.setCell(c[next.getX()][next.getY()]);
		c[next.getX()][next.getY()].setPiece(this);
		c[cur.getX()][cur.getY()].setPiece(null);
	}
	
	/*
	 * The path selection method is based on two principles: 
	 * 1- the pieces are preferred to minimize their distance from the destination,
	 * 2- at he same time, to avoid the pieces from getting stuck, they can go back the path they came
	 * however, the preference is always to avoid going back to a cell that the piece has already traversed.
	 */
	private void prioritize(List<Cell> potCells) {
		potCells.sort(new DistanceComp());
		potCells.sort(new TraversedComp());
	}
	
	
	private double distance(Cell c) {
		return Math.hypot(Math.abs(c.getX() - dest.getX()), c.getY() - dest.getY());
	}
	
	
	/*
	 * A comparator enabling sorting the cells based on their distance from the destination cell.
	 * The cells with minimum distance are prioritized. 
	 */
	private class DistanceComp implements Comparator<Cell> {

		public int compare(Cell c1, Cell c2) {
			if(distance(c1) < distance(c2)) return -1;
			if(distance(c1) > distance(c2)) return 1;
			return 0;
		}
	}
	
	
	/*
	 * A comparator enabling sorting the cells based on being already traversed.
	 * The cells that are not traversed are prioritzed.
	 */
	private class TraversedComp implements Comparator<Cell> {
		
		public int compare(Cell c1, Cell c2) {
			if(traversed[c1.getX()][c1.getY()] && !traversed[c2.getX()][c2.getY()]) return 1;
			if(!traversed[c1.getX()][c1.getY()] && traversed[c2.getX()][c2.getY()]) return -1;
			return 0;
		}
	}
	

	
}
