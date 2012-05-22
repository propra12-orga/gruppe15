package entities;

import game.Game;
import graphics.Sprite;

import java.awt.Graphics;

import level.Box;

public class Bomb extends Entity {
	public Bomb(int x, int y, Player1 owner) {
		super(x, y);
		this.images = Sprite.load("bomb_small.png", 50, 49);
		this.box = new Box(this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
		this.owner = owner;
		this.explosionDelay = 50;
	}

	public Bomb(int a, int b, Player2 owner2) {
		super(a, b);
		this.images = Sprite.load("bomb_small.png", 50, 49);
		this.box = new Box(this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
		this.owner = this.owner;
		this.explosionDelay = 50;
	}

	public Player1 owner;
	public Player2 owner2;
	private int explosionDelay;

	@Override
	public void action(double delta) {
		if (this.explosionDelay == 0) {
			this.removed = true;
			Game.entities.add(new BombAnimation(this.x, this.y));
		} else {
			this.explosionDelay--;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.images[0][0]).image, this.x, this.y, 40, 40, null);
	}
}
