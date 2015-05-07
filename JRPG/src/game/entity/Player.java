package game.entity;

import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.Gamepad;
import game.input.Keyboard;
import game.level.Level;
import game.level.tiles.Tile;

import java.awt.event.KeyEvent;

public class Player extends Entity{

	private Gamepad pad;
	private Keyboard key;
	private int speed=1;
	private Level level;
	private Sprite currentAnim; //TODO mas adelante habr un animador por cada estado diferente(moviendose a la izq, der, arriba, abajo, saltando, etc.)
	
	private final int WIDTH;
	private final int HEIGHT;
	
	private final int WIDTHINTILES = 2;
	private final int HEIGHTINTILES = 3;
	
	//collisions
	private int startColX;
	private int startColY;
	private int endColX;
	private int endColY;
	
	private int dir = 0;
		
	private final int TS;
	
	public Player(int x, int y,Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y);
		key = Keyboard.getSingleton();
		pad = new Gamepad();
		this.level = level;
		
		TS = Level.TILESIZE;
		
		WIDTH = WIDTHINTILES*TS;
		HEIGHT = HEIGHTINTILES*TS;
		
		
		//collisions
		startColX = offsetXStart;
		startColY = offsetYStart;
		endColX = offsetXEnd;
		endColY = offsetYEnd;
		
		int startpointPlayerX = x + level.screenW/2;
		int startpointPlayerY = y + level.screenH/2;
		
		
		currentAnim = new Animator(WIDTH, HEIGHT, 2, 3, 1, new Spritesheet(level.AM.getImage("BloqueSprites01")), 60);
		//currentAnim = new Sprite(16,16,0,2,Spritesheet.tiles);
		
		
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
			dir = 0;
			move(0,-speed);
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)||pad.getPadState(pad.Ldown)){
			dir = 2;
			move(0, speed);
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)||pad.getPadState(pad.Lleft)){
			dir = 3;
			move(-speed, 0);
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)||pad.getPadState(pad.Lright)){
			dir = 1;
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
		
		if(checkCollision(movX,movY)){
			return;
		}
		//  H=24   W=30
		// x/y=0 arriba izquierda   x=60 y=64
		
		x+=movX;
		y+=movY;
		//si el mapa se sale de la camara || el player está volviendo al centro de esta: (<- contenido de los ifs)
		if(movX+level.getXPosScreen()<0 || x<0+level.screenW/2)movX=0;
		
		if(( (level.getWidth()*TS)-level.screenW) < (movX+level.getXPosScreen()) 
				|| x>((level.getWidth()*TS)-(level.screenW/2))) movX=0;
		
		
		if(movY+level.getYPosScreen()<0 || y<0+level.screenH/2)	movY=0;
		
		if(((level.getHeight()*TS)-level.screenH) < (movY+level.getYPosScreen()) 
				|| y>((level.getHeight()*TS)-(level.screenH/2))) movY=0;
		
		level.moveFocus(movX, movY);
		
		/*		movimiento:
		 * 			derecha (0+,0)
		 * 			izquierda (0-,0)
		 * 			arriba(0,0-)
		 * 			abajo(0,0+)
		 */
	}

	private boolean checkCollision(int movX, int movY) {
		
		
		int nextX = movX + x;
		int nextY = movY + y;
		
		
		
		switch (dir){
		case 0: //va hacia arriba
			
			if(isSolid(nextX+startColX,nextY+startColY, -1) || isSolid(nextX+endColX,nextY+startColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColX < endColX ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX+(i*TS),nextY+startColY, 2)){return true;}
			}
			
			return false;
			
		case 1: //va hacia derecha
			
			if(isSolid(nextX+endColX,nextY+endColY, -1) || isSolid(nextX+endColX,nextY+startColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColY < endColY ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+endColX,nextY+startColY+(i*TS), 3)){return true;}
			}
			
			return false;
			
		case 2: //va hacia abajo
			
			if(isSolid(nextX+endColX,nextY+endColY, -1) || isSolid(nextX+startColX,nextY+endColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColX < endColX ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX+(i*TS),nextY+endColY, 0)){return true;}
			}
			
			return false;
						
		case 3: //va hacia izq
			
			if(isSolid(nextX+startColX,nextY+endColY, -1) || isSolid(nextX+startColX,nextY+startColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColY < endColY ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX,nextY+startColY+(i*TS), 1)){return true;}
			}
			
			return false;
			
		}
		
		return false;
	}
	
	private boolean isSolid(int nx, int ny, int side){ 
		//si side no es -1, el metodo comprueba ambos subtiles del lado indicado
		
		int restX = nx%TS;
		int restY = ny%TS;
		
		int middle = TS/2;
		
		int pos, posSideX, posSideY;
		
		Tile hit = level.getTile(nx/TS,ny/TS );
		
		if(restX<middle){ // parte izquierda
			if(restY<middle){//arriba
				pos = 0;
				posSideX = 1;
				posSideY = 2;
			}else{//abajo
				pos = 2;
				posSideX = 3;
				posSideY = 0;
			}
		}else{ // parte derecha
			if(restY<middle){//arriba
				pos = 1;
				posSideX = 0;
				posSideY = 3;
			}else{//abajo
				pos = 3;
				posSideX = 2;
				posSideY = 1;
			}
		}
		
		
		if(side==0||side==2){ // check horizontal
			return (hit.getState(pos)==1)||(hit.getState(posSideX)==1);
		}else if(side==1||side==3){// check vertical
			return (hit.getState(pos)==1)||(hit.getState(posSideY)==1);
		}else{
			return hit.getState(pos)==1;
		}
	}

	public boolean isCentered(){
		
		if(x<0+level.screenW/2)return false;
		if(x>((level.getWidth()*TS)-(level.screenW/2)))return false;
		if(y<0+level.screenH/2)return false;
		if(y>((level.getHeight()*TS)-(level.screenH/2)))return false;
		
		return true;
	}
	
}
