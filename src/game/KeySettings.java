package game;

import game.InputHandler.Key;

public class KeySettings {

	public Key bomb;
	public Key left;
	public Key right;
	public Key up;
	public Key down;

	/**
	 * Create key sets for every player
	 */
	public static void createKeySettings() {
		KeySettings s1 = new KeySettings();
		s1.bomb = Game.keys.bomb;
		s1.left = Game.keys.left;
		s1.right = Game.keys.right;
		s1.up = Game.keys.up;
		s1.down = Game.keys.down;
		Game.key_settings.add(s1);

		KeySettings s2 = new KeySettings();
		s2.bomb = Game.keys.bomb2;
		s2.left = Game.keys.left2;
		s2.right = Game.keys.right2;
		s2.up = Game.keys.up2;
		s2.down = Game.keys.down2;
		Game.key_settings.add(s2);
	}
}
