package network;

import java.net.InetAddress;

public class Server implements Comparable<Server> {

	public InetAddress host;
	public int port;
	private String name;

	public Server(InetAddress host, int port, String name) {
		this.host = host;
		this.port = port;
		this.name = name.trim();
	}

	@Override
	public int compareTo(Server s) {
		if ((this.host == s.host) && (this.port == s.port)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return this.name + " (" + this.host.getHostAddress() + ")";
	}

}
