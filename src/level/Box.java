package level;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Box extends Rectangle {
	public static final int COLLIDE_LEFT = 1;
	public static final int COLLIDE_RIGHT = 2;
	public static final int COLLIDE_DOWN = 3;
	public static final int COLLIDE_UP = 4;

	public Box(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	public void update(int x1, int y1) {
		this.setLocation(x1, y1);
	}

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

	@Override
	public String toString() {
		return "Box [x1=" + this.getX() + ", y1=" + this.getY() + "]";
	}

}
