package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	
	public static KeyInput keyInput = new KeyInput();
	public static MouseInput mouseInput = new MouseInput();
	
	private int xOffset = 0, yOffset = 0;
	
	private LinkedList<Level> levels = new LinkedList<>();
	private Level currentLevel = new Level();
	
	private Player player;
	
	public Game() {
		new Window(this);
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		player = new Player(100, 100);
		currentLevel.addEntity(player);
		Platform p = new Platform(0, Window.HEIGHT-32, Window.WIDTH, 32);
		currentLevel.addPlatform(p);
		p = new Platform(100, Window.HEIGHT-96, 150, 32);
		currentLevel.addPlatform(p);
	}
	
	private void tick() {
		currentLevel.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		
		currentLevel.render(g, xOffset, yOffset);
		
		g.dispose();
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
				frames ++;
				
			
				if(System.currentTimeMillis() - timer >1000) {
					timer += 1000;
//					System.out.println(frames);
					frames = 0;
				}
			}
		}
		stop();		
	}

	
//	public void run() {
//		int fps = 30;
//		long frameTime = 1000000000 / fps;
//		long currentTime = System.nanoTime();
//		long nextFrame;
//		while(running) {
//			nextFrame = currentTime + frameTime;
//			tick();
//			render();
//			while(System.nanoTime() <= nextFrame) {};
//			currentTime = System.nanoTime();
//		}
//		stop();	   
//	}
	
	public synchronized void start() {
		//The server is started in this function
		thread = new Thread(this);
		thread.start();
		running = true;
		this.requestFocus();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
