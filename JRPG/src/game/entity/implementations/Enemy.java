package game.entity.implementations;

import game.GameMaster;
import game.entity.Entity;
import game.entity.MovingEntity;
import game.entity.modules.DMGModule;
import game.entity.modules.HPModule;
import game.entity.movement.Movement;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityWithStats;
import game.graphics.Rendering;
import game.graphics.Sprite;
import game.level.Level;

import java.awt.Rectangle;

public abstract class Enemy extends MovingEntity implements EntityWithStats, DamagingEntity{
	
	protected HPModule hp_mod;
	protected DMGModule dmg_mod;

	protected boolean active = true;
	protected int MAXHP;
	protected int DMG;
	protected int IMMUNETIME;
	
	public Enemy(int x, int y,int w, int h, Movement mov, Level level) {
		super(x, y, w, h,level, mov);
		
	   
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
	
		
	}

	@Override
	public void update() {
		hp_mod.update();
		
		msm.update();
		
		mov.update();
		updateCollider();
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		
		if(!hp_mod.isAlive()){
			die();
		}
		

	}

	@Override
	public void render(Rendering render) {
		Sprite cur = msm.getSprite();
		if(hp_mod.isImmune()){
			render.renderEntityColored(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur,0xDD0000); 
		}
		else render.renderEntity(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur);
		
		if(!hp_mod.isFullHP()){
			int st = ((WIDTH - 50)/2);
			render.renderRectFilled(x-level.getXPosScreen()+st, y-level.getYPosScreen(), (int)(hp_mod.getPercentage()*50), 3, 0xDD0000); //hp bar
		}
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
		return dmg_mod.getDMG();
	}

	@Override
	public void dealtDamage(int d) {
		mov.stop(30);
	}

	@Override
	public abstract void receiveDmg(int dmg, Entity e);

	@Override
	public boolean isActive() {
		return active;
	}
	
	public void die(){
		GameMaster.getSingleton().kill(this);
	}
	
	public boolean isImmune(){
		return hp_mod.isImmune();
	}
	




}
