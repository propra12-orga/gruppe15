package entities.items;

import entities.Player;
import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

public class SlowDown extends Item {

	public SlowDown(int x, int y) {
		super(x, y);
		this.images = Sprite.load("items/freeze.png", 48, 48);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.items.Item#use(entities.Player)
	 */
	@Override
	public void use(Player p) {
		p.speed /= 1.2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.items.Item#remove(entities.Player)
	 */
	@Override
	public void remove(Player p) {
		p.speed *= 1.2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.items.Item#action(entities.Player)
	 */
	@Override
	public void action(Player p) {
		// TODO Auto-generated method stub

	}
}
