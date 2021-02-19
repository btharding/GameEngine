package game;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import game.Game;

public class Window extends Canvas{
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Engine";
	public static Rectangle windowRect = new Rectangle(0,0,Game.WIDTH, Game.HEIGHT);

	private static final long serialVersionUID = 1877720651231192133L;
	
	public Window(Game game) {
		JFrame frame = new JFrame(TITLE);
		Dimension d = new Dimension(WIDTH, HEIGHT);
		Container c = frame.getContentPane();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.setPreferredSize(d);
		c.setMaximumSize(d);
		c.setMinimumSize(d);
		

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.pack();
		game.start();
	}

}
