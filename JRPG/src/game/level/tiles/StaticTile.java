package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;

public class StaticTile implements Tile{
	private SingleSprite sprite;

	public StaticTile(SingleSprite sprite){ // cuando e level crea los tiles, le pasa el tamaño(tiene que ser cuadrado)

		this.sprite = sprite;
	}
	
	public SingleSprite getSprite(){
		return sprite;
	}
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render){ // (x,y) es la posicion con respecto a la pantalla 
		render.renderTile(x, y,xRest,yRest, this);
	}
}
