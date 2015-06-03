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
	public void addSprite(Sprite e);
	public void removeSprite(Sprite e);
	public Sprite getSprite(int i);
	public int getXOffset();
	public int getYOffset();
	public void setXOffset(int a);
	public void setYOffset(int a);
}
