package game;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputListener;

/**
 * A mouse listener class.<br>
 * <br>
 * <b>This should be public and static in the game class, and registered as a mouse listener and mouse motion listener in the Game constructor</b><br>
 * Contains a public static Point storing the position of the mouse on the screen<br>
 * <pre>this.addMouseListener(mouseInput);
 * this.addMouseMotionListener(mouseInput);</pre>
 */
public class MouseInput implements MouseInputListener{
	
	/**
	 * The location of the mouse on the screen. X and Y coordinates can be accessed directly (i.e. <pre>MouseInput.mousePos.x</pre>)
	 * This will be broadcast over the network
	 */
	public static Point mousePos;
	
	/**
	 * MouseInput constructor.<br>
	 * Initialises the mousePos to avoid null errors.
	 */
	public MouseInput() {
		mousePos = new Point();
	}
	
	/**
	 * Broadcasts a mouseClick event to all MouseClickListener objects in the clickListers list
	 * This will be replaced with a network send function
	 */
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < Listeners.mouseClickListeners.size(); i++) {
			Listeners.mouseClickListeners.get(i).mouseClicked(e);
//			Client.mouseEventBuffer.write(e);
		}
	}
	
	/**
	 * Updates MouseInput.mousePos to the current mouse location every time it moves.
	 */
	public void mouseMoved(MouseEvent e) {
		MouseInput.mousePos = e.getPoint();
	}
	
	/**
	 * Updates MouseInput.mousePos to the current mouse location every time it moves.
	 */
	public void mouseDragged(MouseEvent e) {
		MouseInput.mousePos = e.getPoint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
