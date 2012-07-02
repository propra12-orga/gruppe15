package network;

import game.Debug;

import java.io.IOException;
import java.net.SocketException;

/**
 * @author Philipp
 * 
 */
public class DedicatedServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Debug.setMode(Debug.DEBUG);
		GameServer gameserver;
		try {
			gameserver = new GameServer("Map2");
			Thread gameserver_thread = new Thread(gameserver);
			gameserver_thread.start();
		} catch (IOException e) {
			Debug.log(Debug.ERROR, "Can't start Gameserver");
		}

		DiscoverServer discover;
		try {
			discover = new DiscoverServer("Mein Server");
			Thread discover_thread = new Thread(discover);
			discover_thread.start();
		} catch (SocketException e) {

			e.printStackTrace();
		}
	}
}
