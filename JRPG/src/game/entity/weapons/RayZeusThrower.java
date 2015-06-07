package game.entity.weapons;

import game.entity.Entity;
import game.entity.movement.ForwardMovement;
import game.entity.movement.Movement;
import game.entity.movestate.IMove;
import game.entity.types.EntityWithStats;
import game.entity.weapons.ammo.RayZeus;
import game.graphics.Rendering;

public class RayZeusThrower implements Weapon{
	//TODO implementar el resto de metodos usando animacion
	private EntityWithStats parent;
	private int modifier = 2;
	public RayZeusThrower(EntityWithStats e) {
		parent = e;
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

		Movement mov = new ForwardMovement(((Entity)parent).level, xDir, yDir, true);
		RayZeus ray = new RayZeus(((Entity)parent).getX(), ((Entity)parent).getY(), 18, 72, ((Entity)parent).level, mov, parent.getDmg()*modifier);
		((Entity)parent).level.addEntityToList(ray);
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

}
