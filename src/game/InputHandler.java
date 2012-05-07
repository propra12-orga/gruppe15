package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public class Key {
		public boolean down;

		public Key() {
		}

		public void toggle(boolean pressed) {
			if (pressed != this.down) {
				this.down = pressed;
			}
		}
	}

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key bomb = new Key();
	public Key pause = new Key();

	public InputHandler() {

	}

	private void toggle(KeyEvent ke, boolean pressed) {
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			this.up.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			this.down.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			this.left.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			this.bomb.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_P) {
			this.pause.toggle(pressed);
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		this.toggle(ke, true);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		this.toggle(ke, false);
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

}
