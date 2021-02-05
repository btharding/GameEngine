
import java.awt.event.KeyEvent;

/**
 * Interface to be implemented by any objects that require keyboard input.<br>
 * KeyInput.java maintains a list of key listeners, and calls their keyPressed and keyReleased functions when an event is registered<br>
 * <br>
 * <b>In the constructor</b><br>
 * <code>Game.keyInput.addListener(this);</code>
 */
public interface KeyListener {
	/**
	 * keyPressed event that is called by KeyInput.java every time a keyPressed event is detected.<br>
	 * @param e The KeyEvent broadcasted by KeyInput
	 */
	public void keyPressed(KeyEvent e);
	/**
	 * keyReleased event that is called by KeyInput.java every time a keyReleased event is detected.<br>
	 * @param e The KeyEvent broadcasted by KeyInput
	 */
	public void keyReleased(KeyEvent e);
}
