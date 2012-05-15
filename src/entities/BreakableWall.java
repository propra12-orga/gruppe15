package entities;

import graphics.Sprite;

public class BreakableWall extends Wall {

	public BreakableWall(int x, int y) {
		super(x, y);
		this.images = Sprite.load("brick_break_small.png", 100, 100);
	}

	// public void collide(Entity e) {
	// if (e instanceof Player) {
	// this.removed = true;
	// Game.staticBackground.add(new Background(this.x, this.y));
	// }
	// }
}
