package game.entity.movestate;

import game.graphics.Sprite;

public interface IMove {
	public void update();
	public void onEnter(int dir, boolean still);
	public void changeDirection(int dir, boolean still);
	public int getMovX();
	public int getMovY();
	public void move(int movX, int movY);
	public Sprite getSprite();
}
