package se.kth.mlanglet.types;

import java.io.Serializable;

/**
 * This class represents a move taking place from point a to b
 * @author Mathias
 *
 */
public class Move implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 848314599686482816L;
	private Point start;
	private Point target;
	
	public Move(Point start, Point target){
		this.start = start;
		this.target = target;
	}
	
	public Point getStart(){
		return start;
	}
	
	public Point getTarget(){
		return target;
	}
}
