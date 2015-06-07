package game.entity.weapons.ammo;

import game.GameMaster;
import game.entity.MovingEntity;
import game.entity.SpriteContainer;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.entity.types.DamagingEntity;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

public class RayZeus extends MovingEntity implements DamagingEntity{

	private int dmg;
	public RayZeus(int x, int y, int w, int h, Level level, Movement mov, int dmg) {
		super(x, y, w, h, level, mov);

		this.dmg = dmg;
		
		Sprite anim = new SingleSprite(w, h, 0, 0, new Spritesheet(level.AM.getImage("rayoZeus")));
		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprites(8, anim); 
		
		msm.add("normal", new NormalMove(normalState, false));
		msm.change("normal", "", false);
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(this.x+colliderOffsets.getX()), (int)(this.y+colliderOffsets.getY()));

	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public int getDmg() {
		return dmg;
	}

	@Override
	public void dealtDamage(int d) {
		GameMaster.getSingleton().kill(this);
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
		if(s==1 || s==9){
			GameMaster.getSingleton().kill(this);
			return true;
		}
		
		return false;
	}

	@Override
	public void update() {

		msm.update();
		mov.update();
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		updateCollider();
	}

	@Override
	public void render(RenderingLevel render) {
		render.renderEntity(xInScreen,yInScreen,msm.getSprite());
	}

}
