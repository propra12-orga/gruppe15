package entities;

import java.util.Random;

import entities.items.Falle;
import entities.items.Item;
import game.Game;
import graphics.Sprite;

/**
 * Wall which can be destroyed by Bomb
 */
public class BreakableWall extends Wall {

	private Item item;

	/**
	 * @param x
	 *            Upper Left Corner of the wall
	 * @param y
	 *            Upper Left Corner of the wall
	 */

	public BreakableWall(int x, int y) {
		super(x, y);
		int z = new Random().nextInt(12);
		// int z = (int) (Math.random() * 10);
		if (z < 4) {
			this.images = Sprite.load("w1.png", 100, 100);
		} else if (z < 8) {
			this.images = Sprite.load("w2.png", 100, 100);
		} else {
			this.images = Sprite.load("w3.png", 100, 100);
		}

		if (new Random().nextInt(10) > 8) {
			this.item = new Falle(x, y);
		}

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
			((BombAnimation) e).owner.pm.addPoints(200);
			if (this.item != null) {
				((BombAnimation) e).addAfterExplosion(this.item);
			}
		}
	}
}
