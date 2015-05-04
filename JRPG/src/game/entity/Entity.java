package game.entity;

import game.graphics.RenderingLevel;

public abstract class Entity {
	protected int x,y;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	public abstract void update(); 
	public abstract void render(RenderingLevel render);
}
