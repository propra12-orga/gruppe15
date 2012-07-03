package entities.items;

import entities.Entity;
import entities.Player;

public abstract class Item extends Entity {

	/**
	 * Default Constructor for alle Items
	 * 
	 * @param x
	 * @param y
	 */
	public Item(int x, int y) {
		super(x, y);
		this.needsStep = false;
	}

	/**
	 * Gets called when a player gets the item
	 * 
	 * @param p
	 */
	public abstract void use(Player p);

	/**
	 * Gets called when a player gets another item
	 * 
	 * @param p
	 */
	public abstract void remove(Player p);

	/**
	 * Gets called then the player presses "k"
	 * 
	 * @param p
	 */
	public abstract void action(Player p);

}
