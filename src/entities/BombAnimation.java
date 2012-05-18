package entities;

import game.Game;
import graphics.Image;
import graphics.Sprite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import level.Box;

public class BombAnimation extends Entity {

	private Image[][] explosionImages;
	private int explosionTime;
	private int exposionTimeDefault;

	public BombAnimation(int x, int y) {
		super(x, y);
		this.explosionImages = Sprite.load("bombe_all.png", 100, 100);
		this.exposionTimeDefault = 40;
		this.explosionTime = this.exposionTimeDefault;
	}

	@Override
	public void action(double delta) {
		if (this.explosionTime > 0) {
			this.explosionTime--;
		} else {
			this.removed = true;
		}

		List<Entity> entities1 = null, entities2 = null;

		if (this.explosionTime < (this.exposionTimeDefault * 0.7)) {
			entities1 = Game.getEntities(new Box(this.x - (2 * Game.BLOCK_SIZE), this.y, Game.BLOCK_SIZE * 5,
					Game.BLOCK_SIZE));
			entities2 = Game.getEntities(new Box(this.x, this.y - (2 * Game.BLOCK_SIZE), Game.BLOCK_SIZE,
					Game.BLOCK_SIZE * 5));
		} else if (this.explosionTime < (this.exposionTimeDefault * 0.9)) {
			entities1 = Game
					.getEntities(new Box(this.x - Game.BLOCK_SIZE, this.y, Game.BLOCK_SIZE * 3, Game.BLOCK_SIZE));
			entities2 = Game
					.getEntities(new Box(this.x, this.y - Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE * 3));
		} else {
			entities1 = Game.getEntities(new Box(this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
			entities2 = new ArrayList<Entity>();
		}

		entities1.addAll(entities2);

		for (Entity e : entities1) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.explosionImages[1][0]).image, this.x, this.y, Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
		if (this.explosionTime < (this.exposionTimeDefault * 0.9)) {
			g.drawImage((this.explosionImages[0][0]).image, this.x - Game.BLOCK_SIZE, this.y, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[0][0]).image, this.x + Game.BLOCK_SIZE, this.y, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[2][0]).image, this.x, this.y - Game.BLOCK_SIZE, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[2][0]).image, this.x, this.y + Game.BLOCK_SIZE, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
		}

		if (this.explosionTime < (this.exposionTimeDefault * 0.7)) {
			g.drawImage((this.explosionImages[0][0]).image, this.x - (2 * Game.BLOCK_SIZE), this.y, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[0][0]).image, this.x + (2 * Game.BLOCK_SIZE), this.y, Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[2][0]).image, this.x, this.y - (2 * Game.BLOCK_SIZE), Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
			g.drawImage((this.explosionImages[2][0]).image, this.x, this.y + (2 * Game.BLOCK_SIZE), Game.BLOCK_SIZE,
					Game.BLOCK_SIZE, null);
		}

	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			e.removed = true;
		}
		if (e instanceof BreakableWall) {
			e.removed = true;
		}
	}
}
