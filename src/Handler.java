import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Handles any object currently updating or being rendered
 */
public class Handler {
	
	/**
	 * LinkedList of GameObjects that will be updated using tick() or rendered<br>
	 * Objects should be added when they are needed, and removed when no longer needed (i.e when deleting an object, make sure to remove it from this list)<br>
	 * This is sorted in inverse order based on the Z-index, so that lower Z-indexes are rendered below higher ones
	 */
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	/**
	 * Tick function that is called every frame.<br>
	 * This calls the tick() function of every member of the objects list
	 * @param delta The delta value generated in the main run thread
	 */
	public void tick(double delta) {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			object.tick(delta);
		}
	}
	
	/**
	 * Render function called every frame.<br>
	 * This checks if an object in the objects list is on screen, and renders it only if it is.
	 * @param g The Graphics object onto which everything will be rendered
	 */
	public void render(Graphics g) {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			Rectangle bounds = new Rectangle((int)object.getX(), (int)object.getY(), object.getWidth(), object.getHeight());
			if(bounds.intersects(Game.windowRect)) {
				object.render(g);
			}
		}		
	}
	
	/**
	 * Sorts the objects within the objects list by their Z-index
	 */
	private void sortRenderableObjects() {
		Collections.sort(this.objects, new Comparator<GameObject>() {
			public int compare(GameObject o1, GameObject o2) {
				return o1.getZ() - o2.getZ();
			}
		});
	}
	
	/**
	 * Adds a given GameObject to the objects list, and sorts the list<br>
	 * @todo Update this function to insert the object in the correct place, rather than re-sorting
	 * @param object The GameObject to be added to the objects list
	 */
	public void addObject(GameObject object) {
		this.objects.add(object);
		this.sortRenderableObjects();
	}
	
	/**
	 * Removes a given GameObject from the objects list.
	 * @param object The GameObject to be removed from the objects list
	 */
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
}
