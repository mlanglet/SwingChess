package se.kth.mlanglet.server;

import java.net.Socket;
import java.util.ArrayList;

import se.kth.mlanglet.types.Message;
import se.kth.mlanglet.types.Message.MessageType;

public class GameHandler extends Thread {
	private ArrayList<PlayerHandler> players;
	private IGameListener iGameOver;
	
	public GameHandler(IGameListener iGameOver){
		this.iGameOver = iGameOver;
		players = new ArrayList<PlayerHandler>();
	}
	
	public void run(){
		players.get(0).sendMessage(new Message("You are the white player!", MessageType.HELLO, "Server", true));
		players.get(1).sendMessage(new Message("You are the black player!", MessageType.HELLO, "Server", false));
		
		while(true){		
		}
	}
	
	/**
	 * Sends a given message to all players
	 * @param message
	 */
	public void sendMessage(Message message){
		for(PlayerHandler player : players){
			if(message.getType() == MessageType.MOVE && message.getName().equals(player.getPlayerName()))
				continue;
			player.sendMessage(message);
		}
		
		return;
	}
	
	/**
	 * If the game has two players it's full
	 * @return
	 */
	public boolean isFull(){
		if(players.size() > 1){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Adds a player to this game making the game complete and ready to launch
	 * @param socket
	 */
	public synchronized void addPlayer(Socket socket){
		PlayerHandler player = new PlayerHandler(socket, this);
		players.add(player);
		
		if(isFull()){
			start();
		}
	}

	/**
	 * Called by PlayerHandler when a player leaves
	 * @param player
	 */
	public synchronized void playerLeft(PlayerHandler player) {
		players.remove(player);
		players.get(0).sendMessage(new Message("Other player left, game over!", MessageType.BYE, "Server", false));
		iGameOver.gameOver(this);
	}
}
