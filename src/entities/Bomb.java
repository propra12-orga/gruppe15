package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

import level.Box;

public class Bomb extends Entity {
	/**
	 * @param x
	 * @param y
	 * @param owner
	 */
	public Bomb(int x, int y, Player owner) {
		super(x, y);
		this.images = Sprite.load("bomb_small.png", 50, 49);
		this.box = new Box(this.x + (Game.BLOCK_SIZE / 4), this.y + (Game.BLOCK_SIZE / 4), Game.BLOCK_SIZE / 2,
				Game.BLOCK_SIZE / 2);
		this.owner = owner;
		this.explosionDelay = 50;
	}

	public Bomb(int x, int y, int playerID) {
		this(x, y, (Player) Game.players.get(playerID));
	}

	/**
	 * Player who drops a bomb
	 */
	public Player owner;
	/**
	 * Explosion time
	 */
	private int explosionDelay;

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#action(double)
	 */
	@Override
	public void action(double delta) {
		if (this.explosionDelay <= 0) {
			this.removed = true;
			Game.entities.add(new BombAnimation(this.x, this.y));
		} else {
			this.explosionDelay--;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see entities.Entity#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x + (Game.BLOCK_SIZE / 4), this.y + (Game.BLOCK_SIZE / 4),
				Game.BLOCK_SIZE / 2, Game.BLOCK_SIZE / 2, null);
	}

	/**
	 * Method which triggers chain reaction if existing bomb is inside an
	 * explosion
	 */
	public void forceExplosion() {
		this.removed = true;
		Game.entities.add(new BombAnimation(this.x, this.y));
	}
}
