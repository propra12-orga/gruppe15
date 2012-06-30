package input;

import game.Game;

import java.awt.event.KeyEvent;

/**
 * @author Philipp
 * 
 */
public class KeySettings {

	/**
	 * The key the player uses for placing the bomb
	 */
	public Key bomb;
	/**
	 * the key for moving left
	 */
	public Key left;
	/**
	 * the key for moving right
	 */
	public Key right;
	/**
	 * the key for moving up
	 */
	public Key up;
	/**
	 * the key for moving down
	 */
	public Key down;

	/**
	 * Create key sets for every player
	 */
	public static void createKeySettings() {
		KeySettings s1 = new KeySettings();
		s1.bomb = Game.keys.getKey(KeyEvent.VK_L);
		s1.left = Game.keys.getKey(KeyEvent.VK_LEFT);
		s1.right = Game.keys.getKey(KeyEvent.VK_RIGHT);
		s1.up = Game.keys.getKey(KeyEvent.VK_UP);
		s1.down = Game.keys.getKey(KeyEvent.VK_DOWN);
		Game.key_settings.add(s1);

		KeySettings s2 = new KeySettings();
		s2.bomb = Game.keys.getKey(KeyEvent.VK_T);
		s2.left = Game.keys.getKey(KeyEvent.VK_A);
		s2.right = Game.keys.getKey(KeyEvent.VK_D);
		s2.up = Game.keys.getKey(KeyEvent.VK_W);
		s2.down = Game.keys.getKey(KeyEvent.VK_S);
		Game.key_settings.add(s2);
	}

}
