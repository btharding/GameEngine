package game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * /**
 * Interface to be implemented by any objects that are to display sprites<br>
 * Includes default loading and rendering scripts, which can be overwritten for complex cases <br>
 * (if there is more than one instance of a given complex case, form it into its own interface extending this)<br>
 * <br>
 * <b>Required class variables</b><br>
 * <pre>static BufferedImage sprite;<br>
 * static String url = "path/to/image.png";</pre><br>
 * <br>
 * <b>In the constructor</b><br>
 * <pre>sprite = this.loadImage(this.url);</pre><br>
 * <br>
 * <b>In the render method</b><br>
 * <pre>this.drawSprite(g, sprite, (int)this.x, (int)this.y);</pre>
 */
public interface GraphicalObject {
	
	/**
	 * Loads a sprite from a file, throwing an exception if an error occurs.<br>
	 * This should be called in the constructor of any object that needs to load a sprite
	 * @param url The relative url to the sprite image
	 * @return A BufferedImage object of the loaded sprite, or null if there was an error
	 */
	default BufferedImage loadImage(String url) {
		try {
			BufferedImage sprite = ImageIO.read(new File(url));;
			return sprite;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading " + url);
			return null;
		}
	}
	/**
	 * Renders a sprite
	 * @param g The graphics object onto which the sprite will be rendered
	 * @param sprite The BufferedImage to be rendered
	 * @param x The x coordinate at which the image will be rendered
	 * @param y The y coordinate at which the image will be rendered
	 */
	default void drawSprite(Graphics g, BufferedImage sprite, int x, int y) {
		g.drawImage(sprite, x, y, null);
	}
}
