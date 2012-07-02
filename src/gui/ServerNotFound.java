package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ServerNotFound extends JFrame {
	private JPanel panel;

	public ServerNotFound(Container parentWindow) {
		super("Warten..");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(parentWindow);

		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 5, 5);

		JLabel info = new JLabel("Kann Server nicht finden");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 100;
		c.ipady = 40;

		this.panel.add(info, c);
		this.setContentPane(this.panel);
		this.pack();
		this.setVisible(true);
	}
}
