package level;

public class Box {
	public int x1;
	public int x2;
	public int y1;
	public int y2;

	public Box(int x1, int y1, int x2, int y2) {
		this.update(x1, y1, x2, y2);
	}

	public boolean intersect(int x1, int y1, int x2, int y2) {
		if ((x1 > this.x2) || (x2 < this.x1) || (y1 > this.y2) || (y2 < this.y1)) {
			return false;
		}
		return true;
	}

	public void update(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean intersect(Box b) {
		return this.intersect(b.x1, b.y1, b.x2, b.y2);
	}

	@Override
	public String toString() {
		return "Box [x1=" + this.x1 + ", x2=" + this.x2 + ", y1=" + this.y1 + ", y2=" + this.y2 + "]";
	}

}
