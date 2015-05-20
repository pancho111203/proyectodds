package game.entity;

import game.graphics.RenderingLevel;
import game.level.Level;

import java.awt.Rectangle;

public abstract class Entity {
	protected int x,y;
	protected int xInScreen,yInScreen;
	//for collisions
	protected Rectangle spriteOffsets;
	//usamos rectangulo solo por comodidad, guardando el indice inicial en X y el final en Width (lo mismo para y y height)
	
	protected Rectangle collider;
	//TODO fallo en los bounds del collider, no se si es fallo del debug o del collider mismo, 
	//EL ERROR PARECE TENER LUGAR CUANDO LA CAMARA SE BLOQUE EN LOS BORDES DEL ESCENARIO
	
	
	protected final int WIDTH;
	protected final int HEIGHT;
	
	protected final int WIDTHINTILES;
	protected final int HEIGHTINTILES;
	
	public Level level;

	
	protected final int TS;
	
	public Entity(int x, int y, int w, int h, Level level){
		this.level = level;
		this.x = x;
		this.y = y;
		
		TS = Level.TILESIZE;
		
		
		WIDTHINTILES = w;
		HEIGHTINTILES = h;
		
		WIDTH = WIDTHINTILES*TS;
		HEIGHT = HEIGHTINTILES*TS;
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		spriteOffsets = new Rectangle(0,0,WIDTH,HEIGHT);
		
	}
	
	public abstract void update(); 
	public abstract void render(RenderingLevel render);
	
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
	
	public Rectangle getSpriteOffsets(){
		return spriteOffsets;
	}
	
	public Rectangle getCollider(){
		return collider;
	}
	
	public void collision(Entity e){
		if(!e.equals(this)){
			collide(e);
		}
	}
	public abstract void collide(Entity e); //metodo llamado cuando e collidea con this
}
