package game.highscore;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Oktay
 * 
 */
public class HighscoreGui extends JPanel {

	/**
	 * This is the frame of the highscore
	 */
	JFrame frame;
	/**
	 * This is the main frame
	 */
	Container lframe;

	/**
	 * Creates the parts for the highscore frame
	 * 
	 * @param frame2
	 *            Main frame
	 */
	public HighscoreGui(Container frame2) {
		HighscoreManager hm = new HighscoreManager();
		JTextArea text = new JTextArea(hm.getHighscoreString());
		text.setEditable(false);
		this.add(text);
		this.lframe = frame2;
	}

	/**
	 * Creates the highscore frame and adds part to it
	 */
	public void createAndShowHighscore() {

		this.frame = new JFrame("Highscore");
		this.frame.setLocationRelativeTo(this.lframe);
		this.frame.add(new HighscoreGui(this.lframe));

		this.frame.pack();
		this.frame.setVisible(true);
	}
}
