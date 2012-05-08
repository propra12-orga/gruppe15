package entities;

import graphics.Sprite;

public class Bomb extends Entity {
	public Bomb(int x, int y) {
		super(x, y);
		this.isBlocking = true;
		this.images = Sprite.load("bomb_small.png", 100, 100);
	}

	public void explode() {

	}
}
