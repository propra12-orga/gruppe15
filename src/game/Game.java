package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import level.Box;
import level.Generator;
import level.Loader;
import entities.Entity;
import entities.Player;
import game.highscore.HighscoreManager;

/**
 *
 */
public class Game extends Canvas {

	/**
	 * Static var for Singleton
	 */
	private static Game instance = null;

	/**
	 * Size for each block
	 */
	public static final int BLOCK_SIZE = 50;

	/**
	 * Width in "Blocks" for the game
	 */
	public static int FIELD_WIDTH = 16;
	/**
	 * Height in "Blocks" for the game
	 */
	public static int FIELD_HEIGHT = ((Game.FIELD_WIDTH * 3) / 4);

	/**
	 * Width in px
	 */
	public static int GAME_WIDTH = (Game.FIELD_WIDTH * Game.BLOCK_SIZE);
	/**
	 * Height in px
	 */
	public static int GAME_HEIGHT = (Game.FIELD_HEIGHT * Game.BLOCK_SIZE);

	/**
	 * Array with all entities in the game
	 */
	public static CopyOnWriteArrayList<Entity> entities;
	/**
	 * Array with all static entities like Wall and Background
	 */
	public static CopyOnWriteArrayList<Entity> staticBackground;
	/**
	 * Array with all players
	 */
	public static CopyOnWriteArrayList<Entity> players;
	/**
	 * Array with all possible key settings
	 */
	public static ArrayList<KeySettings> key_settings = new ArrayList<KeySettings>();
	/**
	 * 
	 */
	private boolean running;

	/**
	 * Max FPS of the game
	 */
	private int maxUpdateRate = 80;
	/**
	 * Time each frame should take
	 */
	private long frameTimeNs = 1000000000 / this.maxUpdateRate;
	/**
	 * Min time the thread should wait
	 */
	private int minSleepTime = 10;
	/**
	 * FPS shown in gui
	 */
	public int fps_static = 0;
	/**
	 * FPS counter
	 */
	private int fps = 0;

	/**
	 * Counter to check if static elements have changed
	 */
	private int oldBackgroundElems;

	HighscoreManager hm = new HighscoreManager();

	/**
	 * Key Listener
	 */
	public static InputHandler keys = new InputHandler();

	/**
	 * Buffered image for static elements
	 */
	public static BufferedImage background;

	/**
	 * Get Singleton-Instance
	 * 
	 * @return Game
	 */
	public static Game getInstance() {
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return Game.instance;
	}

	/**
	 * Constructor to set Canvas size and create important objects and load the
	 * default map
	 * 
	 */
	private Game() {
		Game.keys = new InputHandler();
		this.addKeyListener(Game.keys);

		KeySettings.createKeySettings();

		this.requestFocus();
		this.setFocusable(true);
		this.init();
	}

	/**
	 * Game-Loop Check how long it took to render a frame and let the thread
	 * sleep some ns
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		long lastLoopTime = System.nanoTime();
		int lastFpsTime = 0;
		long sleepTime = 0;
		BufferStrategy bs = this.getBufferStrategy();
		while (this.running) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / this.frameTimeNs;

			lastFpsTime += updateLength;
			this.fps++;

			if (lastFpsTime >= 1000000000) {
				this.fps_static = this.fps;
				lastFpsTime = 0;
				this.fps = 0;
			}

			/**
			 * Move all objects
			 */
			this.action(delta);

