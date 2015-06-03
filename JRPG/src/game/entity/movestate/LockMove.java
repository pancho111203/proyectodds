package game.entity.movestate;

import game.entity.SpriteContainer;
import game.graphics.Animator;
import game.graphics.Sprite;
//TODO
public class LockMove implements IMove{

	private SpriteContainer container;
	private Sprite currentSprite;
	private int movX, movY;
	
	public LockMove(SpriteContainer sc){
		
		container = sc;
		currentSprite = container.getSprite("8");
	}
	
	@Override
	public void update() {
		if(currentSprite instanceof Animator){
			((Animator)currentSprite).update();
		} 
	}

	@Override
	public void onEnter(int dir) {
		
		currentSprite = container.getSprite(dir+"");
		currentSprite.startAgain();
		
	}

	
	@Override
	public void changeDirection(int dir) { // esta clase solo permite cambio de sprite en la entrada del estado ("onEnter")
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
	public void move(int movX, int movY) {
		this.movX = movX;
		this.movY = movY;
	}

	@Override
	public Sprite getSprite() {
		return currentSprite;
	}

}
