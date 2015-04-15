package game;
import game.graphics.Rendering;
import game.input.Keyboard;
import game.states.LevelState;
import game.states.MainMenuState;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
public class GameStart extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "My Game";
	public static final int WIDTH = 300; //width in pixels
	public static final int HEIGHT = 300;//height in pixels
	public static final int pixelSize = 2;//pixel size (default=2x2)
	
	
	private Rendering screen;
	private StateMachine game;
	private static boolean isRunning = false;
	private Keyboard key;
	
	//Rendering
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public static void main(String[]args){
		GameStart theGame = new GameStart();
		
		JFrame frame = new JFrame();
		frame.add(theGame);
		frame.pack();
		frame.setTitle(NAME);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		frame.setIgnoreRepaint(true);
		
		
		theGame.start();
	}
	
	public GameStart(){
		
		setPreferredSize(new Dimension(WIDTH*pixelSize,HEIGHT*pixelSize));
		
		game = new StateMachine();
		
		game.add("mainmenu", new MainMenuState(game,WIDTH,HEIGHT));
		game.add("level1", new LevelState(game,WIDTH,HEIGHT));
		
		key = new Keyboard();
		addKeyListener(key);
		
		changeState("level1","init"); 
		
		
	}
	// only use this method to change state!
	public void changeState(String state, String params){
		game.change(state, params);		
		screen = game.getRender();
	}

	
	private float getElapsedFrameTime(){
		return 0.0f;
	}
	
	public void start(){

		//Starting gameloop
		isRunning = true;
		new Thread(this).start();
	}
	
	public void stop(){
		isRunning = false;
	}
	
	@Override
	public void run() {
		
		while(isRunning){
			update();
			render();
		}
	}
	
	public void update(){
	
	    float elapsedTime = getElapsedFrameTime();
	    game.update(elapsedTime);
	    
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		screen.clear(); // set the pixels to 0
		//
		
		game.render();
		
		
		//
		//Generate the image by filling the pixels array
		for(int i=0;i<pixels.length;i++){
			pixels[i]=screen.pixels[i];
		}

		//DRAW GRAPHICS
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
}
