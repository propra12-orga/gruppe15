package entities;

import game.Game;
import game.Gameend;

import java.awt.Color;
import java.awt.Graphics;

public class Finishpoint extends Entity {

	private Player player;
	private int winDelay = 10;

	public Finishpoint(int x, int y) {
		super(x, y);
		this.isBlocking = false;
		this.needsStep = true;
		this.player = null;
	}

	@Override
	public void action(double delta) {

		if ((this.player != null)) {
			this.winDelay--;
			if (this.winDelay == 0) {
				Game.getInstance().gameEnd(this.player, Gameend.finishReached);
			}
		}
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			this.player = (Player) e;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawOval(this.x + 5, this.y + 5, 40, 40);

	}

}
