import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ExampleMouseListener extends GameObject implements GraphicalObject{

	static BufferedImage sprite;
	static String url = "./img/Cursor.png";
	
	public ExampleMouseListener() {
		super(0, 0, 10, 32, 32);
		sprite = this.loadImage(this.url);
		Game.handler.addObject(this);
	}

	@Override
	public void tick() {
		this.x = (float) (MouseInput.mousePos.x - (0.5*this.width));
		this.y = (float) (MouseInput.mousePos.y - (0.5*this.height));
	}

	@Override
	public void render(Graphics g) {
		this.drawSprite(g, sprite, (int)this.x, (int)this.y);
	}

}
