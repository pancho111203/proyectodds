package game.entity;

import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

public class Enemy extends MovingEntity {
	
	private Level level;
//	private int timer=0;
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level, int offsetXStart, int offsetXEnd, int offsetYStart, int offsetYEnd) {
		super(x, y, w, h, mov);
		this.level=level;
		
	   
	    
	  //collisions
  		spriteOffsets[0]= offsetXStart; // siempre asignarlas antes de inicializar mov!!
  		spriteOffsets[1]= offsetXEnd;
  		spriteOffsets[2]= offsetYStart;
  		spriteOffsets[3]= offsetYEnd;

  		
  		Sprite currentAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30);
  		Sprite currentAnimSplit = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30);
  		currentAnimSplit.FlipAll();
  		
  		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnim); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprite("1", currentAnimSplit); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprite("2", currentAnimSplit);
		normalState.addSprite("3", currentAnimSplit);
		normalState.addSprite("4", currentAnim);
		normalState.addSprite("5", currentAnim);
		normalState.addSprite("6", currentAnim);
		normalState.addSprite("7", currentAnim);
		normalState.addSprite("8", currentAnim);
		msm.add("normal", new NormalMove(normalState));
		msm.change("normal", "");
	}

	@Override
	public void update() {
		msm.update();
		
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
		
		render.renderEntity(xInScreen,yInScreen,msm.getSprite());
	}


	@Override
	public void move(int movX, int movY) {
		msm.move(movX, movY);
		
		movX = msm.getMovX();
		movY = msm.getMovY();
		
		x+=movX;
		y+=movY;
	}

	@Override
	public boolean collidesWithState(int s) {
		return (s==1)||(s==9); // colision con *solid* y con *void*
	}

	
}
