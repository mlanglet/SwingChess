package se.kth.mlanglet.types;

public class SquareSize {
	
	private int size;
	
	public SquareSize(int width){
		this.size = width / 8;
	}
	
	public int getSize(){
		return size;
	}
}
