package game;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launcher extends JFrame {

	private Game game;

	/**
	 * Create Window with Game Canvas Element
	 */
	public Launcher() {
		this.setTitle("Bomberman");
		try {
			this.game = new Game();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.game);
		new GUI(this);
		this.setContentPane(panel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void start() {
		this.game.start();
	}

	public void stop() {
		this.game.stop();
	}

}
