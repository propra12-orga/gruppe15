package network;

import game.Debug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author Philipp
 * 
 */
public class DiscoverServer implements Runnable {

	/**
	 * Keyword to detect a server
	 */
	final static String KEYWORD_ANNOUNCE = "SERVER_";
	/**
	 * Listen for on this port
	 */
	final static int BROADCAST_PORT = 1337;

	/**
	 * Broadcast connection
	 */
	private DatagramSocket announceSocket;
	/**
	 * Name of the Server
	 */
	private String name;

	/**
	 * @param name
	 * @throws SocketException
	 */
	public DiscoverServer(String name) throws SocketException {
		this.announceSocket = new DatagramSocket(DiscoverServer.BROADCAST_PORT);
		this.name = name;
		Debug.log(Debug.ERROR, "DiscoverServer started");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			String res = DiscoverServer.KEYWORD_ANNOUNCE + this.name;
			byte[] buffer = new byte[res.length()];
			DatagramPacket wait = new DatagramPacket(buffer, buffer.length);
			try {
				this.announceSocket.receive(wait);

				DatagramPacket response = new DatagramPacket(res.getBytes(), res.getBytes().length, wait.getAddress(),
						Discover.CALLBACK_PORT);

				this.announceSocket.send(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
