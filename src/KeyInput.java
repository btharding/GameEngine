import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * A keyboard listener class.<br>
 * <br>
 * <b>This should be public and static in the game class, and registered as a key listener in the Game constructor</b><br>
 * <code>this.addKeyListener(keyInput);</code>
 */
public class KeyInput extends KeyAdapter{
	
	/**
	 * A LinkedList maintaining all classes implementing KeyListener
	 * @see KeyListener
	 */
	private LinkedList<KeyListener> listeners = new LinkedList<KeyListener>();
	
	/**
	 * Broadcasts keyPressed events to all KeyListener objects in the listener list
	 */
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < listeners.size(); i ++) {
			listeners.get(i).keyPressed(e);
		}
	}
	
	/**
	 * Broadcasts keyReleased events to all KeyListener objects in the listener list
	 */
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < listeners.size(); i ++) {
			listeners.get(i).keyReleased(e);
		}
	}
	
	/**
	 * Adds a KeyListener object to the listeners list
	 * @param k The KeyListener object to be added to the listeners list
	 */
	public void addListener(KeyListener k) {
		listeners.add(k);
	}
	
	/**
	 * Removes a KeyListener object from the listeners list
	 * @param k The object to be removed from the listeners list
	 */
	public void removeListener(KeyListener k) {
		listeners.remove(listeners.indexOf(k));
	}
}
