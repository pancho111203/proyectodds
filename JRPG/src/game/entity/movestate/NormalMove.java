package game.entity.movestate;

import game.entity.SpriteContainer;
import game.graphics.Animator;
import game.graphics.Sprite;

public class NormalMove implements IMove{
	protected SpriteContainer ns;
	protected Sprite currentSprite;
	private int movX, movY;
	private boolean still = true;
	private boolean updateWhenStill;
	
	
	public NormalMove(SpriteContainer normalState, boolean updateWhenStill) {
		this.updateWhenStill = updateWhenStill;
		ns = normalState;
		currentSprite = ns.getSprite("8");
	}

	@Override
	public void update() {
		if(still&&!updateWhenStill)return;
		if(currentSprite instanceof Animator){
			((Animator)currentSprite).update();
		} 
	}

	@Override
	public void onEnter(int dir, boolean still) {
		changeDirection(dir, still);
	}

	@Override
	public void changeDirection(int dir, boolean still) {
		currentSprite = ns.getSprite(dir+"");
		currentSprite.startAgain();
		this.still = still;
		
	}

	@Override
	public void move(int movX, int movY) {
		this.movX = movX;
		this.movY = movY;
	}

	@Override
	public int getMovX() {
		return movX;
	}

	@Override
	public int getMovY() {
		return movY;
	}

	@Override
	public Sprite getSprite() {
		return currentSprite;
	}
	
}
