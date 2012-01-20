package chess.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import se.kth.mlanglet.types.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import chess.pieces.Chesspiece;

/**
 * This class is an information displayer for the players
 * @author Mathias
 *
 */
@SuppressWarnings("serial")
public class GameBar extends JPanel implements MouseListener {
	private ArrayList<Chesspiece> pieces;
	private final int SIZE = 75;
	
	public GameBar(Color color){
		setPreferredSize(new Dimension(600, 150));
		setBackground(color);
		setVisible(true);
		
		pieces = new ArrayList<Chesspiece>();
	}

	public void addChesspiece(Chesspiece chesspiece) {
		pieces.add(chesspiece);
		
		int x, y;
		
		if(pieces.size() < 8){
			y = 0;
			x = pieces.size() * SIZE;
		}
		else {
			y = 75;
			x = (pieces.size() - 8) * SIZE;
		}
		
		chesspiece.move(new Point(x, y));
		chesspiece.setDrawingProperties(getBackground());

		repaint();
	}
	
	public Chesspiece removeChesspiece(Chesspiece chesspiece){
		for(int i = 0; i < pieces.size(); i++){
			if(pieces.get(i).equals(chesspiece))
				return pieces.remove(i);
		}
		repaint();
		return null;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		for(Chesspiece chesspiece : pieces){
			chesspiece.draw(getGraphics());
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
