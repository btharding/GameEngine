package game;

import java.util.LinkedList;


public class Listeners {
	
	/**
	 * A list of objects implementing mouseClickListener. All mouse click events are broadcast to this list
	 */
	public static LinkedList<MouseClickListener> mouseClickListeners = new LinkedList<MouseClickListener>();
	
	/**
	 * Adds a MouseClickListener to the mouselickListeners list
	 * @param m The MouseClickListener to be added
	 */
	public static void addMouseClickListener(MouseClickListener m) {
		mouseClickListeners.add(m);
	}
	
	/**
	 * Removes a MouseClickListener from the clickListeners list
	 * @param m The MouseClickListener to be removed
	 */
	public static void removeMouseClickListener(MouseClickListener m) {
		mouseClickListeners.remove(m);
	}
	

}
