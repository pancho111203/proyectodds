package game.level.tiles;

import game.graphics.Animator;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.util.HashMap;
import java.util.Iterator;

public class LevelSprites { // crea los sprites(solo para el nivel, las entidades los crean ellas mismas)
	private HashMap<String,SingleSprite> sprites;
	private HashMap<String,Animator> anims;
	
	// separar sprites en background y foreground ????

	
	public LevelSprites(){
		sprites = new HashMap<String, SingleSprite>();
		anims = new HashMap<String, Animator>();
	}
	
	public Sprite createSprite(String name, int x, int y, Spritesheet sheet){
		SingleSprite aux = new SingleSprite(Level.TILESIZE,x,y,sheet);
		sprites.put(name, aux);
		return aux;
		
	}
	
	public Sprite createSprite(String name, int color){
		SingleSprite aux = new SingleSprite(Level.TILESIZE,color);
		sprites.put(name, aux);
		return aux;
	}
	
	public Sprite createAnim(String name, int x, int y, int n, Spritesheet sheet, int r){
		Animator aux = new Animator(Level.TILESIZE, x, y, n, sheet, r, false);
		anims.put(name, aux);
		return aux;
	}
	
	public Sprite getAnim(String name){
		return anims.get(name);
	}
	
	public Sprite getSprite(String name){
		return sprites.get(name);
	}
	
	public void updateAnims(){
		Iterator<Animator> it = anims.values().iterator();
		
		while(it.hasNext()){
			Animator a = it.next();
			a.update();
		}
	}
}
