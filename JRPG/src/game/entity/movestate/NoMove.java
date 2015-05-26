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
	public void onEnter(int dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDirection(int dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMovX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMovY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(int movX, int movY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return currentSprite;
	}

}
