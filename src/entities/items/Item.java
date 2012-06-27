package entities.items;

import entities.Entity;
import entities.Player;

public abstract class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
		this.needsStep = false;
	}

	public abstract void use(Player p);

	public abstract void remove(Player p);

}
