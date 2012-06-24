package game.highscore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HighscoreGui extends JPanel implements ActionListener {

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

	public void createAndShowGUI() {

		JFrame frame = new JFrame("Highscore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new HighscoreGui());

		frame.pack();
		frame.setVisible(true);
	}
}
