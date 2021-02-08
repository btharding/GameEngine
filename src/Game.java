import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

/**
 *The main class. All setup is done here and global constants are held here. 
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -8921419424614180143L;

	
	public static final int WIDTH_BORDER = 14;
	public static final int HEIGHT_BORDER = 37;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final String TITLE = "Engine";
	public static Rectangle windowRect = new Rectangle(0,0,Game.WIDTH, Game.HEIGHT);
		
	private Thread thread;
	private boolean running = false;
	
	public static Handler handler = new Handler();
	public static KeyInput keyInput = new KeyInput();
	public static MouseInput mouseInput = new MouseInput();
	
	ExampleKeyListener e1;
	ExampleMouseListener e2;
	
	/**
	 * For everything that you want performed every frame.<br>
	 * Try to abstract everything to class-level tick() functions, rather than here
	 * @param delta The delta time generated in the run function
	 */
	private void tick(double delta) {
		handler.tick(delta);
	}
	
	/**
	 * Primary function is to clear the screen, and then call class-level render functions via the handler
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * Game constructor.<br>
	 * Constructors should be called here
	 */
	public Game() {
		new Window(WIDTH+WIDTH_BORDER, HEIGHT+HEIGHT_BORDER, TITLE, this);
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		e1 = new ExampleKeyListener(100, 100, 2);
		e2 = new ExampleMouseListener();
	}
	
	public static void main(String[] args){
		new Game();		
	}
	
	public synchronized void start() {
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
				tick(delta);
				delta--;
			}
			if(running) {
				render();
				frames ++;
				
			
				if(System.currentTimeMillis() - timer >1000) {
					timer += 1000;
					System.out.println(frames);
					frames = 0;
				}
			}
		}
		stop();		
	}
	
//	public void run() {
//		double targetFPS = 60;
//		double timePerFrame = 1000/targetFPS;
//		long sleepTime = 0;
//		long overtime = 0;
//		long time1 = System.currentTimeMillis();
//		while(running) {
//			tick(1);
//			render();
//			
//			sleepTime = (long) (timePerFrame - (System.currentTimeMillis() - time1) + overtime);
//			if(sleepTime > 0) {
//				time1 = System.currentTimeMillis();
//				try {
//					Thread.sleep(sleepTime);
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				overtime = sleepTime - (System.currentTimeMillis() - time1);
//			}else if(sleepTime < 0) {
//				overtime = sleepTime;
//				while(-overtime >= timePerFrame) {
//					tick(1);
//					render();
//					overtime = (long) (overtime + timePerFrame);
//				}
//			}
//			time1 = System.currentTimeMillis();
//		}
//		stop();
//	}

}
