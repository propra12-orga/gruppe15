package entities;

import game.Game;
import game.KeySettings;
import game.highscore.PointManager;
import graphics.Image;
import graphics.Sprite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import level.Box;

public class Player extends Entity {

	/**
	 * Create variables for width, height, speed, key settings Create array for
	 * images
	 */

	private float width;
	private float height;
	private int speed;
	private KeySettings keys;
	private Image[][] facings;
	private int facing = 0, facing1 = 1, drawdelay = 0;
	public PointManager pm = new PointManager();

	/**
	 * Constructor for default Player sets position, speed, images, height and
	 * width
	 */

	public Player(int x, int y) {
		super(x + 1, y + 1);
		this.facings = Sprite.load("player1.png", 67, 100);
		this.width = (67 / 2);
		this.height = (100 / 2);
		this.box = new Box(this.x, this.y, (int) this.width, (int) this.height);
		this.drawdelay = 7;
		this.speed = 10;
	}

	/**
	 * draws Player on canvas
	 */

	@Override
	public void draw(Graphics g) {
		g.drawImage((this.facings[this.facing][this.facing1]).image, this.x,
				this.y, (int) this.width, (int) this.height, null);
	}

	/**
	 * determines what happens if a key is pressed
	 */
	@Override
	public void action(double delta) {
		boolean moved = false;

		/**
		 * if key "up" is pressed
		 */
		if (this.keys.up.down) {
			this.y = this.y - this.speed;
			this.facing1 = 1;
			if (this.drawdelay <= 0) {
				this.drawdelay = 7;
			} else if (this.drawdelay <= 1) {
				this.facing = 5;
				this.drawdelay--;
			} else if (this.drawdelay <= 2) {
				this.facing = 9;
				this.drawdelay--;
			} else if (this.drawdelay <= 3) {
				this.facing = 8;
				this.drawdelay--;
			} else if (this.drawdelay <= 4) {
				this.facing = 5;
				this.drawdelay--;
			} else if (this.drawdelay <= 5) {
				this.facing = 7;
				this.drawdelay--;
			} else if (this.drawdelay <= 6) {
				this.facing = 6;
				this.drawdelay--;
			} else {
				this.drawdelay--;
				this.facing = 5;

			}
			moved = true;
		}

		/**
		 * if key "up" is pressed
		 */

		if (this.keys.down.down) {
			this.y = this.y + this.speed;
			this.facing1 = 1;
			if (this.drawdelay <= 0) {
				this.drawdelay = 7;
			} else if (this.drawdelay <= 1) {
				this.facing = 0;
				this.drawdelay--;
			} else if (this.drawdelay <= 2) {
				this.facing = 4;
				this.drawdelay--;
			} else if (this.drawdelay <= 3) {
				this.facing = 3;
				this.drawdelay--;
			} else if (this.drawdelay <= 4) {
				this.facing = 0;
				this.drawdelay--;
			} else if (this.drawdelay <= 5) {
				this.facing = 2;
				this.drawdelay--;
			} else if (this.drawdelay <= 6) {
				this.facing = 1;
				this.drawdelay--;
			} else {
				this.drawdelay--;
				this.facing = 0;

			}
			moved = true;
		}

		/**
		 * if key "left" is pressed
		 */
		if (this.keys.left.down) {
			this.x = this.x - this.speed;
			this.facing1 = 0;
			if (this.drawdelay <= 0) {
				this.drawdelay = 7;
			} else if (this.drawdelay <= 1) {
				this.facing = 5;
				this.drawdelay--;
			} else if (this.drawdelay <= 2) {
				this.facing = 9;
				this.drawdelay--;
			} else if (this.drawdelay <= 3) {
				this.facing = 8;
				this.drawdelay--;
			} else if (this.drawdelay <= 4) {
				this.facing = 5;
				this.drawdelay--;
			} else if (this.drawdelay <= 5) {
				this.facing = 7;
				this.drawdelay--;
			} else if (this.drawdelay <= 6) {
				this.facing = 6;
				this.drawdelay--;
			} else {
				this.drawdelay--;
				this.facing = 5;

			}
			moved = true;
		}

		/**
		 * if key "right" is pressed
		 */
		if (this.keys.right.down) {
			this.x = this.x + this.speed;
			this.facing1 = 0;
			if (this.drawdelay <= 0) {
				this.drawdelay = 7;
			} else if (this.drawdelay <= 1) {
				this.facing = 0;
				this.drawdelay--;
			} else if (this.drawdelay <= 2) {
				this.facing = 4;
				this.drawdelay--;
			} else if (this.drawdelay <= 3) {
				this.facing = 3;
				this.drawdelay--;
			} else if (this.drawdelay <= 4) {
				this.facing = 0;
				this.drawdelay--;
			} else if (this.drawdelay <= 5) {
				this.facing = 2;
				this.drawdelay--;
			} else if (this.drawdelay <= 6) {
				this.facing = 1;
				this.drawdelay--;
			} else {
				this.drawdelay--;
				this.facing = 0;

			}
			moved = true;
		}

		/**
		 * if no key is pressed
		 */
		if (moved == false) {
			this.facing = 0;
			this.facing1 = 1;
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

		/**
		 * if key "bomb" is pressed
		 */

		if (this.keys.bomb.down) {
			Game.entities.add(new Bomb(Box.fitToBlock(this.x), Box
					.fitToBlock(this.y), this));

		}
	}

	/**
	 * checks if Player collides with an Entity
	 */

	@Override
	public void collide(Entity e) {
		if (e instanceof Wall) {
			ArrayList<Integer> dir = this.box.collideDirection(e.box);
			if (dir.contains(Box.COLLIDE_LEFT)) {
				this.x = this.x + this.speed;
			}

			if (dir.contains(Box.COLLIDE_RIGHT)) {
				this.x = this.x - this.speed;
			}

			if (dir.contains(Box.COLLIDE_DOWN)) {
				this.y = this.y - this.speed;
			}

			if (dir.contains(Box.COLLIDE_UP)) {
				this.y = this.y + this.speed;
			}
		}
		// TODO: Player shouldn't be instantly kicked off the bomb
		if ((e instanceof Bomb) && (((Bomb) e).owner != this)) {
			ArrayList<Integer> dir = this.box.collideDirection(e.box);

			if (dir.contains(Box.COLLIDE_LEFT)) {
				this.x = this.x + this.speed;
			}

			if (dir.contains(Box.COLLIDE_RIGHT)) {
				this.x = this.x - this.speed;
			}

			if (dir.contains(Box.COLLIDE_DOWN)) {
				this.y = this.y - this.speed;
			}

			if (dir.contains(Box.COLLIDE_UP)) {
				this.y = this.y + this.speed;
			}

		}
	}

	/**
	 * sets KeySettings
	 */
	public void setKeys(KeySettings keys) {
		this.keys = keys;
	}

}
