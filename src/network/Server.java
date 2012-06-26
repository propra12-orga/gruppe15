package network;

import java.net.InetAddress;

/**
 * @author Philipp
 * 
 */
public class Server implements Comparable<Server> {

	/**
	 * IP of the Gameserver
	 */
	public InetAddress host;
	/**
	 * Port of the Gameserver
	 */
	public int port;
	/**
	 * Name
	 */
	private String name;

	/**
	 * @param host
	 * @param port
	 * @param name
	 */
	public Server(InetAddress host, int port, String name) {
		this.host = host;
		this.port = port;
		this.name = name.trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Server s) {
		if ((this.host == s.host) && (this.port == s.port)) {
			return 1;
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name + " (" + this.host.getHostAddress() + ")";
	}

}
