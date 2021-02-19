package game;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * <p>Interface to be implemented by any object with a collision interaction<br>
 * This is a general purpose collider interface, extend this with any specific types of collisions (e.g. SolidCollider)</p>
 * 
 * <b>In the constructor</b>
 * <pre>CollidingObject.addCollider(this);</pre>
 * 
 * <b>In the tick method</b>
 * <pre>CollidingObject.getCollisions(this);</pre>
 */
public interface CollidingObject {
	
	/**
	 * A LinkedList maintaining all classes implementing CollidingObject<br>
	 * (all objects that could collide with each other)
	 */
	static LinkedList<CollidingObject> colliders = new LinkedList<CollidingObject>();
	
	/**
	 * Adds a CollidingObject to the colliders list.<br>
	 * Should be called in the constructor of a class implementing CollidingObject
	 * @param object The object to be added to the colliders list
	 */
	static void addCollider(CollidingObject object) {
		colliders.add(object);
	}
	
	/**
	 * Checks for bounding box collisions between a given object and all other CollidingObject classes<br>
	 * Calls the object's collision handler with a list of CollidingObject classes with which it intersects
	 * @param object The object you want to check for collisions. This is also the object whose collision handler will be called
	 */
	static void getCollisions(CollidingObject object){
		LinkedList<CollidingObject> out = new LinkedList<CollidingObject>();
		for(int i = 0; i < colliders.size(); i++) {
			CollidingObject o = colliders.get(i);
			if(o == object) {
				continue;
			}
			if(object.getBounds().intersects(o.getBounds())) {
				out.add(o);
			}
		}
		if(out.size() > 0) object.handleCollisions(out);
	}
	
	/**
	 * A required function for all classes extending CollidingObject.<br>
	 * This is called when a collision is detected on a given object
	 * @param collisions The list of collisions at any one time - this can be used to perform different actions based on the objects collided with
	 */
	void handleCollisions(LinkedList<CollidingObject> collisions);
	
	/**
	 * A required function for all classes extending CollidingObject.<br>
	 * @return A Rectangle showing the bounds of an object. Typically <pre>return new Rectangle((int)this.x, (int)this.y, this.width, this.height);</pre>
	 */
	Rectangle getBounds();	
}
