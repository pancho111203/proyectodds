package game.entity;

import game.entity.collision.Collider;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

public class Player extends MovingEntity{

	private Level level;
	private Collider collider;
	int delta=-1;
	boolean red=false;
		
	public Player(int x, int y,int w, int h, Movement mov, Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y,w,h, mov);
		this.level = level;
		
		//collisions
		spriteOffsets[0]= offsetXStart; // siempre asignarlas antes de inicializar mov!!
		spriteOffsets[1]= offsetXEnd;
		spriteOffsets[2]= offsetYStart;
		spriteOffsets[3]= offsetYEnd;
		
		
		
		Sprite currentAnim = new Animator(WIDTH, HEIGHT, 0, 0, 3, new Spritesheet(level.AM.getImage("MinotauroFrontal")), 15);
		//currentAnim = new Sprite(16,16,0,2,Spritesheet.tiles);
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnim); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprite("1", currentAnim); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprite("2", currentAnim);
		normalState.addSprite("3", currentAnim);
		normalState.addSprite("4", currentAnim);
		normalState.addSprite("5", currentAnim);
		normalState.addSprite("6", currentAnim);
		normalState.addSprite("7", currentAnim);
		normalState.addSprite("8", currentAnim);
		msm.add("normal", new NormalMove(normalState));
		msm.change("normal", "");
		
		this.x = x-currentAnim.getWidth()/2;
		this.y = y-currentAnim.getHeight()/2;
		
		collider = new Collider(this.x,this.y,w,h,this);
	}

	
	@Override
	public void update() {
		if(red&&delta<=10){
			delta++;
		}else if(red&&delta>10){
			red=false;
			delta=-1;
		}
		msm.update();
		mov.update();
		collider.update(x,y);
	}

	@Override
	public void render(RenderingLevel render) {

		int xInScreen = x-level.getXPosScreen();
		int yInScreen = y-level.getYPosScreen();

		if(red){
			render.renderEntityColored(xInScreen,yInScreen,msm.getSprite(),0xDD0000); 
		}
		else render.renderEntity(xInScreen,yInScreen,msm.getSprite());

	}
	
	public void move(int movX,int movY){
		msm.move(movX, movY);
		
		movX = msm.getMovX();
		movY = msm.getMovY();
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


	@Override
	public boolean collidesWithState(int s) {
		return s==1; //colision con *solid*
	}
	
	public void collidesWith(Collider e){
		red=true;
	}
	


	
}
