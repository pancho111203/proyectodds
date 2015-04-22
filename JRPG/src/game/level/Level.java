package game.level;

import game.entity.EntityList;
import game.graphics.RenderingLevel;
import game.input.Keyboard;

import java.awt.event.KeyEvent;

public abstract class Level {
	private EntityList entList;
	
	public static final int TILESIZE = 16;
	
	private final int START_POS_X = 0; // posiciones iniciales de la camara
	private final int START_POS_Y = 0;
	
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	
	
	//TEST Movimiento desde el nivel, mas adelante planeo hacerlo desde player
	private Keyboard key;
	private int speed = 1;
	//
	
	
	public Level(){
		entList = new EntityList();
		xOffset = START_POS_X;
		yOffset = START_POS_Y;
		
		key = Keyboard.getSingleton();
				
	}
	public void update(){
		//TEST mov
		if(key.keyDown(KeyEvent.VK_W)||key.keyDown(KeyEvent.VK_UP)){
			moveFocus(0, -speed);
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)){
			moveFocus(0, speed);
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)){
			moveFocus(-speed, 0);
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)){
			moveFocus(speed, 0);
		}
		//
		entList.update();
	}
	public void render(RenderingLevel render){
		
		
		
		
		
		entList.render(render);
		
	}
	public abstract void loadLevel();
	
	public void moveFocus(int movX,int movY){ // movX y movY son el delta que se tiene que añadir a los offset correspondientes
		xOffset += movX;
		yOffset += movY;
		
		/*		movimiento:
		 * 			derecha (0+,0)
		 * 			izquierda (0-,0)
		 * 			arriba(0,0-)
		 * 			abajo(0,0+)
		 */
	}
}