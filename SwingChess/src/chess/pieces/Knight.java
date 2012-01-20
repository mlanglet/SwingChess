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


public class Knight extends Chesspiece {

	public Knight(Color color) {
		super(color, Type.KNIGHT);
		
		if(color == Color.WHITE && counter == 2)
			point = new Point(75, 0);
		else if(color == Color.WHITE && counter == 7)
			point = new Point(450, 0);
		else if(color == Color.BLACK && counter == 18)
			point = new Point(75, 525);
		else
			point = new Point(450, 525);
	}
	
	public Knight(Color color, boolean test, Point p){
		super(color, Type.KNIGHT, test);
		
		point = p;
		
		System.out.println("Test piece spawned!");
	}
	
	/**
	 * See super class for description!
	 */
	@Override
	public Point[] path() {
		ArrayList<Point> validMoves = new ArrayList<Point>();
		
		for(int i = 0; i < 8; i++){
			Point squarePoint = null;
			GameSquare gs = null;
			
			switch(i){
			case 0:
				squarePoint = new Point(point.x + 75, point.y + 150);
				break;
			case 1:
				squarePoint = new Point(point.x + 75, point.y - 150);
				break;
			case 2:
				squarePoint = new Point(point.x - 75, point.y + 150);
				break;
			case 3:
				squarePoint = new Point(point.x - 75, point.y - 150);
				break;
			case 4:
				squarePoint = new Point(point.x + 150, point.y + 75);
				break;
			case 5:
				squarePoint = new Point(point.x + 150, point.y - 75);
				break;
			case 6:
				squarePoint = new Point(point.x - 150, point.y + 75);
				break;
			case 7:
				squarePoint = new Point(point.x - 150, point.y - 75);
				break;
			}
			
			gs = GameBoard.getGameSquare(squarePoint);
			if(gs != null){
				if(gs.isOccupied()){
					if(gs.getGuest().getColor() != color){
						validMoves.add(squarePoint);
					}
				}
				else {
					validMoves.add(squarePoint);
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
		int x1Points[] = {23, 65, 65, 64, 62, 59, 52, 42, 31, 31, 30, 28, 26, 22, 19, 21, 20, 14,  7,  6,  6,  8, 13, 16, 15, 18, 24, 30, 35, 36, 34, 30, 24, 23};
		int y1Points[] = {70, 70, 51, 37, 30, 22, 16, 10, 10, 4,   3, 9,  10,  5,  6, 12, 16, 24, 33, 37, 43, 45, 46, 42, 48, 48, 40, 36, 31, 25, 37, 49, 62, 70};
		
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
		g2.drawLine(23,23, 26, 18);
		g2.drawLine(24, 23, 27, 18);
		g2.fillArc(10, 37, 3, 3, 0, 360);
		g2.drawLine(20, 72, 67, 72);
	}
}
