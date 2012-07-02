package entities.items;

import entities.Player;
import entities.Trap;
import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

import level.Box;

public class Falle extends Item {

	public Falle(int x, int y) {
		super(x, y);
		this.images = Sprite.load("items/trap_n.png", 100, 100);
	}

	@Override
	public void use(Player p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Player p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void action(Player p) {
		int b_x = Box.fitToBlock(p.x);
		int b_y = Box.fitToBlock(p.y);
		Game.entities.add(new Trap(b_x, b_y, p));
		p.item = null;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, Game.BLOCK_SIZE,
				Game.BLOCK_SIZE, null);
	}

}
