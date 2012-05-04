package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DiscoverServer implements Runnable {

	final static String KEYWORD_ANNOUNCE = "SERVER";
	final static int BROADCAST_PORT = 1337;

	private DatagramSocket announceSocket;

	public DiscoverServer() throws SocketException {
		this.announceSocket = new DatagramSocket(1337);
	}

	@Override
	public void run() {
		while (true) {
			byte[] buffer = new byte[DiscoverServer.KEYWORD_ANNOUNCE.length()];
			DatagramPacket wait = new DatagramPacket(buffer, buffer.length);
			try {
				this.announceSocket.receive(wait);

				DatagramPacket response = new DatagramPacket(DiscoverServer.KEYWORD_ANNOUNCE.getBytes(),
						DiscoverServer.KEYWORD_ANNOUNCE.length(), wait.getAddress(), 1338);

				this.announceSocket.send(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
