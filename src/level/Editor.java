package level;

import game.Game;
import game.Main;
import graphics.Image;
import graphics.Sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author kyra
 * 
 */

public class Editor implements ActionListener {

	/**
	 * new MenuBar named "menubar"
	 */

	private JMenuBar menubar;

	/**
	 * new JMenu named "level"
	 */

	private JMenu level;

	/**
	 * new JMenu named "komponente"
	 */

	private JMenu komponente;
	/**
	 * new JMenuItem named "laden"
	 */
	private JMenuItem laden;

	/**
	 * new JMenuItem named "speichern"
	 */
	private JMenuItem speichern;
	/**
	 * new JMenuItem named "neu"
	 */
	private JMenuItem neu;

	private JMenuItem breakable;

	private JMenuItem unbreakable;

	private JMenuItem player;

	private JMenuItem finish;

	private JMenuItem empty;
	/**
	 * new JFrame named editorframe
	 */
	private JFrame editorframe;

	private int element;

	private Game game;
	private String filename;
	private Image images[][];

	/**
	 * default constructor for Editor, sets frame and buttons
	 */

	public Editor() {

		/**
		 * create new JFrame called "Leveleditor" and set properties
		 */

		JFrame editorframe = new JFrame("Leveleditor");

		editorframe.setTitle("Leveleditor");
		editorframe.setSize(500, 500);
		editorframe.setVisible(true);

		/**
		 * create new JMenuBar
		 */

		this.menubar = new JMenuBar();

		/**
		 * create new JMenu "Level" and JMenuItems "laden", "speichern", "neu"
		 */
		this.level = new JMenu("Level");

		this.laden = new JMenuItem("Laden");
		this.laden.addActionListener(this);

		this.speichern = new JMenuItem("Speichern");
		this.speichern.addActionListener(this);

		this.neu = new JMenuItem("Neu");
		this.neu.addActionListener(this);

		this.level.add(this.laden);
		this.level.add(this.speichern);
		this.level.add(this.neu);
		this.menubar.add(this.level);

		this.komponente = new JMenu("Komponente");

		this.breakable = new JMenuItem("Zerstörbare Wand");
		this.breakable.addActionListener(this);

		this.unbreakable = new JMenuItem("Unzerstörbare Wand");
		this.unbreakable.addActionListener(this);

		this.finish = new JMenuItem("Ziel");
		this.finish.addActionListener(this);

		this.player = new JMenuItem("Spieler");
		this.player.addActionListener(this);

		this.empty = new JMenuItem("Komponente entfernen");
		this.empty.addActionListener(this);

		this.komponente.add(this.breakable);
		this.komponente.add(this.unbreakable);
		this.komponente.add(this.player);
		this.komponente.add(this.finish);
		this.komponente.add(this.empty);
		this.menubar.add(this.komponente);

		/**
		 * set menubar
		 */

		editorframe.setJMenuBar(this.menubar);
		this.drawMap("Map");
	}

	/**
	 * override actionPerformed(ActionEvent arg0) to determine what happens if a
	 * button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// if "laden" is pressed, create FileChooser
		if (arg0.getSource() == this.laden) {
			JFileChooser fileChooser = new JFileChooser();

			File f = new File(Main.class.getResource("/ressources/maps/")
					.getPath());
			fileChooser.setCurrentDirectory(f);
			int returnVal = fileChooser.showOpenDialog(this.editorframe);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// loadMap(chooser.getSelectedFile().getName());
			}

		}

		// if "speichern" is pressed, create FileChooser

		if (arg0.getSource() == this.speichern) {

			JFileChooser fileChooser = new JFileChooser();

			File f = new File(Main.class.getResource("/ressources/maps/")
					.getPath());
			fileChooser.setCurrentDirectory(f);
			int returnVal = fileChooser.showSaveDialog(this.editorframe);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// save file
			}
		}

		// if "neu" is pressed, start reset()
		if (arg0.getSource() == this.neu) {
			this.drawMap("Map");

		}

		// if "unbreakable" is pressed

		if (arg0.getSource() == this.unbreakable) {
			this.element = 2;
		}

		// if "breakable" is pressed
		if (arg0.getSource() == this.breakable) {
			this.element = 1;
		}

		// if "player" is pressed
		if (arg0.getSource() == this.player) {
			this.element = 3;
		}

		// if "empty" is pressed
		if (arg0.getSource() == this.empty) {
			this.element = 0;
		}

		// if "finish" is pressed
		if (arg0.getSource() == this.finish) {
			this.element = 4;
		}

	}

	/**
	 * 
	 */

	public void drawMap(String filename) {

		int x = 0, type, y = 0;

		Scanner maps;
		try {

			maps = new Scanner(
					Main.class.getResourceAsStream("/ressources/maps/"
							+ filename));
			while (maps.hasNextLine()) {
				String text = maps.nextLine();
				for (x = 0; x < text.length(); x++) {
					type = Integer.parseInt("" + text.charAt(x));
					if (type == 0) {
						this.images = Sprite.load("background.png", 100, 100);
					} else if (type == 1) {
						this.images = Sprite.load("w1.png", 100, 100);
					} else if (type == 2) {
						this.images = Sprite.load("wall.png", 100, 100);
					} else if (type == 3) {
						this.images = Sprite.load("bomberman.png", 55, 90);

						// Game.staticBackground.add(this.images =
						// Sprite.load("background.png", 100, 100));
					} else if (type == 4) {
						this.images = Sprite.load("finish.png", 100, 100);
					} else if (type == 5) {
						//

					}

				}
				y++;
			}
			// Game.FIELD_HEIGHT = y;
			// Game.FIELD_WIDTH = x;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
