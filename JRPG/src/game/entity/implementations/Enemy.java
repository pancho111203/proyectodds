package game.entity.implementations;

import game.entity.Entity;
import game.entity.MovingEntity;
import game.entity.SpriteContainer;
import game.entity.modules.HPModule;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityWithStats;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

import auxiliar.Vector2D;

public class Enemy extends MovingEntity implements EntityWithStats, DamagingEntity{
	
	private int dmg=10;
	private HPModule hp_mod;
//	private int timer=0;
	
	public final int MAXHP=100;
	private final int IMMUNETIME = 25;
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level, Rectangle tileOffs) {
		super(x, y, w, h,level, mov);
		
	   
	    spriteOffsets = tileOffs;// siempre asignarlas antes de inicializar mov!!

	    
  		Sprite currentAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
  		Sprite currentAnimSplit = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
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
		msm.add("normal", new NormalMove(normalState, false));
		msm.change("normal", "", false);
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
		
		
		hp_mod = new HPModule(MAXHP, MAXHP, IMMUNETIME);
	}

	@Override
	public void update() {
		
		hp_mod.update();
		
		msm.update();
		
		mov.update();
		updateCollider();
//		//TEST
//		timer++;
//		if(timer>60){
//			currAnim.FlipAll();
//			timer=0;
//		}
//		//
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		
		if(!hp_mod.isAlive()){
			die();
		}
	}

	@Override
	public void render(RenderingLevel render) {
		Sprite cur = msm.getSprite();
		if(hp_mod.isImmune()){
			render.renderEntityColored(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur,0xDD0000); 
		}
		else render.renderEntity(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur);
		
		debug(render);
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

	@Override
	public int getDmg() {
		return dmg;
	}

	@Override
	public void dealtDamage(int d) {
		mov.stop(30);
	}

	@Override
	public void receiveDmg(int dmg, Entity e) {
		//TODO implementar barra de vida
		
		if(!hp_mod.isImmune()){
			push(new Vector2D(e.getX(), e.getY()), 15);
			hp_mod.hit(dmg);
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
	public void die(){
		//TODO implementar muerte usando animacion
		setToDestroy();
	}

}
