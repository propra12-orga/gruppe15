package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * @author Oktay
 * 
 */
public class InputHandler implements KeyListener {

	/**
	 * Array with all known Keys
	 */
	private HashMap<Integer, Key> keys;

	public InputHandler() {
		this.keys = new HashMap<Integer, Key>();
		// Keys for Player 1
		this.keys.put(KeyEvent.VK_UP, new Key());
		this.keys.put(KeyEvent.VK_DOWN, new Key());
		this.keys.put(KeyEvent.VK_LEFT, new Key());
		this.keys.put(KeyEvent.VK_RIGHT, new Key());
		this.keys.put(KeyEvent.VK_L, new Key());
		this.keys.put(KeyEvent.VK_K, new Key());

		// Keys for Player 2
		this.keys.put(KeyEvent.VK_W, new Key());
		this.keys.put(KeyEvent.VK_S, new Key());
		this.keys.put(KeyEvent.VK_A, new Key());
		this.keys.put(KeyEvent.VK_D, new Key());
		this.keys.put(KeyEvent.VK_T, new Key());
		this.keys.put(KeyEvent.VK_R, new Key());

	}

	/**
	 * Method checks which button was pressed
	 * 
	 * @param ke
	 * @param pressed
	 */
	private void toggle(KeyEvent ke, boolean pressed) {
		if (this.keys.containsKey(ke.getKeyCode())) {
			this.keys.get(ke.getKeyCode()).toggle(pressed);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		this.toggle(ke, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ke) {
		this.toggle(ke, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent ke) {
		this.toggle(ke, true);
	}

	/**
	 * Return a key
	 * 
	 * @param key
	 * @return
	 */
	public Key getKey(Integer key) {
		return this.keys.get(key);
	}

	/**
	 * Sets all keys to false
	 */
	public void resetKeys() {
		for (Integer i : this.keys.keySet()) {
			this.keys.get(i).toggle(false);
		}
	}
}
