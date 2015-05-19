package game.entity.movestate;

import game.graphics.Sprite;

public interface IMove {
	public void update();
	public void onEnter(int dir);
	public void changeDirection(int dir);
	public int getMovX();
	public int getMovY();
	public void move(int movX, int movY);
	public Sprite getSprite();
}
