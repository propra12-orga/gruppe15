package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

import level.Box;

public class Bomb extends Entity {
	private boolean activation;

	public Bomb(int x, int y, boolean activation) {
		super(x, y);
		this.activation = activation;
		this.images = Sprite.load("bomb_small.png", 100, 100);
		this.box = new Box(this.x, this.y, this.x * 2, this.y * 2);
	}

	public void explode() {
		int timer;
		timer = 5;
		while (this.activation) {
			for (int i = 0; i < timer; i++) {

			}

			this.activation = false;
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE,
				Game.BLOCK_SIZE, null);
	}
}
