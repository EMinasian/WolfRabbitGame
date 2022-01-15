
public class Cell {
	private int x, y;
	private Piece piece = null;
	
	public Cell(int X, int Y) {
		x = X;
		y = Y;
	}
	
	public void setPiece(Piece p) {piece = p;}
	public Piece getPiece() {return piece;}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	
	public boolean isEmpty() {return piece == null;}
	public boolean isHouse() {return piece instanceof House;}
	public boolean isRabbit() {return piece instanceof Rabbit;}
	
	public boolean equals(Cell c) {
		if(x == c.getX() && y == c.getY()) return true;
		return false;
	}
	
	
}
