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

	public GUI(JFrame frame) {
		this.menubar = new JMenuBar();
		// Spiel
		this.spiel = new JMenu("Spiel");
		this.starten = new JMenuItem("Neustarten");
		this.beenden = new JMenuItem("Beenden");
		this.spiel.add(this.starten);
		this.spiel.add(this.beenden);
		this.menubar.add(this.spiel);

		// Netzwerk
		this.netzwerk = new JMenu("Netzwerk");
		this.menubar.add(this.netzwerk);

		// Optionen
		this.optionen = new JMenu("Optionen");
		this.spname = new JMenuItem("Spielername");
		this.groesse = new JMenuItem("Größe");
		this.optionen.add(this.spname);
		this.optionen.add(this.groesse);
		this.menubar.add(this.optionen);

		frame.setJMenuBar(this.menubar);

	}

}
