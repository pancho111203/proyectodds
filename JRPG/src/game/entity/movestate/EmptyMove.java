package game.entity.movestate;

import game.graphics.Sprite;

public class EmptyMove implements IMove{

	@Override
	public void update() {
	}

	@Override
	public void onEnter(int dir, boolean st) {
	}

	@Override
	public void changeDirection(int dir, boolean st) {
	}

	@Override
	public void move(int movX, int movY) {
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
	public Sprite getSprite() {
		return null;
	}
	
}
