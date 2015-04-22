package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.Sprite;

public class Tile {

//TODO limit sprites to tile length
//TODO funcion de animar el tile
	
	
	//TEST tiles
	public static Tile blanco = new Tile(Sprite.blanco);
	public static Tile verde = new Tile(Sprite.verde);
	public static Tile rojo = new Tile(Sprite.rojo);
	public static Tile negro = new Tile(Sprite.negro);
	//
	
	public Sprite sprite;

	public Tile(Sprite sprite){ // cuando e level crea los tiles, le pasa el tamaño(tiene que ser cuadrado)

		this.sprite = sprite;
	}
	
	
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render){ // (x,y) es la posicion con respecto a la pantalla 
		render.renderTile(x, y,xRest,yRest, this);
	}
}
