package network;

import enums.NetworkInputType;

public class Input {
	public int playerID;
	public NetworkInputType type;
	public int x;
	public int y;

	public Input() {
	}

	@Override
	public String toString() {
		return "Input [playerID=" + this.playerID + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + "]";
	}
}
