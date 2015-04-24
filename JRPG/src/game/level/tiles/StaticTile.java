package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.Sprite;

public class StaticTile implements Tile{
	private Sprite sprite;

	public StaticTile(Sprite sprite){ // cuando e level crea los tiles, le pasa el tamaño(tiene que ser cuadrado)

		this.sprite = sprite;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render){ // (x,y) es la posicion con respecto a la pantalla 
		render.renderTile(x, y,xRest,yRest, this);
	}
}
