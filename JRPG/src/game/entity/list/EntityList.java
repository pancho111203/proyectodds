package game.entity.list;

import game.entity.Entity;
import game.entity.implementations.NullEntity;
import game.entity.implementations.Player;
import game.graphics.RenderingLevel;
import game.level.Level;

import java.util.ArrayList;

public class EntityList {

	private ArrayList<Entity> ents;
	private Entity player;
	private Level level;
	
	public EntityList(Level level){
		ents = new ArrayList<Entity>();
		player = new NullEntity(level);
		this.level = level;
	}
	
	public void update(){
		
		for(int i=0;i<ents.size();i++){
			Entity cur = ents.get(i);
			
			if(cur.getEnabled())cur.update();
			
			if(cur.toBeDestroyed()){
				ents.remove(i);
			}
			
		}
		
		player.update();
	}
	
	public void render(RenderingLevel render){
		
		for(int i=0;i<ents.size();i++){
			Entity cur = ents.get(i);
			
			if(cur.getEnabled())cur.render(render);
			
		}
		
		player.render(render);
		
	}
	
	public Iterator getIterator(){
		return new EntityIterator(this);
	}
	
	public void addEntity(Entity e){
		ents.add(e);
	}
	public void addPlayer(Player e){
		player = e;
	}
	
	public Entity getEntity(int i){
		if(i==0)return player;
		return ents.get(i-1);
	}
	
	public int getSize(){

		return ents.size()+1; //contando al player
	}
	
	public void removeEntity(Entity e){
		ents.remove(e);
	}
	
	public void clearList(){
		ents.clear();
	}
}
