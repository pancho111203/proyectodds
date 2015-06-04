package game.entity.weapons;

import game.AssetManager;
import game.GameMaster;
import game.GameStart;
import game.entity.Entity;
import game.entity.SpriteContainerWithReceiver;
import game.entity.SpriteFinishReceiver;
import game.entity.collision.Collider;
import game.entity.collision.OwnsCollider;
import game.entity.movestate.IMove;
import game.entity.movestate.LockMove;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityWithStats;
import game.graphics.Animator;
import game.graphics.Rendering;
import game.graphics.Spritesheet;

import java.awt.Rectangle;
import java.util.HashMap;

public class Sword implements Weapon, OwnsCollider, DamagingEntity{

	private SpriteFinishReceiver parent;
	private Entity ent;
	private boolean active = false;
	private IMove move;
	private HashMap<String,Rectangle> collideRects;//estos solo se usan para saber la referencia con respecto al player(al crear el collider creamos un rectangle nuevo)
	private Collider collider;
	private int dmg = 20;
	
	//las clases de armas se encargan de definir los sprites que van a usar y crean el IMove
	//tambien se ecargan de gestionar las colisiones, etc
	//pero la clase que usa el arma tiene que recibir el evento de cuando el ataque termina y gestionar el cambio de estado por si misma
	
	public Sword(SpriteFinishReceiver en, String attackFinishEvent){
		if(!(en instanceof Entity)){
			System.out.println("ERROR FATAL, se ha asigado un arma a una clase no-Entity");
			System.exit(0);
		}
		
		parent = en;
		ent = (Entity)parent;
		
		
		
		Animator attackAnimSword = new Animator(60, 68, 0, 0, 3, new Spritesheet(AssetManager.getSingleton().getImage("ataqueFrente")), 6,true);
		attackAnimSword.setXOffset(-15);
		
		SpriteContainerWithReceiver attackStateContainer = new SpriteContainerWithReceiver(parent);
		attackStateContainer.addAnimatorWithReceiver("0", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("1", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("2", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("3", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("4", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("5", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("6", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("7", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("8", attackAnimSword, attackFinishEvent);
		move = new LockMove(attackStateContainer);
		
		collideRects = new HashMap<String, Rectangle>();
		
		Rectangle down = new Rectangle(-15,26,60,42);
		collideRects.put("0", down);
		collideRects.put("1", down);
		collideRects.put("2", down);
		collideRects.put("3", down);
		collideRects.put("4", down);
		collideRects.put("5", down);
		collideRects.put("6", down);
		collideRects.put("7", down);
		collideRects.put("8", down);
	}
	public void update(){
		if(active){
			//System.out.println("atacando");
			collider.checkCollisions();
			
			
		}
	}
	
	@Override
	public void render(Rendering render) {
		if(active){
			debug(render);
		}
	}
	
	public void debug(Rendering render){
		if(!GameStart.getDebug())return;
		
		int entityColliderColor = 0xffff0000;
		
		Rectangle rect = collider.getRect();
		render.renderRect(((int)rect.getX()-ent.level.getXPosScreen()), ((int)rect.getY()-ent.level.getYPosScreen()), (int)(rect.getWidth()), (int)(rect.getHeight()), entityColliderColor);

	}
	
	@Override
	public void attack(int dir) {
		active = true;
		
		String d = dir+"";
		
		Rectangle reference = collideRects.get(d);
		
		collider = new Collider(this,new Rectangle(ent.getX()+(int)reference.getX(),ent.getY()+(int)reference.getY(),(int)reference.getWidth(),(int)reference.getHeight()),ent.level);
	}

	@Override
	public void stopAttack() {
		active = false;
	}
	@Override
	public String getType() {
		return "Sword";
	}
	public IMove getVisualMovement() {
		return move;
	}
	@Override
	public void collide(Entity e) {
		if(parent.equals(e)){
			return;
		}
		
		if(e instanceof EntityWithStats){
			GameMaster.performAttack(this, (EntityWithStats)e, ent);
		}	
	}
	@Override
	public int getDmg() {
		return ((EntityWithStats)ent).getStats().getDMG();
	}
	@Override
	public void dealtDamage(int d) {
		
	}
		

}
