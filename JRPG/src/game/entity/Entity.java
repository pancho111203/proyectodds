package game.entity;

import game.graphics.RenderingLevel;
import game.level.Level;

public abstract class Entity {
	protected int x,y;
	//for collisions
	protected int[] spriteOffsets;
	
	protected final int WIDTH;
	protected final int HEIGHT;
	
	protected final int WIDTHINTILES;
	protected final int HEIGHTINTILES;
	
	protected final int TS;
	
	public Entity(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		
		TS = Level.TILESIZE;
		
		
		WIDTHINTILES = w;
		HEIGHTINTILES = h;
		
		WIDTH = WIDTHINTILES*TS;
		HEIGHT = HEIGHTINTILES*TS;
		
		
		spriteOffsets = new int[4];
		
		spriteOffsets[0]= 0; //default values
		spriteOffsets[1]= WIDTH;
		spriteOffsets[2]= 0;
		spriteOffsets[3]= HEIGHT;
	}
	
	public abstract void update(); 
	public abstract void render(RenderingLevel render);
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int[] getSpriteOffsets(){
		return spriteOffsets;
	}
}
