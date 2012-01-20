package chess.components;

import java.awt.Color;

/**
 * Class representing the current drawing colors for the chesspiece.
 * @author Mathias
 *
 */
public class DrawingProperties {
	
	private Color outlineColor;
	private Color inlineColor;
	private Color invertedSuqareColor;
	private Color squareColor;
	
	public DrawingProperties(Color squareColor, Color pieceColor){
		this.squareColor = squareColor;
		
		outlineColor = Color.GRAY;
		inlineColor = Color.GRAY;
		invertedSuqareColor = Color.BLACK;
		if(squareColor == Color.WHITE){
			invertedSuqareColor = Color.WHITE;
		}
	}
	
	/**
	 * Returns the outlineColor
	 * @return
	 */
	public Color getOutlineColor(){
		return outlineColor;
	}
	
	/**
	 * Returns the inlineColor
	 * @return
	 */
	public Color getInlineColor(){
		return inlineColor;
	}
	
	/**
	 * Returnes the inverted square color
	 * @return
	 */
	public Color getInvertedSuqareColor(){
		return invertedSuqareColor;
	}
	
	/**
	 * Returnes the square color
	 * @return
	 */
	public Color getSuqareColor(){
		return squareColor;
	}
}
