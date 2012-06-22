package entities;

import game.Game;
import game.highscore.PointManager;

public class WallWithFinishingPoint extends BreakableWall {
	PointManager p = new PointManager();

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
			this.p.addPoints(200);
			// System.out.println(this.s.getScore());
		}
	}

}
