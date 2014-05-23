package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArrayList;

import level.Loader;
import enums.GameServerStatus;
import game.Debug;

/**
 * @author Philipp
 * 
 */
public class GameServer implements Runnable {

	/**
	 * Port for a Gameserver
	 */
	public final static int GAME_PORT = 4242;
	/**
	 * Map of the Gameserver
	 */
	private String map;
	/**
	 * Number of player required to start game
	 */
	private int required_player;
	/**
	 * Array with Sockets of connected player
	 */
	private CopyOnWriteArrayList<ClientOnServer> connected_players;
	/**
	 * Socket to accept new player
	 */
	private ServerSocket welcomeSocket;
	/**
	 * Status of the Gameserver
	 */
	private GameServerStatus status;
	/**
	 * List with all incoming events
	 */
	private CopyOnWriteArrayList<Input> queue;

	/**
	 * Create a new Gameserver
	 * 
	 * @param map
	 *            Mapfilename
	 * @throws IOException
	 */
	public GameServer(String map) throws IOException {
		this.map = map;
		this.required_player = new Loader().parseForMultiplayer(map);
		this.connected_players = new CopyOnWriteArrayList<ClientOnServer>();
		this.welcomeSocket = new ServerSocket(GameServer.GAME_PORT);
		this.status = GameServerStatus.WAITING;
		this.queue = new CopyOnWriteArrayList<Input>();
	}

	/*
	 * 
	 * Start the Gameserver
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Debug.log(Debug.DEBUG, "Game Server starting");
		try {
			Debug.log(Debug.DEBUG, "Local Server IP: "
					+ InetAddress.getLocalHost().getHostAddress());
			Debug.log(Debug.DEBUG,
					"External Server IP: " + GameServer.getExternalIP());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Debug.log(Debug.DEBUG, "waiting for clients");
		while (true) {
			try {
				if (this.connected_players.size() < this.required_player) {
					Socket connectionSocket = this.welcomeSocket.accept();
					ClientOnServer c = new ClientOnServer(connectionSocket,
							this.connected_players.size(), this.queue);
					c.start();
					this.connected_players.add(c);
					Debug.log(Debug.DEBUG, "New Client connected ("
							+ connectionSocket.getInetAddress() + ")");
				}

				if ((this.connected_players.size() == this.required_player)
						&& (this.status == GameServerStatus.WAITING)) {
					this.initGame();
				}

				if (this.status == GameServerStatus.RUNNING) {
					this.sendInputs();
				}

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send init to all player, when enough players are connected
	 */
	private void initGame() {
		this.status = GameServerStatus.STARTING;
		Debug.log(Debug.DEBUG, "Match starting");

		for (ClientOnServer c : this.connected_players) {
			try {
				c.sendMap(this.map);
			} catch (IOException e) {
				Debug.log(Debug.ERROR,
						"Can't send Data to player, maybe disconnected");
			}
		}
		this.status = GameServerStatus.RUNNING;
		Debug.log(Debug.DEBUG, "Match started");
	}

	/**
	 * Send all events in queue to the players
	 */
	public void sendInputs() {
		for (Input in : this.queue) {
			for (ClientOnServer c : this.connected_players) {
				if (in.playerID != c.playernumber) {
					try {
						c.sendInput(in);
					} catch (IOException e) {
						Debug.log(Debug.ERROR,
								"Can't send Data to player, maybe disconnected");
					}
				}
			}
		}
		this.queue.clear();
	}

	public static String getExternalIP() {
		String url = "http://wtfismyip.com/text";
		try {
			String ip = new BufferedReader(new InputStreamReader(
					new URL(url).openStream())).readLine();
			return ip + " (Needs Port " + GameServer.GAME_PORT
					+ " to be forwared)";
		} catch (Exception e) {
			e.printStackTrace();
			return "unknown (maybe no connection to internet)";
		}
	}
}
