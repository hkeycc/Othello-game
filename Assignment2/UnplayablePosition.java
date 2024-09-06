package Assignment2;

public class UnplayablePosition extends Position{
	public static final char UNPLAYABLE='*';
	
	public UnplayablePosition(char UNPLAYABLE) {
		setPiece(UNPLAYABLE);
	}
	@Override
	public boolean canPlay() {
		
		return false;
	}
}
