package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import enums.NetworkInputType;
import game.Debug;

/**
 * @author Philipp
 * 
 */
public class ClientOnServer extends Thread {

	/**
	 * Playernumber
	 */
	public int playernumber;
	/**
	 * Socket to Client
	 */
	private Socket socket;
	/**
	 * Input Stream
	 */
	private BufferedReader inStream;
	/**
	 * Output Stream
	 */
	private DataOutputStream outStream;
	/**
	 * Queue of events from Gameserver
	 */
	private CopyOnWriteArrayList<Input> queue;

	/**
	 * Constructor
	 * 
	 * @param socket
	 *            Socket from Serversocket.accept
	 * @param playernumber
	 *            Playernumer
	 * @param queue
	 *            Queue from Gameserver
	 * @throws IOException
	 */
	public ClientOnServer(Socket socket, int playernumber, CopyOnWriteArrayList<Input> queue) throws IOException {
		this.socket = socket;
		this.socket.setSoTimeout(30);
		this.playernumber = playernumber;
		this.inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.outStream = new DataOutputStream(socket.getOutputStream());
		this.queue = queue;
	}

	/**
	 * Notify Client about Map and Playernumber
	 * 
	 * @param map
	 * @throws IOException
	 */
	public void sendMap(String map) throws IOException {
		this.outStream.write(("me:" + this.playernumber + ";\n").getBytes());
		this.outStream.write(("m:" + map + ";\n").getBytes());
	}

	/**
	 * Send Events to Client
	 * 
	 * @param in
	 *            {@link Input}
	 * @throws IOException
	 */
	public void sendInput(Input in) throws IOException {
		this.outStream.write(("input:" + in.playerID + "," + in.type + "," + in.x + "," + in.y + ";\n").getBytes());
	}

	/*
	 * Read input stream and handle incoming events
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				String command = this.inStream.readLine();
				Input in = null;
				if (command != null) {
					if (command.startsWith("input")) {
						in = new Input();
						command = command.replace("input:", "").replace(";", "");
						String[] parts = command.split(",");
						in.playerID = Integer.valueOf(parts[0]);
						in.type = NetworkInputType.valueOf(parts[1]);
						if ((in.type == NetworkInputType.PLAYER) || (in.type == NetworkInputType.BOMB)) {
							in.x = Integer.valueOf(parts[2]);
							in.y = Integer.valueOf(parts[3]);
						}
					}

					if (in != null) {
						this.queue.add(in);
					}
				}
			} catch (java.net.SocketTimeoutException ex) {

			} catch (Exception e) {
				Debug.log(Debug.ERROR, "Can't read from Client. Disconnected?");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}
}
