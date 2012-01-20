package chess.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import se.kth.mlanglet.types.Point;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import chess.exceptions.IllegalMoveException;
import chess.exceptions.NotMovedException;
import chess.pieces.Chesspiece;
import chess.pieces.Chesspiece.Type;

/**
 * Represents a square on a chess board, may have a chess piece as guest
 * @author Mathias
 *
 */
@SuppressWarnings("serial")
public class GameSquare extends JPanel {	
	private GameSquareProperties properties;
	private Chesspiece guest = null;
	
	public GameSquare(int index){
		
		properties = new GameSquareProperties(index);
		
		Color highlight = properties.getColor();
		
		Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED, highlight, highlight.darker().darker().darker());
		setBorder(border);
		
		setBackground(properties.getColor());
		setPreferredSize(new Dimension(75, 75));		
		setVisible(true);
	}

	/**
	 * Tells if the square has a guest or not
	 * @return true if guest is present otherwise false
	 */
	public boolean isOccupied(){
		if(guest == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Sets the current guest. USE ONLY WHEN INITIALIZING BOARD!
	 * Otherwise use occupyGameSquare for move validation
	 * @param piece
	 */
	public void setGuest(Chesspiece piece){
		guest = piece;
		repaint();
	}
	
	/**
	 * Tries to occupy the square with provided chess piece, must cast exception if not moved
	 * @param chesspiece
	 * @throws NotMovedException
	 * @throws IllegalMoveException 
	 */
	public Chesspiece occupyGameSquare(Chesspiece chesspiece) throws IllegalMoveException, NotMovedException {	
		Point[] path = null;
		boolean moved = false;
		Chesspiece result = null;
		
		path = chesspiece.path();
		
		if(path == null)
			throw new IllegalMoveException("No valid moves for this chess piece");
		
		for(Point p : path){
			if(p.equals(getGameSquareProperties().getPoint())){
				if(isOccupied() && getGuest().getColor() != chesspiece.getColor()){
					if(guest.getType() == Type.KING){
						throw new IllegalMoveException(getRowCol() + " is not a valid move! You cannot eat the King!");
					}
					
					result = guest;
					guest = chesspiece;
					guest.move(getGameSquareProperties().getPoint());
					
					moved = true;
					break;
				}
				else {
					guest = chesspiece;
					guest.move(getGameSquareProperties().getPoint());
					
					moved = true;
					break;
				}
			}
		}
		
		if(!moved)
			throw new NotMovedException(getRowCol() + " is not a valid move!");

		return result;
	}
	
	/**
	 * When called clears its guest variable and returns the guest
	 * @return
	 */
	public Chesspiece freeGameSquare(){
		Chesspiece chesspiece = guest;
		guest = null;
		return chesspiece;
	}
	
	/**
	 * Returns information about the square as a string
	 */
	public String toString(){
		return properties.toString();
	}
	
	/**
	 * Returns the row number and column character as a string
	 * @return
	 */
	public String getRowCol(){
		return properties.getRow()+properties.getColumn();
	}
	
	/**
	 * returns the guest piece if any
	 * @return
	 */
	public Chesspiece getGuest(){
		return guest;
	}
	
	/**
	 * Returns the object that holds default data for this square
	 * @return
	 */
	public GameSquareProperties getGameSquareProperties(){
		return properties;
	}
	
	/**
	 * Paints the square and if occupied the on the provided graphic surface
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		if(guest != null){
			guest.setDrawingProperties(getGameSquareProperties().getColor());
			guest.draw(g);
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.GRAY);
		g.drawString(getRowCol(), 5, 15);
	}
}
