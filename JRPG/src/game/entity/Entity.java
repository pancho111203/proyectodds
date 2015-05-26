package game.entity;

import game.GameStart;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
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
		
		colliderOffsets = new Rectangle(0,0,WIDTH,HEIGHT);
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
	
	public void collision(Entity e){
		if(!e.equals(this)){
			collide(e);
		}
	}
	public abstract void collide(Entity e); //metodo llamado cuando e collidea con this
	
	public void debug(Rendering render){
		if(!GameStart.getDebug())return;
		
		int tileColliderColor = 0xff0000ff;
		int entityColliderColor = 0xffff0000;
		
		//Tile Collider
		render.renderRect((int)(x-level.getXPosScreen()+spriteOffsets.getX()), (int)(y-level.getYPosScreen()+spriteOffsets.getY()), (int)(spriteOffsets.getWidth()-spriteOffsets.getX()), (int)(spriteOffsets.getHeight()-spriteOffsets.getY()), tileColliderColor);

		
		//Entity Collider
		render.renderRect((int)(collider.getX()-level.getXPosScreen()), (int)(collider.getY()-level.getYPosScreen()), (int)(collider.getWidth()), (int)(collider.getHeight()), entityColliderColor);

	}
	
	protected class Stats{
		private int HP,delta=0;
		
		public Stats(){
			HP=0;
		}
		
		public void setHP(int hp){
			HP=hp;
		}
		
		public int getHP(){
			return HP;
		}
		
		public Boolean isAlive(){
			return HP>0;
		}
		
		public void hit(int damage){
			if(HP-damage>=0)HP-=damage;
			else{
				HP = 0;
			}
		}
	}
}
