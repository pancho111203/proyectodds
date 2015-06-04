package game.entity.movestate;

import game.graphics.Animator;
import game.graphics.Sprite;

public class NoMove implements IMove{
	private Sprite currentSprite;
	
	public NoMove(Sprite sprite) {
		this.currentSprite = sprite;
	}
	
	@Override
	public void update() {
		if(currentSprite instanceof Animator){
			((Animator)currentSprite).update();
		} 
	}

	@Override
	public void onEnter(int dir, boolean d) {
		
	}

	@Override
	public void changeDirection(int dir, boolean s) {
	}

	@Override
	public int getMovX() {
		return 0;
	}

	@Override
	public int getMovY() {
		return 0;
	}

	@Override
	public void move(int movX, int movY) {
		
	}

	@Override
	public Sprite getSprite() {
		return currentSprite;
	}

}
