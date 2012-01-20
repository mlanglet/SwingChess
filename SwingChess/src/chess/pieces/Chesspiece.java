package chess.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import se.kth.mlanglet.types.Point;
import java.util.ArrayList;

import chess.components.DrawingProperties;
import chess.components.GameSquare;
import chess.interfaces.IDrawable;


/**
 * This class represents a chess piece but needs to be extended
 * @author Mathias
 *
 */
public abstract class Chesspiece implements IDrawable {
	
	protected static int counter = 0;
	
	protected final int SQUARE_SIZE = 75;
	
	public enum Type {
		PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
	}
	
	protected DrawingProperties drawingProperties = null;
	
	protected int id;
	
	protected Color color;
	
	protected Type type;
	
	protected Point point;
	
	protected Image image;
	
	public Chesspiece(Color color, Type type){
		this.color = color;
		this.type = type;
		counter++;
		id = counter;
	}
	
	public Chesspiece(Color color, Type type, boolean test){
		this.color = color;
		this.type = type;
		id = 73357;
	}
	
	/**
	 * Redraws the chess piece on given surface
	 * @param g
	 */
	public void update(Graphics g){
		draw(g);
	}
	
	/**
	 * checks if chess piece is on board or not
	 * @return
	 */
	public boolean isOnBoard() {
		if(point.x > -1 && point.x < 601 && point.y > -1 && point.y < 601){
			return true;
		}
		return false;
	}
	
	public Type getType(){
		return type;
	}
	
	/**
	 * Returns the color of this piece
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Returns the point of this piece
	 * @return
	 */
	public Point getPoint(){
		return point;
	}

	/**
	 * Returns a string representing this piece
	 */
	public String toString() {
		String colorString;
		
		if(color == Color.WHITE)
			colorString = "WHITE";
		else 
			colorString = "BLACK";
		
		
		String description = colorString + " | " + type.toString() + " | ID " + id;
		return description;
	}
	
	/**
	 * Moves the piece to given point
	 * @param p
	 * @param g
	 */
	public void move(Point p) {
		point = new Point(p);
	}
	
	public void setDrawingProperties(Color colorOfSquare){
		drawingProperties = new DrawingProperties(colorOfSquare, color);
	}
	
	protected GameSquare getGameSquare(GameSquare[] boardElements, Point point){
		for(GameSquare gs : boardElements){
			Point gameSquarePoint = gs.getGameSquareProperties().getPoint(); 
			
			if(gameSquarePoint.x == point.x && gameSquarePoint.y == point.y )
				return gs;
		}
		return null;
	}

	protected Point[] arrayListToNativeArray(ArrayList<Point> validMoves){
		Point[] validMovesNA;
		
		validMoves.trimToSize();
		validMovesNA = new Point[validMoves.size()];
		
		for(int i = 0; i < validMoves.size(); i++){
			validMovesNA[i] = validMoves.get(i);
		}
		
		if(validMovesNA.length == 0)
			return null;
		
		return validMovesNA;
	}
	
	/**
	 * Returns a list of points representing the moves the piece can make
	 * @param points
	 * @return
	 */
	public abstract Point[] path();
	
	/**
	 * Draws the piece on the graphic surface
	 * @param graphics The surface to draw the piece on
	 */
	public abstract void draw(Graphics graphics);
}
