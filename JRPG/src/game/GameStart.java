package game;
import game.graphics.Rendering;
import game.input.Keyboard;
import game.states.LevelState;
import game.states.MainMenuState;

import java.awt.Canvas;
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
	public static final int WIDTH = 800; //width in pixels
	public static final int HEIGHT = 600;//height in pixels
	public static final int pixelSize = 2;//pixel size (default=2x2)
	
	public final float ONE_SEC_IN_NANOS = 1000000000;
	public final float UPDATES = ONE_SEC_IN_NANOS/60; // 60 ups (updates por segundo)
	
	
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
		frame.setIgnoreRepaint(true);
		
		theGame.start();
		
	}
	
	public GameStart(){
		
		setPreferredSize(new Dimension(WIDTH*pixelSize,HEIGHT*pixelSize));
		
		game = new StateMachine();
		
		game.add("mainmenu", new MainMenuState(game,WIDTH,HEIGHT));
		game.add("level1", new LevelState(game,WIDTH,HEIGHT));
		
		key = Keyboard.getSingleton();
		addKeyListener(key);
		
		changeState("level1","init"); 
		
		
	}
	// only use this method to change state
	public void changeState(String state, String params){
		game.change(state, params);		
		screen = game.getRender();
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
		
		float prevUpdate = System.nanoTime();
		float diff, timeNow;
		float delta = 0, delta2 = 0;
		int ups=0,fps=0;
		while(isRunning){
			timeNow = System.nanoTime();
			diff = timeNow-prevUpdate;
			prevUpdate = timeNow;
			
			delta2+=diff;
			
			delta+=diff; // delta calcula el tiempo a partir de el ultimo update()
			
			if(delta2>ONE_SEC_IN_NANOS){ // OPCIONAL: lo uso para calcular fps y ups
				updateFrames(fps,ups);
				delta2 = 0;
				fps=0;
				ups=0;
			}
			
			while(delta>=UPDATES){ // 60 veces por segundo se reinicia el delta y se ejecuta update, uso while para evitar que el update se retrase si los ups son inferiores a 60
				delta -= UPDATES; // los nanosegundos perdidosse guardan para la proxima iteracion, asi el estado del programa siempre es estable a 60ups
				update();
				ups++;
			}
			
			render(); // el metodo render no esta limitado, se ejecuta las veces que la maquina lo permita
			fps++;
			
			
			
		}
	}
	
	private void updateFrames(int fps, int ups) {
		System.out.println("FPS: "+fps+"  UPS: "+ups);
		
	}

	private synchronized void update(){ // this function handles the update for keyboards and delegates the update to the ccurrent state instance
		key.update();
	    game.update();
	    
	}
	
	private synchronized void render(){ // this function does all the screen reseting and buffering and delegates the functonality to the current state instance
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
		int[] aux = screen.getPixels();
		for(int i=0;i<pixels.length;i++){
			pixels[i]=aux[i];
		}

		//DRAW GRAPHICS
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
}
