package chess.pieces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import se.kth.mlanglet.types.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import chess.components.GameBoard;
import chess.components.GameSquare;


public class Bishop extends Chesspiece {

	public Bishop(Color color) {
		super(color, Type.BISHOP);
		
		if(color == Color.WHITE && counter == 3)
			point = new Point(150, 0);
		else if(color == Color.WHITE)
			point = new Point(375, 0);
		else if(color == Color.BLACK && counter == 19)
			point = new Point(150, 525);
		else if(color == Color.BLACK && counter == 22)
			point = new Point(375, 525);
	}

	public Bishop(Color color, boolean test, Point p){
		super(color, Type.BISHOP);
		
		point = p;
	}
	
	/**
	 * See super class for description!
	 */
	@Override
	public Point[] path() {
		ArrayList<Point> validMoves = new ArrayList<Point>();
		boolean[] pathBlocked = new boolean[4];
		GameSquare gs = null;
		Point p = null;
		
		for(int i = 0; i < 4; i++){
			for(int j = 1; j < 9; j++){
				
				switch(i){
				case 0:
					p = new Point(point.x + (SQUARE_SIZE * j), point.y + (SQUARE_SIZE * j));
					break;
				case 1:
					p = new Point(point.x + (SQUARE_SIZE * j), point.y - (SQUARE_SIZE * j));
					break;
				case 2:
					p = new Point(point.x - (SQUARE_SIZE * j), point.y + (SQUARE_SIZE * j));
					break;
				case 3:
					p = new Point(point.x - (SQUARE_SIZE * j), point.y - (SQUARE_SIZE * j));
					break;
				default:
					System.out.println("FATAL ERROR, SHOULD NOT BE HERE!");
				}
				
				gs = GameBoard.getGameSquare(p);
				if(gs != null){
					if(gs.isOccupied() && !pathBlocked[i]){
						if(gs.getGuest().getColor() != color){
							validMoves.add(p);
							pathBlocked[i] = true;
						}
						else if(gs.getGuest().getColor() == color){
							pathBlocked[i] = true;
						}
					}
					else if(!pathBlocked[i]){
						validMoves.add(p);
					}
					else {
						pathBlocked[i] = true;
					}
				}
			}
		}
		return arrayListToNativeArray(validMoves);		
	}

	/**
	 * See super class for description!
	 */
	@Override
	public void draw(Graphics g) {
		int x1Points[] = {15, 60, 53, 48, 46, 45, 45, 48, 54, 48, 44, 44, 48, 45, 38, 31, 28, 32, 32, 29, 22, 28, 31, 31, 30, 27, 23, 15};
		int y1Points[] = {70, 70, 64, 59, 54, 49, 42, 37, 34, 33, 30, 25, 20, 13, 8, 13, 20, 25, 30, 33, 34, 37, 42, 49, 54, 59, 64, 70};
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint
		  (RenderingHints.KEY_ANTIALIASING,
				   RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		
		// Draw shape
		g2.setStroke(new BasicStroke(1.6F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g2.setColor(color);

		g2.fillPolygon(x1Points, y1Points, x1Points.length);
		
		
		// Draw outline
		g2.setColor(drawingProperties.getOutlineColor());

		g2.drawPolygon(x1Points, y1Points, x1Points.length);
		
		// Draw inline
		g2.setColor(drawingProperties.getInlineColor());
		g2.drawLine(28, 18, 48, 18);
		g2.drawLine(22, 33, 54, 33);
		g2.drawLine(20, 67, 56, 67);
		
	}

}
