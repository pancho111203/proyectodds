package game.entity.weapons;

import game.entity.movestate.IMove;
import game.graphics.Rendering;


public interface Weapon {
	public void attack(int dir);
	public void update();
	public void render(Rendering render);
	public void stopAttack();
	public String getType();
	public IMove getVisualMovement();
	public boolean customSprite();
	public boolean canAttack();
}
