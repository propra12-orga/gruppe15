package entities;

public class Finishpoint extends Entity {

	public Finishpoint(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.needsStep = false;
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {

		}
	}

}
