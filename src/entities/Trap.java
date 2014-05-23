package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Trap extends Entity {

	private Player owner;
	private Player playerKilled;
	private int killDelay;

	public Trap(int x, int y, Player p) {
		super(x, y);
		this.owner = p;
		this.images = Sprite.load("falle.png", 100, 100,
				BufferedImage.TYPE_INT_ARGB);
		this.killDelay = 10;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#collide(entities.Entity)
	 */
	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p != this.owner) {
				this.playerKilled = p;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#action(double)
	 */
	@Override
	public void action(double delta) {

		if ((this.playerKilled != null)) {
			this.killDelay--;
			if (this.killDelay == 0) {
				this.playerKilled.killed(this);
				this.removed = true;
			}
		}
	}
}
