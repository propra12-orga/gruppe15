package input;

/**
 * @author Oktay
 * 
 */
public class Key {
	/**
	 * Boolean shows if key is pressed (true) or not (false)
	 */
	public boolean down;

	public Key() {
	}

	/**
	 * If key is pressed down becomes true
	 * 
	 * @param pressed
	 */
	public void toggle(boolean pressed) {
		if (pressed != this.down) {
			this.down = pressed;
		}
	}
}