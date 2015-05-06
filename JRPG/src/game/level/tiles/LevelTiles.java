package game.level.tiles;

import game.graphics.Sprite;

import java.util.HashMap;

public class LevelTiles {
	private HashMap<String,Tile> tiles;
	private HashMap<Integer,String> hex; // mapa de pixel-hex > nombreTile
	
	public LevelTiles(){
		tiles = new HashMap<String, Tile>();
		hex = new HashMap<Integer, String>();
	}
	
	public void addTile(String name, Sprite spr){ 
		Tile t = new Tile(spr);
		tiles.put(name, t);
	}
	
	public void addTile(String name, Sprite spr, int s1, int s2, int s3, int s4){ 
		Tile t = new Tile(spr,s1,s2,s3,s4);
		tiles.put(name, t);
	}
	
	public void addHex(int h, String name){ // este lo usamos si vamos a añadir un tile creado anteriormente
		hex.put(h, name);
	}
	
	public Tile getTile(int h){ // con el valor hex devuelve el Tile
		
		
		return tiles.get(hex.get(h));
	}
}
