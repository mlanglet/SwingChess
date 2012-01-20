package chess.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chess.network.MessageHandler;

import se.kth.mlanglet.types.Message;

/**
 * The chat window
 * @author mathias
 *
 */
@SuppressWarnings("serial")
public class ChatWindow extends JPanel implements ActionListener {
	
	private final JButton quitGame = new JButton("Quit");
	private JTextArea messageTextArea = new JTextArea();
	private JScrollPane messageTextPane = new JScrollPane(messageTextArea);
	private JTextField messageInput = new JTextField();

	private MessageHandler messageHandler = null;
	
	public ChatWindow(MessageHandler messageHandler){
		setPreferredSize(new Dimension(407, 950));
		setBackground(Color.decode("0x104E8B"));
		
		this.messageHandler = messageHandler;
		
		messageHandler.addMessageListener(messageTextArea);
		
		setLayout(new FlowLayout());
		
		messageTextArea.setEditable(false);
		messageTextArea.setFont(new Font("Fixedsys", Font.BOLD, 14));
		messageInput.setFont(new Font("Fixedsys", Font.BOLD, 14));
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setMargin(new Insets(5, 5, 5 ,5));
		
		messageTextArea.setBackground(Color.decode("0x7EB6FF"));
		quitGame.setBackground(Color.RED);
		
		messageTextPane.setPreferredSize(new Dimension(390, 860));
		messageInput.setPreferredSize(new Dimension(390, 20));
		quitGame.setPreferredSize(new Dimension(390, 20));
		
		messageInput.addActionListener( this );
		quitGame.addActionListener( this );
		
		//Container layout
		add(quitGame);
		add(messageTextPane);
		add(messageInput);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == messageInput){
			messageHandler.sendMessage(new Message(messageInput.getText(), Message.MessageType.TEXT, messageHandler.getPlayerName()));
			messageInput.setText("");
		}
		if(e.getSource() == quitGame){
			messageHandler.sendMessage(new Message("has left the server.",  Message.MessageType.BYE, messageHandler.getPlayerName()));
			System.exit(0);
		}
	}
}
