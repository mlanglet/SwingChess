package chess.exceptions;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {
	public IllegalMoveException() {
		super();
	}
	public IllegalMoveException(String msg){
		super(msg);
	}
}
