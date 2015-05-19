package game.entity;

import game.entity.movement.Movement;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;
import game.level.Level;

public class Enemy extends MovingEntity {
	
	private Level level;
//	private int timer=0;
	
	Animator currAnim;
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y, w, h, mov);
		this.level=level;
		
	    currAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30);
	    
	  //collisions
  		spriteOffsets[0]= offsetXStart; // siempre asignarlas antes de inicializar mov!!
  		spriteOffsets[1]= offsetXEnd;
  		spriteOffsets[2]= offsetYStart;
  		spriteOffsets[3]= offsetYEnd;
	    
	}

	@Override
	public void update() {
		if(currAnim instanceof Animator){
			((Animator)currAnim).update();
		} 
		
		mov.update();
			
		
//		//TEST
//		timer++;
//		if(timer>60){
//			currAnim.FlipAll();
//			timer=0;
//		}
//		//
	}

	@Override
	public void render(RenderingLevel render) {
		int xInScreen = x-level.getXPosScreen();
		int yInScreen = y-level.getYPosScreen();
		
		render.renderEntity(xInScreen,yInScreen,currAnim);
	}


	@Override
	public void move(int movX, int movY) {
		x+=movX;
		y+=movY;
	}

	@Override
	public boolean collidesWithState(int s) {
		return (s==1)||(s==9); // colision con *solid* y con *void*
	}

	
}
