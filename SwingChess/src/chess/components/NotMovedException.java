package chess.components;

@SuppressWarnings("serial")
public class NotMovedException extends Exception {
	public NotMovedException() {
		super();
	}
	public NotMovedException(String msg){
		super(msg);
	}
}
