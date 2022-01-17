
public interface Piece {
	
	Cell getCell();
	void setCell(Cell c);

	boolean move(Board board);
	
	void display();
	

}
