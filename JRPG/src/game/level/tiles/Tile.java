package game.level.tiles;

import game.graphics.Rendering;
import game.graphics.SingleSprite;
import game.graphics.Sprite;

public class Tile {
	
	private Sprite sprite;
	
	private int subStates[];	// 0 normal, 1 solid, 2 kill, 3 agua, 9 void

	public Tile(Sprite sprite){ // inicializa el tile con estado normal (0)
		subStates = new int[4];
		
		subStates[0] = 0;
		subStates[1] = 0;
		subStates[2] = 0;
		subStates[3] = 0;
		
		this.sprite = sprite;
	}
	
	public Tile(Sprite sprite, int st){ // inicializa el tile con un estado(igual para todos los subcuadrados)
		subStates = new int[4];
		
		subStates[0] = st;
		subStates[1] = st;
		subStates[2] = st;
		subStates[3] = st;
		
		this.sprite = sprite;
	}
	
	public Tile(Sprite sprite, int st1, int st2, int st3, int st4){
		// inicializa el tile con un estado diferente para cada subcuadrado
		subStates = new int[4];
		
		subStates[0] = st1;
		subStates[1] = st2;
		subStates[2] = st3;
		subStates[3] = st4;
		
		this.sprite = sprite;
	}
	
	public SingleSprite getSprite(){
		return sprite.getActual();
	}
	
	public void render(int x, int y, int xRest, int yRest, Rendering render){
		render.renderTile(x, y,xRest,yRest, this);
	}
	
	public int getState(int i){
		return subStates[i];
	}
	
	public void setState(int i, int x){
		subStates[i] = x;
	}
	public void setAllStates(int x){
		subStates[0] = x;
		subStates[1] = x;
		subStates[2] = x;
		subStates[3] = x;
	}
	
	public Tile clone(){
		return new Tile(sprite, subStates[0], subStates[1], subStates[2], subStates[3]);
	}
	
	public boolean isAnyState(int x){
		if(subStates[0] == x || subStates[1] == x || subStates[2] == x || subStates[3] == x){
			return true;
		}
		return false;
	}
	
}