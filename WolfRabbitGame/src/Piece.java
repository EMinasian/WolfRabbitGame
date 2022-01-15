
public interface Piece {
	
	Cell getCell();
	void setCell(Cell c);
	
	boolean move(Piece dest, Cell[][] cells);
	
	void display();

}
