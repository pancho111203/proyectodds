package game.graphics;

public interface Sprite {
	public SingleSprite getActual();
	public int getHeight();
	public int getWidth();
	public int getX();
	public int getY();
	public void FlipAll();
	public void startAgain();
	public SingleSprite getFirst();
}
