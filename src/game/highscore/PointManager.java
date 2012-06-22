package game.highscore;

public class PointManager {
	private static int totalPoints = 0;

	public void addPoints(int points) {
		PointManager.totalPoints = PointManager.totalPoints + points;
	}

	public int getPoints() {
		return PointManager.totalPoints;
	}
}
