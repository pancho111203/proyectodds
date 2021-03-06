package game.entity.weapons.ammo;

import game.entity.SpriteContainer;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;


public class RayZeus extends Ammo{
	
	public RayZeus(int x, int y, Level level, Movement mov, int dmg, boolean destroyOnHit) {
		super(x, y, 18, 72, level, mov, dmg,destroyOnHit);

	}

	@Override
	public void setSprite() {
		Sprite anim = new SingleSprite(WIDTH, HEIGHT, 0, 0, new Spritesheet(level.AM.getImage("rayoZeus")));
		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprites(8, anim); 
		
		msm.add("normal", new NormalMove(normalState, true));
		msm.change("normal", "", false);
		
	}

	
}
