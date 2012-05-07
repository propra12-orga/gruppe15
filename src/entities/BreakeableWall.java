package entities;

import graphics.Sprite;

public class BreakeableWall extends Wall {

	public BreakeableWall(int x, int y) {
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
