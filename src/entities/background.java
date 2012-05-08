package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

public class background extends Entity {

	public background(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.images = Sprite.load("background.png", 100, 100);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE,
				Game.BLOCK_SIZE, null);
	}

}
