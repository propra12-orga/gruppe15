package game.highscore;

/**
 * @author Oktay
 * 
 */
public class PointManager {
	/**
	 * Integer for the sum of all points of one player
	 */
	private int totalPoints = 0;

	/**
	 * Sums the points which a player gets with the total points
	 * 
	 * @param points
	 */
	public void addPoints(int points) {
		this.totalPoints = this.totalPoints + points;
	}

	/**
	 * Getter method for total points
	 * 
	 * @return total points of one player
	 */
	public int getPoints() {
		return this.totalPoints;
	}
}
