package game.entity;

import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.Gamepad;
import game.input.Keyboard;
import game.level.Level;

import java.awt.event.KeyEvent;

public class Player extends Entity{

	private Gamepad pad;
	private Keyboard key;
	private int speed=1;
	private Level level;
	private Sprite currentAnim; //TODO mas adelante habr un animador por cada estado diferente(moviendose a la izq, der, arriba, abajo, saltando, etc.)
	
	public Player(int x, int y,Level level) {
		super(x, y);
		key = Keyboard.getSingleton();
		pad = new Gamepad();
		this.level = level;
		
		int startpointPlayerX = x + level.screenW/2;
		int startpointPlayerY = y + level.screenH/2;
		
		
		
		currentAnim = new Animator(WIDTH, HEIGHT, 2, 3, 1, Spritesheet.test, 60);
		
		this.x = startpointPlayerX-currentAnim.getWidth()/2;
		this.y = startpointPlayerY-currentAnim.getHeight()/2;
	}

	@Override
	public void update() {
		
		if(currentAnim instanceof Animator){
			((Animator)currentAnim).update();
		} 
		//TODO es temporal
		pad.pollController();
		if(key.keyDown(KeyEvent.VK_W)||key.keyDown(KeyEvent.VK_UP)||pad.getPadState(pad.Lup)){
			move(0,-speed);
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)||pad.getPadState(pad.Ldown)){
			move(0, speed);
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)||pad.getPadState(pad.Lleft)){
			move(-speed, 0);
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)||pad.getPadState(pad.Lright)){
			move(speed, 0);
		}
		//
		
	}

	@Override
	public void render(RenderingLevel render) {

		int xInScreen = x-level.getXPosScreen();
		int yInScreen = y-level.getYPosScreen();

		render.renderEntity(xInScreen,yInScreen,currentAnim); // hay que pasar la posicion del jugador respecto a la pantalla(en pixeles)
	}
	
	public void move(int movX,int movY){
		//TODO falta comprobar bounds de player y comprobar bounds de level con funcion auxiliar en level
		
		//TODO usa patron estrategia para diferentes tipos de movimiento
		x+=movX;
		y+=movY;
		
		level.moveFocus(movX, movY);
		
		/*		movimiento:
		 * 			derecha (0+,0)
		 * 			izquierda (0-,0)
		 * 			arriba(0,0-)
		 * 			abajo(0,0+)
		 */
	}

}
