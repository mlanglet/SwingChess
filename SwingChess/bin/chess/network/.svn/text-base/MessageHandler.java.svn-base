package chess.network;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import chess.interfaces.IBoard;

import se.kth.mlanglet.types.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles networking on client side
 * @author mathias
 *
 */
public class MessageHandler extends Thread {

	ObjectOutputStream out = null;
    ObjectInputStream in = null;
    
    private JTextArea textArea;
    private IBoard board;
    
    private String name;
    
    public MessageHandler(Socket socket){
    	name = JOptionPane.showInputDialog("Enter your name!", "");
 
    	try {
    		out = new ObjectOutputStream(socket.getOutputStream());
        	in = new ObjectInputStream(socket.getInputStream());
    	} catch (IOException e){
    		System.err.println(e.getMessage());
    	}
    }
    
    public void run(){
    	System.out.println("MessageHandler: running..");
     	sendMessage(new Message("has joined the server.", Message.MessageType.HELLO, name));
    	
    	while(true){
    		try {
    			Message message = null;
				while((message = (Message) in.readObject()) != null){
					switch(message.getType()){
						case TEXT:
							textArea.append(message.getTime() + " " + message.getName() + ": " + message.getMessage() + "\n");
							break;
						case HELLO:
							textArea.append(message.getTime() + " " + message.getName() + ": " + message.getMessage() + "\n");
							board.setPlayer(message.getTurn());
							break;
						case BYE:
							textArea.append(message.getTime() + " " + message.getName() + ": " + message.getMessage() + "\n");
							board.gameOver();
							break;
						case MOVE:
							board.move(message.getMove());
							textArea.append(message.getTime() + " " + "Server: It's your turn now!\n");
							break;
					}
				}
			} catch (IOException e) {
				System.err.println("MessageHandler: IOException caught: " + e.getMessage());
				System.exit(-1);
			} catch (ClassNotFoundException e) {
				System.err.println("MessageHandler: ClassNotFoundException caught: " + e.getMessage());
			}
    	}
    }
    
    /**
     * Attempts to send a message to the server
     * @param message
     * @return returns a boolean indicating success or failure
     */
    public boolean sendMessage(Message message){
    	try {
			out.writeObject(message);
			out.flush();
			return true;
		} catch (IOException e) {
			System.out.println("MessageHandler: Couldn't send message: " + e.getMessage());
			return false;
		}
    }
    
    /**
     * Returns the player name
     * @return
     */
    public String getPlayerName(){
    	return name;
    }
    
    /**
     * set a text area where the incoming messages will be appended to
     * @param textArea
     */
    public void addMessageListener(JTextArea textArea){
    	this.textArea = textArea;
    }
    
    /**
     * set a board to where the incoming game messages will be forwarded to
     * @param board
     */
    public void addMessageListener(IBoard board){
    	this.board = board;
    }
}
