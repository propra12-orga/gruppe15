package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Entity to display the default background
 */
public class Background extends Entity {

	/**
	 * @param x
	 *            Upper Left Corner of the background tile
	 * @param y
	 *            Upper Left Corner of the background tile
	 */
	public Background(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.images = Sprite.load("background.png", 100, 100,
				BufferedImage.TYPE_INT_RGB);
		this.needsStep = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE,
				Game.BLOCK_SIZE, null);
	}

}
