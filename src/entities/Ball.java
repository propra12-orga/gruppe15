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

	public Ball(int x, int y) {
		super(x, y);
		this.speedX = (int) (Math.random() * 10);
		this.speedY = (int) (Math.random() * 10);
		this.isBlocking = true;
		this.box = new Box(this.x, this.y, this.radius, this.radius);
		this.color = Color.RED;
	}

	@Override
	public void action(double delta) {

		this.x += this.speedX;
		this.y += this.speedY;

		if ((this.x + (this.radius / 2)) >= (Game.GAME_WIDTH)) {
			this.speedX *= -1;
			this.x = (Game.GAME_WIDTH) - (this.radius / 2);
		} else if ((this.x - (this.radius / 2)) <= 0) {
			this.speedX *= -1;
			this.x = this.radius / 2;
		}

		if (this.y >= (Game.GAME_HEIGHT)) {
			this.speedY *= -1;
			this.y = (Game.GAME_HEIGHT) - (this.radius / 2);
		} else if ((this.y - (this.radius / 2)) <= 0) {
			this.speedY *= -1;
			this.y = this.radius / 2;
		}

		this.box.update(this.x, this.y);

		List<Entity> es = Game.getEntities(this.box);
		for (Entity e : es) {
			if (e != this) {
				e.collide(this);
				this.collide(e);
				this.box.update(this.x, this.y);
			}
		}

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