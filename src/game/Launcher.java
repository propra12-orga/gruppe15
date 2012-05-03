package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launcher extends JFrame {

	private Game game;

	public Launcher() {
		this.game = new Game();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.game);

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
