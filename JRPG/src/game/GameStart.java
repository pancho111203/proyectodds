package game;
import game.graphics.Rendering;
import game.input.GameInput;
import game.states.IState;
import game.states.MenuState;
import game.states.StateEmpty;
import game.states.games.Game1State;
import game.states.games.Game2State;
import game.states.pause.PauseDecorator;
import game.states.pause.PausedState;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

import javax.swing.JFrame;
public class GameStart extends Canvas implements Runnable{
	
	//TODO /REF Cambiar todos los usos de GameStart a Singletons
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static boolean debug = false;
	
	public int fontSize = 30;
	public Font dialogFont = new Font("TimesRoman", Font.PLAIN, fontSize); 
	
	public static final String NAME = "My Game";
	public static final int WIDTH = 320; //width in pixels // tienen que ser multiplos de 16
	public static final int HEIGHT = 320;//height in pixels
	public static final int pixelSize = 2;//pixel size (default=2x2)
	
	public final float ONE_SEC_IN_NANOS = 1000000000;
	public final float UPDATES = ONE_SEC_IN_NANOS/60; // 60 ups (updates por segundo)
	
	//gestion de estados
	private HashMap<String,IState> states;
	private IState currentS;
	
	private Rendering screen;
	private static boolean isRunning = false;
	private GameInput gi;
	
	//Rendering
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public static void main(String[]args){
		GameStart theGame = GameStart.getSingleton();
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
	
	private static GameStart gamestart;
	
	public static GameStart getSingleton(){
		if(gamestart==null){
			gamestart = new GameStart();
		}
		
		return gamestart;
	}
	
	private GameStart(){
		
		setPreferredSize(new Dimension(WIDTH*pixelSize,HEIGHT*pixelSize));
		
		states = new HashMap<String,IState>();
		currentS = new StateEmpty();
		
		
		add("mainmenu", new MenuState(this,WIDTH,HEIGHT));
		add("game1", new Game1State(this,WIDTH,HEIGHT));
		add("game2", new Game2State(this,WIDTH,HEIGHT));
		
		gi= GameInput.getSingleton();
		gi.addListeners(this);
		
		change("mainmenu","init");
		
		
	}
		
	public void start(){

		//Starting gameloop
		isRunning = true;
		new Thread(this).start();
	}
	
	public void stop(){
		isRunning = false;
		System.exit(0);
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
			render(); 
			fps++;
			
			
		}
		Thread.currentThread().interrupt();
	}
	
	private void updateFrames(int fps, int ups) {
		System.out.println("FPS: "+fps+"  UPS: "+ups);
		
	}

	private synchronized void update(){ // this function handles the update for keyboards and delegates the update to the ccurrent state instance
		gi.update();
		currentS.update();
		if(gi.inputPressed(8)){
			changeDebug();
		}
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
		
		currentS.render();
		
		
		//
		//Generate the image by filling the pixels array
		int[] aux = screen.getPixels();
		for(int i=0;i<pixels.length;i++){
			pixels[i]=aux[i];
		}

		//DRAW GRAPHICS
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		currentS.renderGraphics(g);
		
		g.dispose();
		bs.show();
	}

	public void change(String newState, String params){
		currentS.onExit();
		currentS = states.get(newState);
		currentS.onEnter(params);
		
		screen = currentS.getRender();
	}
	
	public void add(String stName, IState state){
		states.put(stName, state);
	}
	
	public static boolean getDebug(){
		return debug;
	}
	
	private void changeDebug() {
		debug = !debug;
	}
	

	public void pause(PausedState pau){
		if(currentS instanceof PauseDecorator)return;
		currentS = new PauseDecorator(this, currentS, pau);
	}
	
	public void unpause(){
		if(!(currentS instanceof PauseDecorator))return;
		currentS = ((PauseDecorator)currentS).getPrevState();
	}
	
	public boolean isPaused(){
		if(currentS instanceof PauseDecorator)return true;
		return false;
	}
}
