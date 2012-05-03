package entities;

import game.Game;

import java.awt.Graphics;
import java.util.List;

import level.Box;

public class Entity {
	public Box box;
	private int y;
	private int x;
	private boolean isBlocking;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		this.box = new Box(x, y, x, y);
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