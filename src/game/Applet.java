package game;

import gui.GUI;

import javax.swing.JApplet;

public class Applet extends JApplet {

	public Game game;

	@Override
	public void init() {
		this.setIgnoreRepaint(true);
		this.setVisible(true);
		this.game = Game.getInstance();
		this.game.setFrame(this);
		this.add(this.game);

		new GUI(this, this.game);

		this.setSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		// Applet.this.setVisible(true);
	}

	@Override
	public void start() {
		this.game.start();
	}

	@Override
	public void stop() {
		this.game.stop();
		super.destroy();
	}
}
