
public class House extends AbstractPiece {

	public House() {}
	public House(int n) {super(n);}

	public void display() {System.out.print('H');}

	public boolean move(Piece dest, Cell[][] cells) {
		return false;
		
	}
}
