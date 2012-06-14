package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Editor implements ActionListener {

	private JMenuBar menubar;
	private JMenu level;
	private JMenuItem laden;
	private JMenuItem speichern;
	private JMenuItem neu;
	private JFrame editorframe;

	public Editor() {

		JFrame editorframe = new JFrame("Leveleditor");
		editorframe.setTitle("Leveleditor");
		editorframe.setSize(500, 500);
		editorframe.setVisible(true);

		this.menubar = new JMenuBar();

		this.level = new JMenu("Level");

		this.laden = new JMenuItem("laden");
		this.laden.addActionListener(this);

		this.speichern = new JMenuItem("speichern");
		this.speichern.addActionListener(this);

		this.neu = new JMenuItem("neu");
		this.neu.addActionListener(this);

		this.level.add(this.laden);
		this.menubar.add(this.level);

		// set Menubar
		this.editorframe.setJMenuBar(this.menubar);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// if "laden" is pressed
		if (arg0.getSource() == this.laden) {

			this.load();

		}

		// if "speichern" is pressed (not working yet)

		if (arg0.getSource() == this.speichern) {

			this.save();
		}

		// if "neu" is pressed
		if (arg0.getSource() == this.neu) {
			this.reset();
		}
	}

	private void load() {
		// TODO Auto-generated method stub

	}

	private void save() {
		// TODO Auto-generated method stub

	}

	private void reset() {
		// TODO Auto-generated method stub

	}

}
