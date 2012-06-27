package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Oktay
 * 
 */
public class InputHandler implements KeyListener {

	/**
	 * Key for moving up for player 1
	 */
	public Key up = new Key();
	/**
	 * Key for moving down for player 1
	 */
	public Key down = new Key();
	/**
	 * Key for moving left for player 1
	 */
	public Key left = new Key();
	/**
	 * Key for moving right for player 1
	 */
	public Key right = new Key();
	/**
	 * Key for planting bomb for player 1
	 */
	public Key bomb = new Key();
	/**
	 * Key for moving up for player 2
	 */
	public Key up2 = new Key();
	/**
	 * Key for moving down for player 2
	 */
	public Key down2 = new Key();
	/**
	 * Key for moving left for player 2
	 */
	public Key left2 = new Key();
	/**
	 * Key for moving right for player 2
	 */
	public Key right2 = new Key();
	/**
	 * Key for planting bomb for player 2
	 */
	public Key bomb2 = new Key();
	/**
	 * Key for pausing the game
	 */
	public Key pause = new Key();

	public InputHandler() {

	}

	/**
	 * Method checks which button was pressed
	 * 
	 * @param ke
	 * @param pressed
	 */
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
		if (ke.getKeyCode() == KeyEvent.VK_L) {
			this.bomb.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_W) {
			this.up2.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_S) {
			this.down2.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_A) {
			this.left2.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_D) {
			this.right2.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_T) {
			this.bomb2.toggle(pressed);
		}
		if (ke.getKeyCode() == KeyEvent.VK_P) {
			this.pause.toggle(pressed);
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

}
