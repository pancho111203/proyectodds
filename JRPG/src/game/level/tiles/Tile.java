package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.Sprite;

public interface Tile {

	public Sprite getSprite();
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render);
	
}
