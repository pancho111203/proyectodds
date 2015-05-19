package game.entity;

import game.graphics.Sprite;

import java.util.HashMap;

public class SpriteContainer {
	//esta clase sirve como contenedora de sprites, util para mantener todos los sprites de una entidad en un estado determinado juntos
	// (p ej. player nadando, hacen falta al menos 8 sprites, uno para cada direccion y uno en estado estatico)
	private HashMap<String,Sprite> sprites;
	public SpriteContainer(){
		sprites = new HashMap<String,Sprite>();
	}
	
	public void addSprite(String name, Sprite sprite){
		sprites.put(name, sprite);
	}
	
	public Sprite getSprite(String name){
		return sprites.get(name);
	}
}
