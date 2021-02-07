import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Interface to be implemented by any object that must not allow an object to pass through it<br>
 * This interface extends CollidingObject, so you only need to implement this one.<br>
 * <br>
 * <b>In the constructor</b><br>
 * <code>CollidingObject.addCollider(this);</code><br>
 * <code>SolidCollider.addSolidCollider(this);</code><br>
 * <br>
 * <b>In the tick method</b><br>
 * <code>CollidingObject.getCollisions(this);</code><br>
 * Before your movement code, use <code>CollidingObject.willCauseSolidCollision(this, ...);</code><br>
 * <i>Example</i><br>
 * <code>if(!SolidCollider.willCauseSolidCollision(this, this.velX, true)) {
			this.x += this.velX;
		}
		if(!SolidCollider.willCauseSolidCollision(this, this.velY, false)) {
			this.y += this.velY;
		}
	</code>
 */
public interface SolidCollider extends CollidingObject{
	/**
	 * A LinkedList maintaining all classes implementing SolidCollider<br>
	 * (all objects that could collide with each other)
	 */
	static LinkedList<SolidCollider> solidColliders = new LinkedList<SolidCollider>();	
	
	/**
	 * Adds a SolidCollider object to the solidColliders list.<br>
	 * Should be called in the constructor of a class implementing SolidCollider
	 * @param object The object to be added to the SolidColliders list
	 */
	static void addSolidCollider(SolidCollider s) {
		solidColliders.add(s);
	}
	
	/**
	 * Determines if a given movement along a single axis will cause a collision with a solid object<br>
	 * Should be used to determine if a given movement can be performed e.g.<br>
	 * <code>
	 * if(!SolidCollider.willCauseSolidCollision(this, this.velX, true)) {
			this.x += this.velX;
		}
		if(!SolidCollider.willCauseSolidCollision(this, this.velY, false)) {
			this.y += this.velY;
		}
	 * </code>
	 * @param o The CollidingObject you'd like to test for potential collisions
	 * @param vel The velocity in the direction specified by the xAxis param
	 * @param xAxis A boolean identifying the direction of proposed movement
	 * @return A boolean representing whether a collision will occur by a proposed action
	 */
	static boolean willCauseSolidCollision(CollidingObject o, int vel, boolean xAxis) {
		Rectangle oBounds = o.getBounds();
		Rectangle newBounds;
		if(xAxis) {
			newBounds = new Rectangle(oBounds.x + vel, oBounds.y, oBounds.width, oBounds.height);
		}else {
			newBounds = new Rectangle(oBounds.x, oBounds.y + vel, oBounds.width, oBounds.height);
		}
		for(int i = 0; i < solidColliders.size(); i ++) {
			SolidCollider s = solidColliders.get(i);
			if(newBounds.intersects(s.getBounds())) {
				return true;
			}
		}
		return false;
	}

}
