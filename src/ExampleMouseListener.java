import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ExampleMouseListener extends GameObject implements GraphicalObject, SolidCollider{

	static BufferedImage sprite;
	static String url = "./img/Cursor.png";
	
	public ExampleMouseListener() {
		super(0, 0, 10, 32, 32);
		sprite = this.loadImage(ExampleMouseListener.url);
		CollidingObject.addCollider(this);
		SolidCollider.addSolidCollider(this);
		Game.handler.addObject(this);
	}

	@Override
	public void tick(double delta) {
		CollidingObject.getCollisions(this);
		this.x = (float) (MouseInput.mousePos.x - (0.5*this.width));
		this.y = (float) (MouseInput.mousePos.y - (0.5*this.height));
	}

	@Override
	public void render(Graphics g) {
		this.drawSprite(g, sprite, (int)this.x, (int)this.y);
	}

	@Override
	public void handleCollisions(LinkedList<CollidingObject> collisions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
	}

}
