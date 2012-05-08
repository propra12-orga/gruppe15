package entities;

import graphics.Sprite;

public class BreakableWall extends Wall {

	public BreakableWall(int x, int y) {
		super(x, y);
		this.images = Sprite.load("brick_break_small.png", 100, 100);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Ball) {
			this.removed = true;
		}
	}

}
