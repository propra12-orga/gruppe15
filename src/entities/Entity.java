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
	protected boolean isBlocking = true;
	protected Image[][] images;
	public boolean removed = false;

	/**
	 * Default Constructor for Entities;
	 * 
	 * @param x
	 *            Upper Left Corner of the Entity (x-Part)
	 * @param y
	 *            Upper Left Corner of the Entity (y-Part)
	 */
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		this.box = new Box(x, y, x + Game.BLOCK_SIZE, y + Game.BLOCK_SIZE);
	}

	/**
	 * Checks if Entity is blocking another entity
	 * 
	 * @param e
	 *            Entity Entity to check
	 * @return boolean
	 */
	public boolean blocks(Entity e) {
		return this.isBlocking;
	}

	/**
	 * Gets called from Game to draw Entity into Canvas
	 * 
	 * @param g
	 *            Window to draw
	 */
	public void draw(Graphics g) {

	}

	/**
	 * Checks if Entity collides with other Entity
	 * 
	 * @param e
	 *            Entity to check
	 * @return boolean
	 */
	public boolean intersect(Entity e) {
		return this.box.intersect(e.box);
	}

	/**
	 * Gets called when two Entities collide
	 * 
	 * @param e
	 *            Entities
	 */
	public void collide(Entity e) {

	}

	/**
	 * Gets called when the entity has to move and check for collisions
	 * 
	 * @param delta
	 */
	public void step(double delta) {
		List<Entity> es = Game.getEntities(this.box.x1, this.box.y1,
				this.box.x2, this.box.y2);
		for (Entity e : es) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
			}
		}
	}
}
