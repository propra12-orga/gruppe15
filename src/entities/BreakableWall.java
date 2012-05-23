package entities;

import game.Game;
import graphics.Sprite;

public class BreakableWall extends Wall {

	public BreakableWall(int x, int y) {
		super(x, y);
		this.images = Sprite.load("wall_des.png", 100, 100);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof BombAnimation) {
			this.removed = true;
			Game.staticBackground.add(new Background(this.x, this.y));
		}
	}
}
