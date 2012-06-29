package game.highscore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoreManager {

	/**
	 * Arraylist which contains the highscore
	 */
	private ArrayList<Score> scores;

	/**
	 * Path and filename for highscore file
	 */
	private static final String HIGHSCORE_FILE = "src/ressources/scores.dat";

	/**
	 * Stream which writes into file
	 */
	ObjectOutputStream outputStream = null;
	/**
	 * Stream which reads from file
	 */
	ObjectInputStream inputStream = null;

	/**
	 * Arralist scores is initialized
	 */
	public HighscoreManager() {
		this.scores = new ArrayList<Score>();
	}

	/**
	 * Returns the arraylist if method is used
	 * 
	 * @return Arraylist scores
	 */
	public ArrayList<Score> getScores() {
		this.loadScoreFile();
		return this.scores;
	}

	/**
	 * Sorts the arraylist
	 */
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(this.scores, comparator);
	}

	/**
	 * Adds a score to the arraylist. If then there more than 10 entries the
	 * last one is deleted after the list was sorted.
	 * 
	 * @param name
	 *            Name of the player
	 * @param score
	 *            Score of the player
	 */
	public void addScore(String name, int score) {
		this.loadScoreFile();
		this.scores.add(new Score(name, score));
		this.sort();
		if (this.scores.size() > 10) {
			this.scores.remove(10);
		}
		this.updateScoreFile();
	}

	/**
	 * Loads the scores from file to the arraylist
	 */
	public void loadScoreFile() {
		try {
			this.inputStream = new ObjectInputStream(new FileInputStream(
					HighscoreManager.HIGHSCORE_FILE));
			this.scores = (ArrayList<Score>) this.inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out
					.println("[Laad] FNF Error: "
							+ e.getMessage()
							+ " Das Programm wird versuchen eine neue Datei zu erstellen.");
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (this.outputStream != null) {
					this.outputStream.flush();
					this.outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Prints the entries of the Arraylist into file
	 */
	public void updateScoreFile() {
		try {
			this.outputStream = new ObjectOutputStream(new FileOutputStream(
					HighscoreManager.HIGHSCORE_FILE));
			this.outputStream.writeObject(this.scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (this.outputStream != null) {
					this.outputStream.flush();
					this.outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Returns the highscore as a String if there are entries. Else a message is
	 * returned.
	 * 
	 * @return Highscore as a String
	 */
	public String getHighscoreString() {
		String highscoreString = "";
		int max = 10;

		ArrayList<Score> scores;
		scores = this.getScores();
		if (scores.size() > 0) {

			int i = 0;
			int x = scores.size();
			if (x > max) {
				x = max;
			}
			while (i < x) {
				highscoreString += (i + 1) + ".\t" + scores.get(i).getName()
						+ "\t\t" + scores.get(i).getScore() + "\n";
				i++;
			}
		} else {
			highscoreString = "Es sind bisher keine Eintr\u00E4ge vorhanden!";
		}
		return highscoreString;
	}

}