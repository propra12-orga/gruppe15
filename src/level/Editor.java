package level;

//import level.Loader;
//import ressources.maps.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		this.laden = new JMenuItem("laden");
		this.laden.addActionListener(this);

		this.speichern = new JMenuItem("speichern");
		this.speichern.addActionListener(this);

		this.neu = new JMenuItem("neu");
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
	}

	/**
	 * override actionPerformed(ActionEvent arg0) to determine what happens if a
	 * button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// if "laden" is pressed, start load()
		if (arg0.getSource() == this.laden) {

			this.load();

		}

		// if "speichern" is pressed, start save()

		if (arg0.getSource() == this.speichern) {

			this.save();
		}

		// if "neu" is pressed, start reset()
		if (arg0.getSource() == this.neu) {
			this.reset();
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
	 * method to load an existing Map
	 */
	private void load() {
		// TODO Auto-generated method stub

	}

	/**
	 * method to save a generated Map in a textfile
	 */
	private void save() {
		// TODO Auto-generated method stub

	}

	/**
	 * called to set map to empty map
	 */
	private void reset() {
		// loadMap(Map);

	}

}
