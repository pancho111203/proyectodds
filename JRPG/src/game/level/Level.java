package game.level;

import game.entity.EntityList;
import game.graphics.RenderingLevel;

public abstract class Level {
	private EntityList entList;
	
	protected int width, height;
	protected int[] tiles;
	
	public Level(){
		entList = new EntityList();
	}
	public void update(){
		entList.update();
	}
	public void render(RenderingLevel render){
		entList.render(render);
		
	}
	public abstract void loadLevel();
}