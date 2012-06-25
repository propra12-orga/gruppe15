package game.highscore;

import game.Launcher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HighscoreGui extends JPanel {

	JFrame frame;
	Launcher lframe;

	public HighscoreGui(Launcher lframe) {
		HighscoreManager hm = new HighscoreManager();
		JTextArea text = new JTextArea(hm.getHighscoreString());
		text.setEditable(false);
		this.add(text);
		this.lframe = lframe;
	}

	public void createAndShowHighscore() {

		this.frame = new JFrame("Highscore");
		this.frame.setLocationRelativeTo(this.lframe);
		this.frame.add(new HighscoreGui(this.lframe));

		this.frame.pack();
		this.frame.setVisible(true);
	}
}
