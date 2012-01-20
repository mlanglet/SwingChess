package chess.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import chess.network.MessageHandler;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815920757861844762L;
	
	public MainWindow(String title, MessageHandler messageHandler){
		super(title);
		
		setResizable(false);
		setPreferredSize(new Dimension(1020, 950));
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("res/icon.ico").getImage());
		
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		layout.setHgap(0);
		setLayout(layout);
		
		setBackground(Color.BLACK);

		add(new ChatWindow(messageHandler));
		add(new GameWindow(messageHandler));
		
		setVisible(true);
		
		pack();
	}
}
