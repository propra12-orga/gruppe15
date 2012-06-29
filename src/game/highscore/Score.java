package game.highscore;

import java.io.Serializable;

/**
 * @author Oktay
 * 
 */
public class Score implements Serializable {
	/**
	 * Integer for score
	 */
	private int score;
	/**
	 * String for name
	 */
	private String name;

	/**
	 * Getter method for score
	 * 
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Getter method for name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Saves the parameters into local variables
	 * 
	 * @param name
	 * @param score
	 */
	public Score(String name, int score) {
		this.score = score;
		this.name = name;
	}

}