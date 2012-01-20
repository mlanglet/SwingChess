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


public class Rook extends Chesspiece {
	
	public Rook(Color color) {
		super(color, Type.ROOK);
			
		if(color == Color.WHITE && counter == 1)
			point = new Point(0, 0);
		else if(color == Color.WHITE && counter == 8)
			point = new Point(525, 0);
		else if(color == Color.BLACK && counter == 17)
			point = new Point(0, 525);
		else if(color == Color.BLACK && counter == 24)
			point = new Point(525, 525);
	}
	
	public Rook(Color color, boolean test, Point p){
		super(color, Type.ROOK, test);
		
		point = p;
		
		System.out.println("Test piece spawned!");
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
					p = new Point(point.x + (SQUARE_SIZE * j), point.y);
					break;
				case 1:
					p = new Point(point.x - (SQUARE_SIZE * j), point.y);
					break;
				case 2:
					p = new Point(point.x, point.y + (SQUARE_SIZE * j));
					break;
				case 3:
					p = new Point(point.x, point.y - (SQUARE_SIZE * j));
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
					else if(!pathBlocked[i]) {
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
		int x1Points[] = {10, 65, 65, 59, 59, 55, 55, 61, 61, 52, 52, 43, 43, 32, 32, 23, 23, 14, 14, 20, 20, 16, 16, 10, 10};
		int y1Points[] = {70, 70, 63, 63, 55, 51, 26, 20, 10, 10, 14, 14, 10, 10, 14, 14, 10, 10, 20, 25, 50, 55, 63, 63, 70};
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		// Draw shape
		g2.setStroke(new BasicStroke(0.8F));
		g2.setColor(color);
		g2.fillPolygon(x1Points, y1Points, x1Points.length);

		// Draw outline
		g2.setColor(drawingProperties.getOutlineColor());
		g2.drawPolygon(x1Points, y1Points, x1Points.length);
		
		// Draw inlines
		g2.setColor(drawingProperties.getInlineColor());
		g2.drawLine(15, 20, 61, 20); // ______
		g2.drawLine(16, 56, 59, 56);
		g2.drawLine(10, 63, 65, 63);
		
		g2.setStroke(new BasicStroke(1.0F));
		g2.drawLine(20, 50, 55, 50);
		g2.drawLine(20, 25, 55, 25);
		
	}

}
