package gui;

import game.Game;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.GameServer;
import network.Server;

public class ConnectToIp extends JFrame implements ActionListener {

	private JPanel panel;
	private JButton connectButton;
	private JTextField input;
	private Container parentWindow;

	public ConnectToIp(Container frame) {
		super("IP-Adresse");

		this.parentWindow = frame;

		this.setLocationRelativeTo(this.parentWindow);

		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 5, 5);

		JLabel label = new JLabel("IP-Adresse:");

		this.panel.add(label);

		this.input = new JTextField();
		this.input.addActionListener(this);

		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 40;

		this.panel.add(this.input, c);

		this.connectButton = new JButton("Connect");
		this.connectButton.addActionListener(this);

		c.gridx = 1;
		c.gridy = 1;

		this.panel.add(this.connectButton, c);

		this.setContentPane(this.panel);

		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.connectButton) {
			String ip = this.input.getText();
			if (ConnectToIp.validate(ip)) {
				InetAddress ip_real;
				try {
					ip_real = InetAddress.getByName(ip);
					Game.getInstance().connectServer(new Server(ip_real, GameServer.GAME_PORT, ""), this.parentWindow);
				} catch (Exception e) {
					new ServerNotFound(this.parentWindow);
					// e.printStackTrace();
				}
			}
		}

	}

	public static boolean validate(final String ip) {
		String PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

}
