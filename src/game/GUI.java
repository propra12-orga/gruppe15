package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUI implements ActionListener {
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
	private Launcher frame;

	public GUI(Launcher frame) {

		this.frame = frame;

		// Create Menubar
		this.menubar = new JMenuBar();

		// Buttons for "Spiel"
		this.spiel = new JMenu("Spiel");
		this.starten = new JMenuItem("Neustarten");
		this.starten.addActionListener(this);
		this.beenden = new JMenuItem("Beenden");
		this.beenden.addActionListener(this);
		// this.spiel.add(this.starten);
		this.spiel.add(this.beenden);
		this.menubar.add(this.spiel);

		// Buttons for "Netzwerk"
		this.netzwerk = new JMenu("Netzwerk");
		/*
		 * this.startserver = new JMenuItem("Server starten"); this.stopserver =
		 * new JMenuItem("Server beenden"); this.findserver = new
		 * JMenuItem("Server suchen"); this.netzwerk.add(this.startserver);
		 * this.netzwerk.add(this.stopserver);
		 * this.netzwerk.add(this.findserver);
		 */
		this.netzwerk.add(new JMenuItem("noch nicht verfügbar"));
		this.menubar.add(this.netzwerk);

		// Buttons for "Optionen"
		this.optionen = new JMenu("Optionen");
		/*
		 * this.spname = new JMenuItem("Spielername"); this.groesse = new
		 * JMenuItem("Groesse"); this.optionen.add(this.spname);
		 * this.optionen.add(this.groesse);
		 */
		this.optionen.add(new JMenuItem("noch nicht verfügbar"));
		this.menubar.add(this.optionen);

		// set Menubar
		this.frame.setJMenuBar(this.menubar);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.starten) {
			Object[] options = { "Neustart", "abbrechen" };
			JOptionPane question = new JOptionPane("Spiel neustarten? Der aktuelle fortschritt geht verloren");
			question.setOptions(options);
			JDialog dialog = question.createDialog(this.frame, "Achtung");
			dialog.setVisible(true);
			Object obj = question.getValue();
			if (obj.equals(options[0])) {
				// Spiel neustarten

				this.frame.game.stop();
				this.frame.game.start();
			}
			dialog.dispose();
			question.disable();
		} else if (arg0.getSource() == this.beenden) {
			Object[] options = { "beenden", "abbrechen" };
			JOptionPane question = new JOptionPane("Spiel beenden? Der aktuelle fortschritt geht verloren");
			question.setOptions(options);
			JDialog dialog = question.createDialog(this.frame, "Achtung");
			dialog.setVisible(true);
			Object obj = question.getValue();
			if (obj.equals(options[0])) {
				// Spiel beenden
				System.exit(0);
			}
			dialog.dispose();
		}

	}
}
