package level;

import game.Game;
import game.Main;
import graphics.Image;
import graphics.Sprite;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

public class Editor extends Canvas implements ActionListener, MouseListener {

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

	protected int element;

	private String filename;
	private Image images[][];
	private int position[][];
	int x = 0, type, y = 0;

	private Scanner maps;
	private Container frame;
	private Graphics g;

	/**
	 * default constructor for Editor, sets frame and buttons
	 */

	public Editor(Container frame) {

		/**
		 * create new JFrame called "Leveleditor" and set properties
		 */
		JFrame editorframe = new JFrame("Leveleditor");

		editorframe.setTitle("Leveleditor");
		editorframe.setSize(500, 500);
		editorframe.setLocationRelativeTo(frame);
		editorframe.setVisible(true);

		editorframe.addMouseListener(this);

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

		this.breakable = new JMenuItem("Zerst\u00F6rbare Wand");
		this.breakable.addActionListener(this);

		this.unbreakable = new JMenuItem("Unzerst\u00F6rbare Wand");
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
		this.loadMap("Map1");

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
				this.loadMap(fileChooser.getSelectedFile().getName());
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
				this.saveMap();
			}

			// if "neu" is pressed, load default Map
			if (arg0.getSource() == this.neu) {
				this.loadMap("Map1");

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
	}

	/**
	 * method to draw Map
	 */

	public void loadMap(String filename) {

		try {

			this.maps = new Scanner(
					Main.class.getResourceAsStream("/ressources/maps/"
							+ filename));
			while (this.maps.hasNextLine()) {
				String text = this.maps.nextLine();
				for (this.x = 0; this.x < text.length(); this.x++) {
					this.element = Integer.parseInt("" + text.charAt(this.x));
					if (this.element == 0) {
						this.images = Sprite.load("background.png", 100, 100);
					} else if (this.element == 1) {
						this.images = Sprite.load("w1.png", 100, 100);
					} else if (this.element == 2) {
						this.images = Sprite.load("wall.png", 100, 100);
					} else if (this.element == 3) {
						this.images = Sprite.load("bomberman.png", 55, 90);

						// Game.staticBackground.add(this.images =
						// Sprite.load("background.png", 100, 100));
					} else if (this.element == 4) {
						this.images = Sprite.load("finish.png", 100, 100);
					} else if (this.element == 5) {
						//

					}
					this.draw(this.g);
				}

				this.y++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setComponent(int x, int y) {

		this.x = x;
		this.y = y;

		if (this.element == 0) {
			this.images = Sprite.load("background.png", 100, 100);
		} else if (this.element == 1) {
			this.images = Sprite.load("w1.png", 100, 100);
		} else if (this.element == 2) {
			this.images = Sprite.load("wall.png", 100, 100);
		} else if (this.element == 3) {
			this.images = Sprite.load("bomberman.png", 55, 90);
		} else if (this.element == 4) {
			this.images = Sprite.load("finish.png", 100, 100);
		} else if (this.element == 5) {

		}

		this.draw(this.g);
	}

	public void draw(Graphics g) {
		g.drawImage((this.images[this.x][this.y]).image, this.x, this.y,
				Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
	}

	/**
	 * Method to save Map
	 */
	public void saveMap() {

		int x = 0;
		int y = 0;
		try {
			FileWriter fw = new FileWriter(this.getName() + ".txt");

			while (y < Game.FIELD_HEIGHT) {
				while (x < Game.FIELD_WIDTH) {

					try {
						if (this.images == Sprite.load("background.png", 100,
								100)) {
							this.element = 0;
						} else if (this.images == Sprite.load("w1.png", 100,
								100)) {
							this.element = 1;
						} else if (this.images == Sprite.load("wall.png", 100,
								100)) {
							this.element = 2;
						} else if (this.images == Sprite.load("bomberman.png",
								55, 90)) {
							this.element = 3;
						} else if (this.images == Sprite.load("finish.png",
								100, 100)) {
							this.element = 4;
						}

						fw.write(this.element);
					} catch (IOException e) {
						e.printStackTrace();
						fw.write("Fehler");
					}
					x++;
				}
				y++;
			}
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * determine what happens if mouse is clicked
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.setComponent(arg0.getX(), arg0.getY());
	}

	/**
	 * determine what happens if mouse enters field
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse exits field
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse is released
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
