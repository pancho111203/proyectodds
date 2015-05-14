package game.entity;

import game.entity.movement.Movement;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

public class Player extends MovingEntity{

	private Level level;
	private Sprite currentAnim; //TODO mas adelante habr un animador por cada estado diferente(moviendose a la izq, der, arriba, abajo, saltando, etc.)
	
		
	public Player(int x, int y,int w, int h, Movement mov, Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y,w,h, mov);

		this.level = level;
		
		//collisions
		spriteOffsets[0]= offsetXStart; // siempre asignarlas antes de inicializar mov!!
		spriteOffsets[1]= offsetXEnd;
		spriteOffsets[2]= offsetYStart;
		spriteOffsets[3]= offsetYEnd;
		
		currentAnim = new Animator(WIDTH, HEIGHT, 2, 3, 1, new Spritesheet(level.AM.getImage("BloqueSprites01")), 60);
		//currentAnim = new Sprite(16,16,0,2,Spritesheet.tiles);
		
		
		this.x = x-currentAnim.getWidth()/2;
		this.y = y-currentAnim.getHeight()/2;
	}

	
	@Override
	public void update() {
		
		if(currentAnim instanceof Animator){
			((Animator)currentAnim).update();
		} 
		
		mov.update();
		
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

	

	public boolean isCentered(){
		
		if(x<0+level.screenW/2)return false;
		if(x>((level.getWidth()*TS)-(level.screenW/2)))return false;
		if(y<0+level.screenH/2)return false;
		if(y>((level.getHeight()*TS)-(level.screenH/2)))return false;
		
		return true;
	}
	
}
