package game.level.tiles;

import game.graphics.RenderingLevel;
import game.graphics.Sprite;

public class Tile {
//TODO Tile class
//TODO limit sprites to tile length
//TODO funcion de animar el tile

	public final int SIZE; 
	public Sprite sprite;

	public Tile(int size){ // cuando e level crea los tiles, le pasa el tamaño(tiene que ser cuadrado)
		this.SIZE = size;
	}
	public void render(RenderingLevel render){
		
	}
}
