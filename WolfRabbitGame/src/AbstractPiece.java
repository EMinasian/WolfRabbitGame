
import java.util.List;

public abstract class AbstractPiece implements Piece {
	
	private boolean traversed[][];
	private Cell cell;
	
	public AbstractPiece() {}
	public AbstractPiece(int n) {traversed = new boolean[n][n];}
	
	public Cell getCell() {return cell;}
	public void setCell(Cell c) {
		cell = c;
		if(!(this instanceof House || this instanceof Barrier))
			traversed[c.getX()][c.getY()] = true;
		}
	
	protected void moveTo(Cell dest, Cell cur, Cell next, Cell[][] c) {
		this.setCell(c[next.getX()][next.getY()]);
		c[next.getX()][next.getY()].setPiece(this);
		c[cur.getX()][cur.getY()].setPiece(null);
	}
	
	protected static void prioritize(List<Cell> potCells, Cell dest) {
		distanceSort(potCells, dest);
	}
	
	private static void distanceSort(List<Cell> potCells, Cell dest) {
		int n = potCells.size();
		for(int k = 0; k < n; k++) {
			int min = k;
			for(int j = k + 1; j < n; j++)
				if(distCompare(potCells.get(j), potCells.get(min), dest) < 0)
					min = j;
				Cell temp = potCells.get(k);
				potCells.set(k, potCells.get(min));
				potCells.set(min, temp);
		}
	}
	
	
	private static int distCompare(Cell c1, Cell c2, Cell dest) {
		if(distance(c1, dest) < distance(c2, dest)) return -1;
		if(distance(c1, dest) > distance(c2, dest)) return 1;
		return 0;
	}
	
//	private int traverseCompare(Cell c1, Cell c2) {
//		if(traversed[c1.getX()][c1.getY()] && !traversed[c2.getX()][c2.getY()]) return 1;
//		if(!traversed[c1.getX()][c1.getY()] && traversed[c2.getX()][c2.getY()]) return -1;
//		return 0;
//	}
	
	
	private static double distance(Cell c, Cell dest) {
		return Math.hypot(Math.abs(c.getX() - dest.getX()), c.getY() - dest.getY());
	}
	
	
	
}
