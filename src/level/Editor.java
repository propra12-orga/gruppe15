package level;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	String filename;

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
		this.run();
	}

	/**
	 * override actionPerformed(ActionEvent arg0) to determine what happens if a
	 * button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// if "laden" is pressed, start load()
		if (arg0.getSource() == this.laden) {
			/*
			 * JFileChooser chooser = new JFileChooser(); int returnVal =
			 * chooser.showOpenDialog(this.editorframe); if (returnVal ==
			 * JFileChooser.APPROVE_OPTION) { //
			 * loadMap(chooser.getSelectedFile().getName()); } else {
			 * JOptionPane.showMessageDialog(this.editorframe,
			 * "Die Datei ist nicht gültig."); }
			 */
			// this.load();

			JFrame dialog = new JFrame();
			dialog.setTitle("Karte laden");
			dialog.setSize(200, 100);
			JPanel panel = new JPanel();

			JLabel label = new JLabel("Karte (Bsp: Map1): ");
			panel.add(label);
			JTextField tfield = new JTextField("Map", 10);
			panel.add(tfield);

			JButton loadOK = new JButton("OK");
			panel.add(loadOK);

			dialog.add(panel);
			dialog.setVisible(true);
		}

		// if "speichern" is pressed, start save()

		if (arg0.getSource() == this.speichern) {
			/*
			 * JFileChooser fileChooser = new JFileChooser(
			 * System.getProperty("user.dir")); int returnVal =
			 * fileChooser.showSaveDialog(this.editorframe);
			 * 
			 * if (returnVal == JFileChooser.APPROVE_OPTION) { // save file }
			 * else { JOptionPane .showMessageDialog(this.editorframe,
			 * "Nicht gültig."); }
			 */
			// this.save();

			JFrame dialog = new JFrame();
			dialog.setTitle("Level speichern");
			dialog.setSize(200, 100);
			JPanel panel = new JPanel();

			JLabel label = new JLabel("Name (Bsp: Map1): ");
			panel.add(label);
			JTextField tfield = new JTextField("Map", 10);
			panel.add(tfield);

			JButton saveOK = new JButton("OK");
			panel.add(saveOK);

			dialog.add(panel);
			dialog.setVisible(true);
		}

		// if "neu" is pressed, start reset()
		if (arg0.getSource() == this.neu) {
			// loadMap(Map);

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

	public void run() {
		// Karte zeichnen, wenn klick und element=0-4 jeweilige grafik zeichnen

	}
}
