package entities.items;

import entities.Player;

public class Schuh extends Item {

	public Schuh(int x, int y) {
		super(x, y);
	}

	@Override
	public void use(Player p) {
		p.speed *= 1.2;
	}

	@Override
	public void remove(Player p) {
		p.speed /= 1.2;
	}

}