			/**
			 * Redraw all objects
			 */
			Graphics g = bs.getDrawGraphics();
			this.draw(g);
			bs.show();
			Toolkit.getDefaultToolkit().sync();
			/**
			 * Let the thread sleep
			 */
			sleepTime = (lastLoopTime - System.nanoTime()) / this.frameTimeNs;
			if (sleepTime < this.minSleepTime) {
				sleepTime = this.minSleepTime;
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Create all arrays, load the map and set size of gamefield
	 */
	public void init() {
		Game.entities = new CopyOnWriteArrayList<Entity>();
		Game.staticBackground = new CopyOnWriteArrayList<Entity>();
		Game.players = new CopyOnWriteArrayList<Entity>();
		Generator g1 = new Generator(200, 320);
		Loader l1 = new Loader();
		g1.generateMap();
		l1.loadMap("DebugMap");
		Game.GAME_WIDTH = (Game.FIELD_WIDTH * Game.BLOCK_SIZE) + 1;
		Game.GAME_HEIGHT = (Game.FIELD_HEIGHT * Game.BLOCK_SIZE) + 1;

		Dimension d = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
	}

	/**
	 * Restart the current map
	 */
	public void restart() {
		this.init();
		this.running = true;
		this.run();
	}

	/**
	 * Gets called form Launcher to start the game;
	 */
	public void start() {
		this.createBufferStrategy(2);
		this.running = true;
		this.run();
	}

	/**
	 * Stop the game
	 */
	public void stop() {
		this.running = false;
	}

	/**
	 * Move all entities
	 * 
	 * @param delta
	 */
	private void action(double delta) {

		for (Entity e : Game.entities) {
			if ((e.removed == false) && (e.needsStep == true)) {
				e.action(delta);
			}
		}

		/*
		 * for (Entity e : Game.players) { if (e.removed) { this.gameEnd(false);
		 * break; } }
		 */
	}

	/**
	 * Draw everything
	 * 
	 * @param g
	 */
	private void draw(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		if (this.oldBackgroundElems != Game.staticBackground.size()) {

			Game.background = new BufferedImage(Game.GAME_WIDTH,
					Game.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			for (Entity e : Game.staticBackground) {
				e.draw(Game.background.getGraphics());
			}
			this.oldBackgroundElems = Game.staticBackground.size();
		}

		g.drawImage(Game.background, 0, 0, null);

		for (Entity e : Game.entities) {
			if (e.removed == false) {
				e.draw(g);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("FPS: " + this.fps_static, 0, 10);
	}

	/**
	 * Get all Entities in a Box
	 * 
	 * @param b
	 *            Box to check
	 * @return List<Entity> Found entities
	 */
	public static List<Entity> getEntities(Box b) {
		List<Entity> result = new ArrayList<Entity>();

		for (Entity e : Game.entities) {
			if (e.removed == false) {
				if (e.box.intersects(b)) {
					result.add(e);
				}
			}
		}
		return result;
	}

	/**
	 * End the game and show dialog to end / restart
	 * 
	 * @param p
	 *            Player
	 * @param type
	 *            Gameend enum
	 */
	public void gameEnd(Player p, Gameend type) {
		this.stop();
		int index;
		Player otherP;
		String winner;
		int choice;

		if (Game.players.size() == 2) {
			if (p == (Player) Game.players.get(0)) {
				otherP = (Player) Game.players.get(1);
			} else {
				otherP = (Player) Game.players.get(0);
			}
			index = Game.players.indexOf(p) + 1;
			if (type == Gameend.finishReached) {
				JOptionPane.showMessageDialog(this, "Spieler " + index
						+ " ist im Ziel und hat gewonnen!");
				winner = JOptionPane
						.showInputDialog(
								this,
								"Spieler "
										+ index
										+ ", bitte geben Sie ihren Namen ein und best\u00E4tigen Sie ihre Eingabe mit einem Klick auf OK",
								null, JOptionPane.PLAIN_MESSAGE);
				if (winner != null) {
					this.hm.addScore(winner, p.pm.getPoints());
				}
			} else {
				int otherplayer;
				if (index == 1) {
					otherplayer = 2;

				} else {
					otherplayer = 1;
				}
				JOptionPane.showMessageDialog(this, "Spieler " + index
						+ " ist tot. Somit hat Spieler " + otherplayer
						+ " gewonnen.");
				winner = JOptionPane
						.showInputDialog(
								this,
								"Spieler "
										+ otherplayer
										+ ", bitte geben Sie ihren Namen ein und best\u00E4tigen Sie ihre Eingabe mit einem Klick auf OK",
								null, JOptionPane.PLAIN_MESSAGE);
				if (winner != null) {
					this.hm.addScore(winner, otherP.pm.getPoints());
				}
			}
		} else {
			if (type == Gameend.finishReached) {
				JOptionPane.showMessageDialog(this, "Du hast gewonnen!");
			} else {
				JOptionPane.showMessageDialog(this, "Du hast verloren!");
			}
		}
		choice = JOptionPane.showConfirmDialog(this,
				"M\u00F6chten Sie das Spiel neustarten ?", "Spielende",
				JOptionPane.YES_NO_OPTION);

		if (choice == 0) {
			// Spiel neustarten
			this.restart();
		} else {
			// Spiel beenden;
			System.exit(0);
		}
	}

	/**
	 * Get a input configuration for a player
	 * 
	 * @param player_count
	 * @return KeySettings
	 * @throws Exception
	 */
	public static KeySettings getKeySettings(int player_count) throws Exception {
		if (player_count < Game.key_settings.size()) {
			return Game.key_settings.get(player_count);
		} else {
			throw new Exception("Unknown key settings");
		}
	}
}
