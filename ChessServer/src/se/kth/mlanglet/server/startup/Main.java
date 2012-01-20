package se.kth.mlanglet.server.startup;

import java.io.IOException;
import java.net.ServerSocket;

import se.kth.mlanglet.server.ConnectionHandler;

/**
 * Startup for the server
 * @author mathias
 *
 */
public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(2000);
			System.out.println("Server started");
			ConnectionHandler connectionHandler = new ConnectionHandler(serverSocket);
		} catch (IOException e) {
			System.out.println("Server initiation failed!: " + e.getMessage());
			System.exit(-1);
			e.printStackTrace();
		}
	}
}

