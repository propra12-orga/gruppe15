package game.highscore;

public class PointManager {
	private int totalPoints = 0;

	public void addPoints(int points) {
		this.totalPoints = this.totalPoints + points;
	}

	public int getPoints() {
		return this.totalPoints;
	}
}
