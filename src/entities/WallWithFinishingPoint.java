package entities;

import game.Game;
import graphics.Sprite;

public class WallWithFinishingPoint extends BreakableWall {

	/**
	 * @param x
	 * @param y
	 */
	public WallWithFinishingPoint(int x, int y) {
		super(x, y);
		int z = (int) (Math.random() * 10);
		if (z < 4) {
			this.images = Sprite.load("w1.png", 100, 100);
		} else if ((z >= 3) && (z < 8)) {
			this.images = Sprite.load("w2.png", 100, 100);
		} else if (z >= 8) {
			this.images = Sprite.load("w3.png", 100, 100);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.BreakableWall#collide(entities.Entity)
	 */
	@Override
	public void collide(Entity e) {
		if (e instanceof BombAnimation) {
			this.removed = true;
			Game.staticBackground.add(new Background(this.x, this.y));
			Game.entities.add(new Finishpoint(this.x, this.y));
		}
	}
}
