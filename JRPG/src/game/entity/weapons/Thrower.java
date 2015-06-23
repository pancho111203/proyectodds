package game.entity.weapons;

import game.entity.Entity;
import game.entity.movement.ForwardMovement;
import game.entity.movement.Movement;
import game.entity.movestate.IMove;
import game.entity.types.EntityWithStats;
import game.entity.weapons.ammo.Ammo;
import game.entity.weapons.ammo.RayZeus;
import game.entity.weapons.ammo.ThunderBall;
import game.entity.weapons.ammo.ThunderBallGreen;
import game.graphics.Rendering;

public class Thrower implements Weapon{
	private EntityWithStats parent;
	private int modifier;
	private String ammo_type;
	private int speed;
	public Thrower(EntityWithStats e, int dmg_modifier, String ammo_type, int speed) {
		parent = e;
		modifier = dmg_modifier;
		this.ammo_type = ammo_type;
		this.speed = speed;
	}
	@Override
	public void attack(int dir) {
		int xDir,yDir;
		switch(dir){
		case 0: xDir = 0; yDir = -1; break;
		case 1: xDir = 1; yDir = -1; break;
		case 2: xDir = 1; yDir = 0; break;
		case 3: xDir = 1; yDir = 1; break;
		case 4: xDir = 0; yDir = 1; break;
		case 5: xDir = -1; yDir = 1; break;
		case 6: xDir = -1; yDir = 0; break;
		case 7: xDir = -1; yDir = -1; break;
		default: xDir = 0; yDir = 1; 
		}
		Movement mov = new ForwardMovement(((Entity)parent).level, xDir*speed, yDir*speed, true);
		Ammo ammo = factoryAmmo(mov);
				
				
		if(ammo!=null){
			mov.initializeEntity(ammo);
			((Entity)parent).level.addEntityToList(ammo);
		}
	}

	private Ammo factoryAmmo(Movement mov) {
		
		switch(ammo_type){
		case "ThunderBall": return new ThunderBall(((Entity)parent).getX()+((Entity)parent).getWidth()/2, ((Entity)parent).getY()+((Entity)parent).getHeight()/2, ((Entity)parent).level, mov, parent.getDmg()*modifier, true);
		case "ThunderBallGreen": return new ThunderBallGreen(((Entity)parent).getX()+((Entity)parent).getWidth()/2, ((Entity)parent).getY()+((Entity)parent).getHeight()/2, ((Entity)parent).level, mov, parent.getDmg()*modifier, true);
		case "RayZeus": return new RayZeus(((Entity)parent).getX()+((Entity)parent).getWidth()/2, ((Entity)parent).getY()+((Entity)parent).getHeight()/2, ((Entity)parent).level, mov, parent.getDmg()*modifier, true);
		}
		
		return null;
	
	}
	@Override
	public void update() {
	}

	@Override
	public void render(Rendering render) {
	}

	@Override
	public void stopAttack() {
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public IMove getVisualMovement() {
		return null;
	}
	@Override
	public boolean customSprite() {
		return false;
	}
	@Override
	public boolean canAttack() {
		return true;
	}

}
