package game.entity;

import game.graphics.RenderingLevel;

import java.util.ArrayList;

public class EntityList {

	private ArrayList<Entity> ents;
	
	public EntityList(){
		ents = new ArrayList<Entity>();
	}
	
	public void update(){
		
		for(int i=0;i<ents.size();i++){
			
			((Entity)ents.get(i)).update();
			
		}
		
	}
	
	public void render(RenderingLevel render){
		
		for(int i=0;i<ents.size();i++){
			
			((Entity)ents.get(i)).render(render);
			
		}
		
	}
	public void addEntity(Entity e){
		ents.add(e);
	}
	
	public void removeEntity(Entity e){
		ents.remove(e);
	}
}
