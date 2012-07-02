package network;

import game.Debug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Philipp
 * 
 */
public class Discover extends Thread {

	/**
	 * Wait for response from Broadcast
	 */
	private DatagramSocket waitSocket;
	/**
	 * Found Servers
	 */
	public ArrayList<Server> servers;
	/**
	 * Broadcast connection
	 */
	private DatagramSocket broadcast;
	/**
	 * Broadcast packet
	 */
	private DatagramPacket broadcastPacket;
	/**
	 * 
	 */
	public boolean running = true;
	/**
	 * Wait for Callback from DiscoverServer on this Port
	 */
	public final static int CALLBACK_PORT = 1338;

	/**
	 * Constructor
	 */
	public Discover() {
		try {
			this.waitSocket = new DatagramSocket(Discover.CALLBACK_PORT);
			this.broadcast = new DatagramSocket();
		} catch (SocketException e1) {
			Debug.log(Debug.ERROR, "Can't create Datagram");
			e1.printStackTrace();
		}
		this.servers = new ArrayList<Server>();
		byte[] buffer = new byte[1];
		try {
			InetAddress broadcastIP = InetAddress.getByName("255.255.255.255");
			this.broadcastPacket = new DatagramPacket(buffer, buffer.length, broadcastIP, DiscoverServer.BROADCAST_PORT);
		} catch (UnknownHostException e) {
			Debug.log(Debug.ERROR, "Can't create Broadcast");
		}
	}

	/*
	 * Search for Servers
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (this.running) {
			try {
				this.broadcast.send(this.broadcastPacket);
			} catch (IOException e1) {
				Debug.log(Debug.ERROR, "Can't send Discovery Broadcast");
			}

			byte[] buffer = new byte[255];
			DatagramPacket wait = new DatagramPacket(buffer, buffer.length);
			try {
				// Immer nur 200 Millisekunden warten
				this.waitSocket.setSoTimeout(200);
				this.waitSocket.receive(wait);
				String res = new String(wait.getData());
				if (res.startsWith(DiscoverServer.KEYWORD_ANNOUNCE)) {

					Server s = new Server(wait.getAddress(), GameServer.GAME_PORT, res.replace(
							DiscoverServer.KEYWORD_ANNOUNCE, ""));
					if (this.servers.isEmpty() || (Collections.binarySearch(this.servers, s) < 0)) {
						this.servers.add(s);
						Debug.log(Debug.DEBUG, "Found new Server");
					}
				}
			} catch (IOException e) {

			}
		}
		this.broadcast.close();
		this.waitSocket.close();
	}
}
