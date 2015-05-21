package game.entity;

import game.entity.collision.Collider;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.entity.movestate.SwimMove;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

public class Player extends MovingEntity{

	private Collider colls;
	int delta=-1;
	boolean red=false;
		
	public Player(int x, int y,int w, int h, Movement mov, Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y,w,h,level, mov);
		
	    spriteOffsets = new Rectangle(offsetXStart,offsetYStart,offsetXEnd,offsetYEnd);// siempre asignarlas antes de inicializar mov!!
		
		
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
		
		Sprite swimAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30);
		//currentAnim = new Sprite(16,16,0,2,Spritesheet.tiles);
		SpriteContainer swimState = new SpriteContainer();
		swimState.addSprite("0", swimAnim); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		swimState.addSprite("1", swimAnim); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		swimState.addSprite("2", swimAnim);
		swimState.addSprite("3", swimAnim);
		swimState.addSprite("4", swimAnim);
		swimState.addSprite("5", swimAnim);
		swimState.addSprite("6", swimAnim);
		swimState.addSprite("7", swimAnim);
		swimState.addSprite("8", swimAnim);
		msm.add("swim", new SwimMove(swimState));
		msm.change("normal", "");
		
		
		this.x = x;
		this.y = y;
		
		
		colls = new Collider(this.x,this.y,w,h,this);
	
		
		collider = new Rectangle((int)(spriteOffsets.getWidth()-spriteOffsets.getX()),(int)(spriteOffsets.getHeight()-spriteOffsets.getY()));
		collider.setLocation((int)(this.x+spriteOffsets.getX()), (int)(this.y+spriteOffsets.getY()));

	
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
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		updateCollider();
		colls.checkCollisions();
		
		
	}

	@Override
	public void render(RenderingLevel render) {
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


	@Override
	public void collide(Entity e) {
	}
	
	public void takeDamage(int d){
		red = true;
	}


	
}
