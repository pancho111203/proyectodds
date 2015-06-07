package game.entity.weapons.ammo;

import game.entity.MovingEntity;
import game.entity.movement.Movement;
import game.entity.types.DamagingEntity;
import game.graphics.RenderingLevel;
import game.level.Level;

public class ThunderBall extends MovingEntity implements DamagingEntity{

	public ThunderBall(int x, int y, int w, int h, Level level, Movement mov) {
		super(x, y, w, h, level, mov);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dealtDamage(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int movX, int movY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collidesWithState(int s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(RenderingLevel render) {
		// TODO Auto-generated method stub
		
	}

}
