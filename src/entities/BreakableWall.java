package entities;

import game.Game;
import graphics.Sprite;

public class BreakableWall extends Wall {

	/**
	 * @param x
	 * @param y
	 */
	public BreakableWall(int x, int y) {
		super(x, y);
		this.images = Sprite.load("wall_des.png", 100, 100);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#collide(entities.Entity)
	 */
	@Override
	public void collide(Entity e) {
		if (e instanceof BombAnimation) {
			this.removed = true;
			Game.staticBackground.add(new Background(this.x, this.y));
		}
	}
}
