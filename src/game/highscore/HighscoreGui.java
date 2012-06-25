package game.highscore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HighscoreGui extends JPanel {

	JFrame frame;

	public HighscoreGui() {
		HighscoreManager hm = new HighscoreManager();
		JTextArea text = new JTextArea(hm.getHighscoreString());
		text.setEditable(false);
		this.add(text);
	}

	public void createAndShowHighscore() {

		this.frame = new JFrame("Highscore");

		this.frame.add(new HighscoreGui());

		this.frame.pack();
		this.frame.setVisible(true);
	}
}
