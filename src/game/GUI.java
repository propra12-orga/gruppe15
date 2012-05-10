package game;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUI {
	private JMenuBar menubar;
	private JMenu spiel;
	private JMenuItem starten;
	private JMenuItem beenden;
	private JMenu netzwerk;
	private JMenu optionen;
	private JMenuItem spname;
	private JMenuItem groesse;
	private JMenuItem startserver;
	private JMenuItem stopserver;
	private JMenuItem findserver;

	public GUI(JFrame frame) {

		// Create Menubar
		this.menubar = new JMenuBar();

		// Buttons for "Spiel"
		this.spiel = new JMenu("Spiel");
		this.starten = new JMenuItem("Neustarten");
		this.beenden = new JMenuItem("Beenden");
		this.spiel.add(this.starten);
		this.spiel.add(this.beenden);
		this.menubar.add(this.spiel);

		// Buttons for "Netzwerk"
		this.netzwerk = new JMenu("Netzwerk");
		this.startserver = new JMenuItem("Server starten");
		this.stopserver = new JMenuItem("Server beenden");
		this.findserver = new JMenuItem("Server suchen");
		this.netzwerk.add(this.startserver);
		this.netzwerk.add(this.stopserver);
		this.netzwerk.add(this.findserver);
		this.menubar.add(this.netzwerk);

		// Buttons for "Optionen"
		this.optionen = new JMenu("Optionen");
		this.spname = new JMenuItem("Spielername");
		this.groesse = new JMenuItem("Groesse");
		this.optionen.add(this.spname);
		this.optionen.add(this.groesse);
		this.menubar.add(this.optionen);

		// set Menubar
		frame.setJMenuBar(this.menubar);

	}

}
