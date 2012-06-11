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
		this.images = Sprite.load("wall_des.png", 100, 100);
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
