package se.kth.mlanglet.server;

/**
 * Listener allowing a Game instance to tell the ConnectionHandler that the game is over
 * @author Mathias
 *
 */
public interface IGameListener {
	public void gameOver(GameHandler game);
}
