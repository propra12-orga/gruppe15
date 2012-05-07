package entities;

import game.Game;
import graphics.Image;

import java.awt.Graphics;
import java.util.List;

import level.Box;

public class Entity {
	public Box box;
	protected int y;
	protected int x;
	protected boolean isBlocking;
	protected Image[][] images;
	public boolean removed;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		this.box = new Box(x, y, x + Game.BLOCK_SIZE, y + Game.BLOCK_SIZE);
		this.removed = false;
	}

	public boolean blocks(Entity e) {
		return this.isBlocking;
	}

	public void draw(Graphics g) {

	}

	public boolean intersect(Entity e) {
		return this.box.intersect(e.box);
	}

	public void collide(Entity e) {

	}

	public void step(double delta) {
		List<Entity> es = Game.getEntities(this.box.x1, this.box.y1, this.box.x2, this.box.y2);
		for (Entity e : es) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
			}
		}
	}
}
