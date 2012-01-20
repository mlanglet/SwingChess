package se.kth.mlanglet.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import se.kth.mlanglet.types.Message;
import se.kth.mlanglet.types.Message.MessageType;

/**
 * Class representing the player, handles the messages to and from the player
 * @author Mathias
 *
 */
public class PlayerHandler extends Thread {
	
	private Socket socket;
	private GameHandler gameHandler;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    
    private String name = "Player";
	
	public PlayerHandler(Socket socket, GameHandler gameHandler){
		this.socket = socket;
		this.gameHandler = gameHandler;
		
    	try {
    		out = new ObjectOutputStream(socket.getOutputStream());
        	in = new ObjectInputStream(socket.getInputStream());
    	} catch (IOException e1){
    		try { 
    			socket.close();
    		} catch (Exception e){
    			System.err.println(e.getMessage());
    		}
    	}
    	start();
	}
	
	public void run(){
		boolean running = true;		
		while(running){
			Message message = null;
	        try {
	        	if(socket.isConnected()){
					while((message = ((Message) in.readObject())) != null){
						switch(message.getType()){
						case TEXT:
							gameHandler.sendMessage(message);
							break;
						case HELLO:
							gameHandler.sendMessage(new Message(message, MessageType.TEXT));
							name = message.getName();
							break;
						case BYE:
							gameHandler.sendMessage(message);
							gameHandler.playerLeft(this);
							running = false;
							break;
						case MOVE:
							gameHandler.sendMessage(message);
							break;
						}
					}
	        	}
	        } catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					System.err.println("PlayerHandler for " + name + "Couldn't close client socket!");
					break;
				}
			}
		}
	}
	
	/**
	 * Sends message to this player
	 * @param message
	 */
	public void sendMessage(Message message){		
        try {
        	out.writeObject(message);
        	out.flush();
		} catch (IOException e) {
			System.out.println("PlayerHandler for " + name + ": Could not write to output stream! Closing connection");
			gameHandler.playerLeft(this);
		}
	}
	
	/**
	 * Returns the socket state of the player
	 * @return
	 */
	public boolean isConnected(){
		return (!socket.isClosed());
	}
	
	/**
	 * Returns the player name
	 * @return
	 */
	public String getPlayerName(){
		return name;
	}
}
