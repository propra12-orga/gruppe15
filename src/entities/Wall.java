package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Normal Wall
 */
public class Wall extends Entity {

	/**
	 * Constructor for default Wall. Sets position and image
	 */

	public Wall(int x, int y) {
		super(x, y);
		this.images = Sprite.load("wall.png", 100, 100);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
	}

}
