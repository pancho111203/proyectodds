package game.entity.movestate;

import game.entity.SpriteContainer;
import game.graphics.Animator;
import game.graphics.Sprite;

public class NormalMove implements IMove{
	private SpriteContainer ns;
	private Sprite currentSprite;
	private int movX, movY;
	public NormalMove(SpriteContainer normalState) {
		ns = normalState;
		currentSprite = ns.getSprite("0");
	}

	@Override
	public void update() {
		if(currentSprite instanceof Animator){
			((Animator)currentSprite).update();
		} 
	}

	@Override
	public void onEnter(int dir) {
		changeDirection(dir);
	}

	@Override
	public void changeDirection(int dir) {
		currentSprite = ns.getSprite(dir+"");
		currentSprite.startAgain();
	}

	@Override
	public void move(int movX, int movY) {
		this.movX = movX;
		this.movY = movY;
	}

	@Override
	public int getMovX() {
		// TODO Auto-generated method stub
		return movX;
	}

	@Override
	public int getMovY() {
		// TODO Auto-generated method stub
		return movY;
	}

	@Override
	public Sprite getSprite() {
		return currentSprite;
	}
	
}
