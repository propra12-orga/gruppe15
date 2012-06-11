package level;

import game.Game;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Box extends Rectangle {
	/**
	 * 
	 */
	public static final int COLLIDE_LEFT = 1;
	/**
	 * 
	 */
	public static final int COLLIDE_RIGHT = 2;
	/**
	 * 
	 */
	public static final int COLLIDE_DOWN = 3;
	/**
	 * 
	 */
	public static final int COLLIDE_UP = 4;

	/**
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 */
	public Box(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	/**
	 * @param x1
	 * @param y1
	 */
	public void update(int x1, int y1) {
		this.setLocation(x1, y1);
	}

	/**
	 * @param b
	 *            Box
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> collideDirection(Box b) {
		ArrayList<Integer> theArray = new ArrayList<Integer>();

		Line2D oben = new Line2D.Double(b.getX(), b.getY(), (b.getX() + b.getWidth()), b.getY());

		if (this.intersectsLine(oben)) {
			theArray.add(Box.COLLIDE_DOWN);
		}

		Line2D unten = new Line2D.Double(b.getX(), b.getY() + b.getHeight(), (b.getX() + b.getWidth()), b.getY()
				+ b.getHeight());

		if (this.intersectsLine(unten)) {
			theArray.add(Box.COLLIDE_UP);
		}

		Line2D links = new Line2D.Double(b.getX(), b.getY(), b.getX(), b.getY() + b.getHeight());

		if (this.intersectsLine(links)) {
			theArray.add(Box.COLLIDE_RIGHT);
		}

		Line2D rechts = new Line2D.Double(b.getX() + b.getWidth(), b.getY(), b.getX() + b.getWidth(), b.getY()
				+ b.getHeight());

		if (this.intersectsLine(rechts)) {
			theArray.add(Box.COLLIDE_LEFT);
		}

		return theArray;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Rectangle#toString()
	 */
	@Override
	public String toString() {
		return "Box [x1=" + this.getX() + ", y1=" + this.getY() + "]";
	}

	/**
	 * @param i
	 * @return int
	 */
	public static int fitToBlock(int i) {
		return i - (i % Game.BLOCK_SIZE);
	}

	/**
	 * @param b
	 * @param mode
	 * @return int
	 */
	public int getDistance(Box b, int mode) {
		int tmp;
		if (mode == 1) {
			tmp = Box.fitToBlock(b.x) - Box.fitToBlock(this.x);
			return tmp / Game.BLOCK_SIZE;
		} else {
			tmp = Box.fitToBlock(b.y) - Box.fitToBlock(this.y);
			return tmp / Game.BLOCK_SIZE;
		}
	}
}
