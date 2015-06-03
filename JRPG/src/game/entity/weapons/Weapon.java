package game.entity.weapons;

import game.entity.movestate.IMove;


//TODO clase "Unarmed" implementando esta int
public interface Weapon {
	public void attack();
	public void update();
	public void stopAttack();
	public String getType();
	public IMove getVisualMovement();
	
}
