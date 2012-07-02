package entities;

import game.Game;
import graphics.Image;

import java.awt.Graphics;
import java.util.List;

import level.Box;

/**
 * Main Class for all Entities Needs to be extended by all Entities
 */
public class Entity {
	public Box box;
	public int y;
	public int x;
	public boolean isBlocking = true;
	public boolean needsStep = true;
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
		this.box = new Box(x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);

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
		return this.box.intersects(e.box);
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
	public void action(double delta) {
		List<Entity> es = Game.getEntities(this.box);
		es = Game.unique(es);
		for (Entity e : es) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
			}
		}
	}
}
