package entities;

import enums.Gameend;
import game.Game;
import graphics.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Finishpoint for the game
 */
public class Finishpoint extends Entity {

	/**
	 * Player who reaches finish point
	 */
	private Player player;
	/**
	 * Time before win message is shown
	 */
	private int winDelay = 10;

	/**
	 * @param x
	 *            Upper Left Corner of the finish point
	 * @param y
	 *            Upper Left Corner of the finish point
	 */
	public Finishpoint(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.needsStep = true;
		this.images = Sprite.load("finish.png", 100, 100,
				BufferedImage.TYPE_INT_RGB);
		this.player = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#action(double)
	 */
	@Override
	public void action(double delta) {

		if ((this.player != null)) {
			this.winDelay--;
			if (this.winDelay == 0) {
				this.player.pm.addPoints(5000);
				Game.getInstance().gameEnd(this.player, Gameend.finishReached);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#collide(entities.Entity)
	 */
	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			this.player = (Player) e;
		}
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
