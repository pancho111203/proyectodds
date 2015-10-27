package game.entity.implementations;

import game.entity.Entity;
import game.entity.types.EntityActionable;
import game.graphics.Rendering;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;
import auxiliar.AssetManager;

public class Hearth extends Entity implements EntityActionable{

	private int healAmount;
	private Sprite disp;
	
	public Hearth(int healing, int x, int y, Level level) {
		super(x, y, 16, 16, level);
		healAmount = healing;
		
		this.x =x;
		this.y=y;
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		
		disp = new SingleSprite(16, 0, 0, new Spritesheet(level.AM.getImage("hearth")));
	}

	@Override
	public void action(Player e) {
		AssetManager.getSingleton().stop("pickup");
		AssetManager.getSingleton().playSound("pickup",0);
		e.heal(healAmount);
		setToDestroy();
	}

	@Override
	public void update() {
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
	}

	@Override
	public void render(Rendering render) {
		render.renderEntity(xInScreen,yInScreen,disp);
		debug(render);
	}

}
