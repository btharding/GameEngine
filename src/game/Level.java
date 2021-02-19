package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Level {
	private LinkedList<GameObject> entities = new LinkedList<>();
	private LinkedList<Platform> platforms = new LinkedList<>();
	
	public void tick() {
		for(GameObject o : entities) {
			o.tick();
		}
	}
	
	public void render(Graphics g, int xOffset, int yOffset) {
		//Render the platforms first, so they are below the entities
		renderPlatforms(g, xOffset, yOffset);
		renderEntities(g, xOffset, yOffset);
	}
	
	public void renderEntities(Graphics g, int xOffset, int yOffset) {
		for(GameObject o : entities) {
			o.render(g, xOffset, yOffset);
		}
	}
	
	public void renderPlatforms(Graphics g, int xOffset, int yOffset) {
		for(Platform p : platforms) {
			p.render(g, xOffset, yOffset);
		}
	}
	
	public void addEntity(GameObject o) {
		entities.add(o);
	}
	public void removeEntity(GameObject o) {
		entities.remove(o);
	}
	public void addPlatform(Platform p) {
		platforms.add(p);
	}
	public void removePlatform(Platform p) {
		platforms.remove(p);
	}
	
}
