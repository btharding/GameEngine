package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player extends GameObject implements GraphicalObject, SolidCollider, GravityObject{
	
	static BufferedImage sprite;
	static String url = "./img/Anglerfish.png";
	
	private float velX = 0;
	private float velY = 0;
	private float terminalVelY = 15;
	
	public Player(float x, float y) {
		super(x, y, 1, 64, 32);
		sprite = this.loadImage(this.url);
		CollidingObject.addCollider(this);
		SolidCollider.addSolidCollider(this);
	}
	
	//TODO: Fix sticking into wall when moving in the air
	public void tick() {
		CollidingObject.getCollisions(this);
		if(Game.keyInput.right.isPressed()) {
			this.velX = 3.5f;
		}else if(Game.keyInput.left.isPressed()) {
			this.velX = -3.5f;
		}else {
			this.velX = 0;
		}
		if(Game.keyInput.down.isPressed()) {
			this.velY = 10;
		}else if(Game.keyInput.up.isPressed()) {
			if(isOnGround()) {
				this.velY = -7.5f;
			}
		}
		if(!isOnGround()) {
			fall(this);
		}
		if(!SolidCollider.willCauseSolidCollision(this, velX, true)) {
			this.x += velX;
//		}else {
//			Rectangle s = SolidCollider.nextCollision(this, this.velX, true).getBounds();
//			if(this.velX > 0) {
//				this.x = s.x - this.width;
//				this.velX = 0;
//			}else {
//				this.x = s.x + s.width;
//				this.velX = 0;
//			}
		}
		if(!SolidCollider.willCauseSolidCollision(this, this.velY, false)) {
			this.y += this.velY;
		}else {
			Rectangle s = SolidCollider.nextCollision(this, this.velY, false).getBounds();
			if(this.velY > 0) {
				this.y = s.y - this.height;
				this.velY = 0;
			}else if(this.velY < 0 && !isOnGround()){
				this.y = s.y - s.height;
				this.terminalVelY = 0;
				System.out.println("This is my fault");
			}
		}
	}
	
	private boolean isOnGround() {
		return SolidCollider.willCauseSolidCollision(this, 5, false);
	}

	public void handleCollisions(LinkedList<CollidingObject> collisions) {
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getVelY() {
		return this.velY;
	}

	public float getTerminalVel() {
		return this.terminalVelY;
	}

	public void render(Graphics g, int xOffset, int yOffset) {
		this.drawSprite(g, sprite, (int)this.x+xOffset, (int)this.y+yOffset);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}
