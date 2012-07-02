package entities.items;

import entities.Player;
import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

public class Schuh extends Item {

	public Schuh(int x, int y) {
		super(x, y);
		this.images = Sprite.load("items/speed_n.png", 100, 100);
	}

	@Override
	public void use(Player p) {
		p.speed *= 1.2;
	}

	@Override
	public void remove(Player p) {
		p.speed /= 1.2;
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

	@Override
	public void action(Player p) {
		// TODO Auto-generated method stub

	}
}
