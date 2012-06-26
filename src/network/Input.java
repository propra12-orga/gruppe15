package network;

import enums.NetworkInputType;

public class Input {
	/**
	 * 
	 */
	public int playerID;
	/**
	 * Eventtype
	 * 
	 * @see NetworkInputType
	 */
	public NetworkInputType type;
	/**
	 * x-Position for the event
	 */
	public int x;
	/**
	 * y-Position for the event
	 */
	public int y;

	/**
	 * Empty constructor
	 */
	public Input() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Input [playerID=" + this.playerID + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + "]";
	}
}
