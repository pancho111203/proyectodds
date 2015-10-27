package game.entity;

import game.GameStart;
import game.graphics.Rendering;
import game.graphics.Rendering;
import game.level.Level;

import java.awt.Rectangle;

public abstract class Entity {
	protected int x,y;
	protected int xInScreen,yInScreen;
	//for collisions
	protected Rectangle spriteOffsets;
	//usamos rectangulo solo por comodidad, guardando el indice inicial en X y el final en Width (lo mismo para y y height)
	
	protected Rectangle colliderOffsets;
	protected Rectangle collider;
	protected boolean enabled = true;
	
	protected final int WIDTH;
	protected final int HEIGHT;
	
	
	public Level level;

	
	protected final int TS;
	private boolean toBeDestroyed = false;
	
	
	public Entity(int x, int y, int w, int h, Level level){
		this.level = level;
		this.x = x;
		this.y = y;
		
		TS = Level.TILESIZE;
		
		
		
		WIDTH = w;
		HEIGHT = h;
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		colliderOffsets = new Rectangle(0,0,WIDTH,HEIGHT);
		spriteOffsets = new Rectangle(0,0,WIDTH,HEIGHT);
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
		
	}
	
	
	public void addCustomCollider(Rectangle newColl){
		colliderOffsets = newColl;
		collider.setSize((int)(colliderOffsets.getWidth()-colliderOffsets.getX()), (int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
	}
	
	public void setEnabled(boolean e){
		enabled = e;
	}
	public boolean getEnabled(){
		return enabled;
	}
	
	public abstract void update(); 
	public abstract void render(Rendering render);
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getXScreen() {
		return xInScreen;
	}
	public int getYScreen() {
		return yInScreen;
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	public Rectangle getSpriteOffsets(){
		return spriteOffsets;
	}
	public Rectangle getColliderOffsets(){
		return colliderOffsets;
	}
	
	public Rectangle getCollider(){
		return collider;
	}

	
	public void debug(Rendering render){
		if(!GameStart.getDebug())return;
		
		int tileColliderColor = 0xff0000ff;
		int entityColliderColor = 0xffff0000;
		
		//Tile Collider
		render.renderRect((int)(x-level.getXPosScreen()+spriteOffsets.getX()), (int)(y-level.getYPosScreen()+spriteOffsets.getY()), (int)(spriteOffsets.getWidth()-spriteOffsets.getX()), (int)(spriteOffsets.getHeight()-spriteOffsets.getY()), tileColliderColor);

		
		//Entity Collider
		render.renderRect((int)(collider.getX()-level.getXPosScreen()), (int)(collider.getY()-level.getYPosScreen()), (int)(collider.getWidth()), (int)(collider.getHeight()), entityColliderColor);

	}
	
	public boolean toBeDestroyed(){
		return toBeDestroyed ;
	}
	
	public void setToDestroy(){
		toBeDestroyed = true;
	}

}
