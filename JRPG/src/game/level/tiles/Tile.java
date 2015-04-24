package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;

public interface Tile {

	public SingleSprite getSprite();
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render);
	
}
