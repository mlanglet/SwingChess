package chess.main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import chess.components.MainWindow;
import chess.network.MessageHandler;

/**
 * The main class for the application, creating the game window.
 * @author Mathias
 *
 */
public class Main {
	private static Socket socket = null;
	
	public static void main(String[] args) {
		String ip = "localhost";
		
		ip = JOptionPane.showInputDialog(null, "Enter ip: ", "Connect to server", JOptionPane.PLAIN_MESSAGE);
		
		try {
			socket = new Socket(ip, 2000);
		} catch (UnknownHostException e){
		    System.err.println("Cannot connect to: " + ip);
		    System.exit(1);
		} catch (IOException e){
		    System.err.println("Couldn't get I/O for the connection to: " + ip);
		    System.exit(1);
		}
		
		System.out.println("Socket created!");
		
		MessageHandler messageHandler = new MessageHandler(socket);
		
		System.out.println("MessageHandler created!");
		
       	@SuppressWarnings("unused")
		MainWindow mw = new MainWindow("SwingChess", messageHandler);
       	
       	messageHandler.start();
       	
       	System.out.println("GameWindow created!");
	}
	
	public void finalize(){
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println("Couldn't close socket: " + e.getMessage());
		}
	}
}