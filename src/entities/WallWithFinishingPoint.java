package entities;

import game.Game;
import game.Score;

public class WallWithFinishingPoint extends BreakableWall {
	Score s = new Score();

	/*
	 * @param x
	 * 
	 * @param y
	 */
	public WallWithFinishingPoint(int x, int y) {
		super(x, y);
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
			((BombAnimation) e).addAfterExplosion(new Finishpoint(this.x,
					this.y));
			this.s.setScore(200);
			System.out.println(this.s.getScore());
		}
	}

}
