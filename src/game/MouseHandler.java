package game;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import level.Editor;

/**
 * 
 * @author kyra
 * 
 */

public class MouseHandler extends Editor implements MouseListener {

	public MouseHandler(Container frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	/**
	 * determine what happens if mouse is clicked
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.setComponent(arg0.getX(), arg0.getY());
	}

	/**
	 * determine what happens if mouse enters field
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse exits field
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * determines what happens if mouse is released
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
