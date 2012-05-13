package entities;

public class Spawnpoint extends Entity {

	public Spawnpoint(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.needsStep = false;
	}

}
