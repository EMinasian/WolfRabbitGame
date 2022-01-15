
public interface Piece {
	
	Cell getCell();
	void setCell(Cell c);
	
	boolean move(Cell[][] cells);
	
	void display();
	

}
