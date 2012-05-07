package entities;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import level.Box;

public class Ball extends Entity {

	public int radius = 10;
	private int speedY;
	public int speedX;
	private Color color;
	private int oldX;
	private int oldY;

	public Ball(int x, int y) {
		super(x, y);
		this.speedX = (int) (Math.random() * 5);
		this.speedY = (int) (Math.random() * 5);
		this.isBlocking = true;
		this.box = new Box(this.x, this.y, this.x + this.radius, this.y + this.radius);
		this.color = Color.RED;
	}

	@Override
	public void step(double delta) {

		this.x += this.speedX;
		this.y += this.speedY;

		if ((this.x + (this.radius / 2)) >= (Game.GAME_WIDTH * Game.SCALE)) {
			this.speedX *= -1;
			this.x = (Game.GAME_WIDTH * Game.SCALE) - (this.radius / 2);
		} else if ((this.x - (this.radius / 2)) <= 0) {
			this.speedX *= -1;
			this.x = this.radius / 2;
		}

		if (this.y >= (Game.GAME_HEIGHT * Game.SCALE)) {
			this.speedY *= -1;
			this.y = (Game.GAME_HEIGHT * Game.SCALE) - (this.radius / 2);
		} else if ((this.y - (this.radius / 2)) <= 0) {
			this.speedY *= -1;
			this.y = this.radius / 2;
		}

		this.box.update(this.x, this.y, this.x + this.radius, this.y + this.radius);

		List<Entity> es = Game.getEntities(this.box.x1, this.box.y1, this.box.x2, this.box.y2);
		for (Entity e : es) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
			}
		}

		this.box.update(this.x, this.y, this.x + this.radius, this.y + this.radius);

	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Wall) {
			this.color = Color.blue;
			this.speedX *= -1;
			this.speedY *= -1;
			this.x += this.speedX;
			this.y += this.speedY;
		} else if (e instanceof Ball) {
			this.speedX *= -1;
			this.speedY *= -1;
			this.x += this.speedX;
			this.y += this.speedY;
			this.color = Color.GREEN;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.radius, this.radius);
		this.color = Color.red;
	}

}
