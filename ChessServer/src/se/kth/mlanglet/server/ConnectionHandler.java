package se.kth.mlanglet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The connection handler for the server. 
 * Accepts connections and assigns a worker thread to serve the client socket
 * @author mathias
 *
 */
public class ConnectionHandler extends Thread implements IGameListener {
	
	private ServerSocket serverSocket;
	private ArrayList<GameHandler> games;
	
	public ConnectionHandler(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
		
		games = new ArrayList<GameHandler>();
		games.add(new GameHandler(this));
		
		start();
	}
	
	public void run(){
		while(true){
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
			    System.err.println(e.getMessage());
			}
			
			if(socket != null){
				for(GameHandler game : games){
					if(!game.isFull()){
						game.addPlayer(socket);
						socket = null;
						break;
					}
				}
				
				if(socket != null){
					GameHandler game = new GameHandler(this);
					game.addPlayer(socket);
					games.add(game);
				}
			}
		}
	}
	
	/**
	 * Called by the GameHandler when the game ends
	 */
	public void gameOver(GameHandler game){
		games.remove(game);
	}
	
	protected void finalize(){
	    try{
	        serverSocket.close();
	    } catch (IOException e) {
	        System.err.println("ConnectionHandler: Could not close server socket!");
	        System.exit(-1);
	    }
	}
}
