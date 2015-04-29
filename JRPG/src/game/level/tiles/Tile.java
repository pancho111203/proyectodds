package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Sprite;

public class Tile {
	
	private Sprite sprite;
	

	public Tile(Sprite sprite){

		this.sprite = sprite;
	}
	
	public SingleSprite getSprite(){
		return sprite.getActual();
	}
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render){
		render.renderTile(x, y,xRest,yRest, this);
	}
	
}
