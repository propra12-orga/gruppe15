package network;

import java.net.InetAddress;

public class Server implements Comparable<Server> {

	public InetAddress host;
	public int port;

	public Server(InetAddress host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public int compareTo(Server s) {
		if ((this.host == s.host) && (this.port == s.port)) {
			return 1;
		} else {
			return 0;
		}
	}

}
