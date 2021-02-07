import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputListener;

/**
 * A mouse listener class.<br>
 * <br>
 * <b>This should be public and static in the game class, and registered as a mouse listener and mouse motion listener in the Game constructor</b><br>
 * Contains a public static Point storing the position of the mouse on the screen<br>
 * <code>this.addMouseListener(mouseInput);<br>this.addMouseMotionListener(mouseInput);</code>
 */
public class MouseInput implements MouseInputListener{
	/**
	 * A list of objects implementing mouseClickListener. All mouse click events are broadcast to this list
	 */
	private static LinkedList<MouseClickListener> clickListeners = new LinkedList<MouseClickListener>();
	
	/**
	 * The location of the mouse on the screen. X and Y coordinates can be accessed directly (i.e. <code>MouseInput.mousePos.x</code>)
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
	 * Adds a MouseClickListener to the clickListeners list
	 * @param m The MouseClickListener to be added
	 */
	public void addClickListener(MouseClickListener m) {
		clickListeners.add(m);
	}
	
	/**
	 * Removes a MouseClickListener from the clickListeners list
	 * @param m The MouseClickListener to be removed
	 */
	public void removeClickListener(MouseClickListener m) {
		clickListeners.remove(m);
	}
	
	/**
	 * Broadcasts a mouseClick event to all MouseClickListener objects in the clickListers list
	 */
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < clickListeners.size(); i++) {
			clickListeners.get(i).mouseClicked(e);
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
