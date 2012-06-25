package game.highscore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HighscoreGui extends JPanel implements ActionListener {

	JFrame frame;

	public HighscoreGui() {
		HighscoreManager hm = new HighscoreManager();
		JTextArea text = new JTextArea(hm.getHighscoreString());
		text.setEditable(false);
		this.add(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void createAndShowHighscore() {

		this.frame = new JFrame("Highscore");

		this.frame.add(new HighscoreGui());

		this.frame.pack();
		this.frame.setVisible(true);
	}
}
