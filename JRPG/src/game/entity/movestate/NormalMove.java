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
		currentSprite = ns.getSprite("8");
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
		
		if(dir==8){
			currentSprite = currentSprite.getFirst();
		}else{
			currentSprite = ns.getSprite(dir+"");
			currentSprite.startAgain();
		}
		

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
