package chess.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import chess.interfaces.IBoardToGameBar;
import chess.network.MessageHandler;
import chess.pieces.Chesspiece;

/**
 * The main window for this application
 * @author Mathias
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JPanel implements IBoardToGameBar {	
	private GameBar player1Bar;
	private GameBar player2Bar;
	private GameBoard 	board;
	
	public GameWindow(MessageHandler messageHandler) {
		board 		= new GameBoard(messageHandler, this);
		player1Bar	= new GameBar(Color.WHITE);
		player2Bar 	= new GameBar(Color.BLACK);
		setup();
	}
	
	private void setup(){		
		setPreferredSize(new Dimension(607, 950));
		setBackground(Color.decode("0x104E8B"));
	
		player1Bar.setLayout(new FlowLayout());
		player2Bar.setLayout(new FlowLayout());	
		
		add(player1Bar);
		add(board);
		add(player2Bar);
	}

	/**
	 * Send an eaten piece to the correct GameBar
	 */
	public void putChesspieceOnGameBar(Chesspiece chesspiece) {
		System.out.println("putChesspieceOnGameBar called.." + chesspiece.toString());
		
		if(chesspiece.getColor() == Color.WHITE){
			player1Bar.addChesspiece(chesspiece);
		}
		else {
			player2Bar.addChesspiece(chesspiece);
		}
	}
}