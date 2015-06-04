package game.entity.implementations;

import game.entity.Entity;
import game.entity.MovingEntity;
import game.entity.SpriteContainer;
import game.entity.Stats;
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

public class Enemy extends MovingEntity implements EntityWithStats, DamagingEntity{
	
	private int dmg=10;
	private Stats stats; 

	private int delta=-1;
	private boolean red=false;
//	private int timer=0;
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level, Rectangle tileOffs) {
		super(x, y, w, h,level, mov);		
	   
	    spriteOffsets = tileOffs;// siempre asignarlas antes de inicializar mov!!
	    
  		Sprite currentAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
  		Sprite currentAnimSplit = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
  		currentAnimSplit.FlipAll();  		
  		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnim); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprites(3,currentAnimSplit); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprites(5, currentAnim);
		msm.add("normal", new NormalMove(normalState));
		msm.change("normal", "", false);
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
		
	}
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level, Rectangle tileOffsn,Stats st) {
		this(x, y,w, h, mov,level, tileOffsn);
		stats=st;
	}

	@Override
	public void update() {
		if(red){
			if(delta<=20){
				delta++;
			}else{
				red=false;
				delta=-1;
			}
		}
		
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
	}

	@Override
	public void render(RenderingLevel render) {
		Sprite cur = msm.getSprite();
		if(red){
			render.renderEntityColored(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur,0xDD0000);
		}else render.renderEntity(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur);
		
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
		//TODO implementar sistema de 2.muerte (3.barra de vida) generico para player y cualquier enemigo que lo necesite
		System.out.println("Enemy received "+dmg+" damage from "+e.getClass()+".");
		if(!red){
		stats.hit(dmg);
		red=true;
		}
		System.out.println(stats.getHP());
	}

	@Override
	public Stats getStats() {
		return stats;
	}

	


}
