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

public class Discover implements Runnable {

	private DatagramSocket waitSocket;
	public ArrayList<Server> servers;
	private DatagramSocket broadcast;
	private DatagramPacket broadcastPacket;

	public Discover() throws SocketException {
		this.waitSocket = new DatagramSocket(1338);
		this.servers = new ArrayList<Server>();
		this.broadcast = new DatagramSocket();
		byte[] buffer = new byte[1];
		try {
			InetAddress broadcastIP = InetAddress.getByName("255.255.255.255");
			this.broadcastPacket = new DatagramPacket(buffer, buffer.length, broadcastIP, DiscoverServer.BROADCAST_PORT);
		} catch (UnknownHostException e) {
			Debug.log(Debug.DEBUG, "Can't create Broadcast");
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.broadcast.send(this.broadcastPacket);
			} catch (IOException e1) {
				Debug.log(Debug.DEBUG, "Can't send Discovery Broadcast");
			}

			byte[] buffer = new byte[DiscoverServer.KEYWORD_ANNOUNCE.length()];
			DatagramPacket wait = new DatagramPacket(buffer, buffer.length);
			try {
				this.waitSocket.receive(wait);

				if (new String(wait.getData()).equals(DiscoverServer.KEYWORD_ANNOUNCE)) {

					Server s = new Server(wait.getAddress(), GameServer.GAME_PORT);
					if (this.servers.isEmpty() || (Collections.binarySearch(this.servers, s) < 0)) {
						this.servers.add(s);
						Debug.log(Debug.DEBUG, "Found new Server");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
