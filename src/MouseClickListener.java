import java.awt.event.MouseEvent;

/**
 * Interface to be implemented by any objects that require mouse click input.<br>
 * MouseInput.java maintains a list of key listeners, and calls their mouseClicked when an event is registered<br>
 * <br>
 * <b>In the constructor</b><br>
 * <code>Game.MouseInput.addClickListener(this);</code>
 */
public interface MouseClickListener {
	/**
	 * @param e The MouseEvent broadcasted by MouseInput
	 */
	public void mouseClicked(MouseEvent e);
}
