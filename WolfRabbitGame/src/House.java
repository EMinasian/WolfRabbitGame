
public class House extends AbstractPiece {

	public House() {}
	public House(int n, Piece d) {super(n, d);}

	public void display() {System.out.print('H');}

	public boolean move(Cell[][] cells) {
		return false;
		
	}
}
