package game;

import game.highscore.HighscoreManager;
import gui.ServerNotFound;
import input.InputHandler;
import input.KeySettings;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import level.Box;
import level.Loader;
import network.NetworkManager;
import network.Server;
import sound.Sound;
import sound.Soundmanager;
import entities.Entity;
import entities.Player;
import enums.Gameend;
import enums.Gamemode;

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

	public static Gamemode gamemode = null;

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

	public static NetworkManager network;

	/**
	 * Highscore manager
	 */
	public HighscoreManager hm = new HighscoreManager();

	private String map;

	private Container frame;

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
		Sound backgroundSound = Soundmanager.getInstance().load("rick_roll.wav", true);
		backgroundSound.play();
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
				if (this.fps_static < 20) {
					sleepTime = 0;
				} else {
					sleepTime = this.minSleepTime;
				}
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
		this.init("Map3");
	}

	public void init(String map) {
		this.map = map;
		Game.entities = new CopyOnWriteArrayList<Entity>();
		Game.staticBackground = new CopyOnWriteArrayList<Entity>();
		Game.players = new CopyOnWriteArrayList<Entity>();
		Loader l1 = new Loader();
		l1.loadMap(map);

		Game.GAME_WIDTH = (Game.FIELD_WIDTH * Game.BLOCK_SIZE) + 1;
		Game.GAME_HEIGHT = (Game.FIELD_HEIGHT * Game.BLOCK_SIZE) + 1;

		Dimension d = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		Game.keys.resetKeys();
		if (this.frame != null) {
			this.repack();
		}
	}

	/**
	 * Restart the current map
	 */
	public void restart() {
		this.init(this.map);
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

			Game.background = new BufferedImage(Game.GAME_WIDTH, Game.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			for (Entity e : Game.staticBackground) {
				e.draw(Game.background.getGraphics());
			}
			this.oldBackgroundElems = Game.staticBackground.size();
		}

		g.drawImage(Game.background, 0, 0, null);

		for (Entity e : Game.entities) {
			if ((e instanceof Player) == false) {
				if (e.removed == false) {
					e.draw(g);
				}
			}
		}

		for (int i = 0; i < Game.players.size(); i++) {
			Game.players.get(i).draw(g);
		}

		g.setColor(Color.WHITE);

		for (int i = 0; i < Game.players.size(); i++) {
			Player drawPoints = (Player) Game.players.get(i);
			g.drawString("Spieler " + (i + 1) + ":" + drawPoints.pm.getPoints(), 100 * (i + 1), 10);
		}

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
		JOptionPane question;
		int index;
		if (Game.gamemode == Gamemode.NETWORK) {
			if (type == Gameend.finishReached) {
				question = new JOptionPane("Du hast gewonnen!");
			} else if (type == Gameend.lastAlive) {
				question = new JOptionPane("Alle anderen Spieler sind tot. Du hast gewonnen.");
			} else {
				question = new JOptionPane("Du hast verloren.");
			}
		} else {
			if (Game.players.size() == 2) {
				Player otherP;
				if (p == (Player) Game.players.get(0)) {
					otherP = (Player) Game.players.get(1);
				} else {
					otherP = (Player) Game.players.get(0);
				}
				index = Game.players.indexOf(p) + 1;
				String winner;
				if (type == Gameend.finishReached) {
					JOptionPane.showMessageDialog(this, "Spieler " + index + " ist im Ziel und hat gewonnen!");
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
					JOptionPane.showMessageDialog(this, "Spieler " + index + " ist tot. Somit hat Spieler "
							+ otherplayer + " gewonnen.");
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
			int choice = JOptionPane.showConfirmDialog(this, "M\u00F6chten Sie das Spiel neustarten ?", "Spielende",
					JOptionPane.YES_NO_OPTION);

			if (choice == 0) {
				// Spiel neustarten
				this.restart();
			} else {
				// Spiel beenden;
				System.exit(0);
			}
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

	/**
	 * Connect to a Gameserver (Multiplayer)
	 * 
	 * @param server
	 * @param parentWindow
	 */
	public void connectServer(Server server, Container parentWindow) {
		Debug.log(Debug.DEBUG, "Connecting to server");
		Debug.log(Debug.DEBUG, server);
		Game.network = new NetworkManager(server, parentWindow);
		if (Game.network.connect()) {
			Game.network.start();
		} else {
			new ServerNotFound(this.getParent());
		}
	}

	/**
	 * Set the parent Container of the Game
	 * 
	 * @param frame
	 */
	public void setFrame(Container frame) {
		this.frame = frame;
	}

	/**
	 * Resize the parent Container when a new map is loaded
	 */
	public void repack() {
		JPanel panel;
		if (this.frame instanceof JFrame) {
			panel = (JPanel) ((JFrame) this.frame).getContentPane();
		} else {
			panel = (JPanel) ((JApplet) this.frame).getContentPane();
		}
		panel.setSize(panel.getPreferredSize());
		panel.revalidate();
		this.frame.setSize(this.frame.getPreferredSize());
		if (this.frame instanceof JFrame) {
			((JFrame) this.frame).pack();
		}
	}

	public static List<Entity> unique(List<Entity> entities2) {
		HashSet<Entity> hs = new HashSet<Entity>();
		hs.addAll(entities2);
		entities2.clear();
		entities2.addAll(hs);
		return entities2;

	}
}
