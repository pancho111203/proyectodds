package game.entity.weapons.ammo;

import game.entity.SpriteContainer;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.graphics.Animator;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

public class ThunderBall extends Ammo{

	public ThunderBall(int x, int y, Level level, Movement mov, int dmg, boolean destroyOnHit) {
		super(x, y, 30, 30, level, mov, dmg,destroyOnHit);
	
		
		addCustomCollider(new Rectangle(5,5,25,25));
	}

	@Override
	public void setSprite() {

		Sprite anim = new Animator(WIDTH, HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("thunderBall")), 10,false);
		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprites(8, anim); 
		
		msm.add("normal", new NormalMove(normalState, true));
		msm.change("normal", "", false);
		
	}

}
