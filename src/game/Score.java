package game;

public class Score {
	public static int score = 0;
	private int highscore = 0;

	public int getScore() {
		return Score.score;
	}

	public void setScore(int newScore) {
		Score.score = newScore + Score.score;
	}

	public int getHighscore() {
		return this.highscore;
	}

	public void calcScore() {

	}

}
