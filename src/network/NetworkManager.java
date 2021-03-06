package network;

import entities.Bomb;
import entities.Entity;
import entities.Player;
import enums.Gameend;
import enums.Gamemode;
import enums.NetworkInputType;
import game.Debug;
import game.Game;
import gui.ServerJoinInfo;
import input.KeySettings;
import input.NetworkPlayerKeys;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

import level.Loader;
import level.Point;

public class NetworkManager extends Thread {

	private Server server;
	private Socket socket;
	private BufferedReader inStream;
	private DataOutputStream outStream;
	private CopyOnWriteArrayList<Input> out_queue;
	public int playerID;
	public CopyOnWriteArrayList<NetworkPlayerKeys> networkplayer;
	private JFrame infoWindow;
	private Container parentWindow;

	public NetworkManager(Server server, Container parentWindow) {
		this.server = server;
		this.out_queue = new CopyOnWriteArrayList<Input>();
		this.networkplayer = new CopyOnWriteArrayList<NetworkPlayerKeys>();
		this.parentWindow = parentWindow;
	}

	public boolean connect() {
		try {
			this.socket = new Socket(this.server.host, this.server.port);
			this.socket.setSoTimeout(30);
		} catch (IOException e) {
			Debug.log(Debug.ERROR, "Can't connect to gameserver");
			return false;
		}
		try {
			this.infoWindow = new ServerJoinInfo(this.parentWindow);
			this.inStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.outStream = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			Debug.log(Debug.ERROR, "Can't get input/output stream");
		}
		return true;
	}

	@Override
	public void run() {
		while (true) {
			if (this.out_queue.isEmpty() == false) {
				this.sendCommand();
			}

			try {
				String command = this.inStream.readLine();
				Input in = null;
				if (command.startsWith("input:")) {
					in = new Input();
					command = command.replace("input:", "").replace(";", "");
					String[] parts = command.split(",");
					in.playerID = Integer.valueOf(parts[0]);
					in.type = NetworkInputType.valueOf(parts[1]);
					in.x = Integer.valueOf(parts[2]);
					in.y = Integer.valueOf(parts[3]);

					if (in.type == NetworkInputType.BOMB) {
						Game.entities.add(new Bomb(in.x, in.y, in.playerID));
					} else if (in.type == NetworkInputType.PLAYER) {
						for (Entity e : Game.players) {
							Player player = (Player) e;
							if (player.networkID == in.playerID) {
								player.setPosition(in.x, in.y);
								break;
							}
						}
					} else if (in.type == NetworkInputType.PLAYER_DEAD) {
						ArrayList<Player> alive = new ArrayList<Player>();
						for (Entity e : Game.players) {
							Player player = (Player) e;
							if (player.networkID == in.playerID) {
								player.removed = true;
							}

							if (player.removed == false) {
								alive.add(player);
							}
						}
						if (alive.size() == 1) {
							if (alive.get(0).networkID == this.playerID) {
								Game.getInstance().gameEnd(alive.get(0), Gameend.lastAlive);
							}
						}
					}
				} else if (command.startsWith("me:")) {
					this.playerID = Integer.valueOf(command.replace("me:", "").replace(";", ""));
					Debug.log(Debug.DEBUG, "PlayerID: " + this.playerID);
				} else if (command.startsWith("m:")) {
					Game.gamemode = Gamemode.NETWORK;

					String mapname = command.replace("m:", "").replace(";", "");

					Game.getInstance().init(mapname);
					ArrayList<Point> spawns = new Loader().getSpawnPoints(mapname);
					for (int i = 0; i < spawns.size(); i++) {
						KeySettings keys;
						Player p;
						Point po = spawns.get(i);
						if (i == this.playerID) {
							p = new Player(po.x * Game.BLOCK_SIZE, po.y * Game.BLOCK_SIZE);
							keys = Game.getKeySettings(0);
							p.setKeys(keys);
						} else {
							keys = new NetworkPlayerKeys(i);
							p = new Player(po.x * Game.BLOCK_SIZE, po.y * Game.BLOCK_SIZE);
							p.setKeys(keys);
						}
						p.setKeys(keys);
						p.networkID = i;
						Game.players.add(p);
						Game.entities.add(p);
					}
					this.infoWindow.setVisible(false);
					this.infoWindow.dispose();
					this.infoWindow = null;
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}

			} catch (java.net.SocketTimeoutException ex) {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendCommand() {
		for (Input in : this.out_queue) {
			try {
				this.outStream.write(("input:" + this.playerID + "," + in.type + "," + in.x + "," + in.y + ";\n")
						.getBytes());
			} catch (IOException e) {
				Debug.log(Debug.ERROR, "Can't send to Server. Server down ?");
			}
		}
		this.out_queue.clear();
	}

	public void send(Input in) {
		this.out_queue.add(in);
	}
}
