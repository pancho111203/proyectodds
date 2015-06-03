package game.entity.collision;

import game.entity.Entity;
import game.entity.list.EntityIterator;
import game.entity.list.EntityList;
import game.level.Level;

import java.awt.Rectangle;

public class Collider{
	
	private OwnsCollider owner;
	private Rectangle ownerRect;
	private EntityList el;
	

	public Collider(OwnsCollider e, Rectangle r, Level level) { 
		ownerRect = r;
		owner = e;
		el = level.getEntityList();
		
	}
	public Rectangle getRect(){
		return ownerRect;
	}
	
	
	public void checkCollisions(){
		EntityIterator iterator = (EntityIterator) el.getIterator();
		while(iterator.hasNext()){
			Entity current = iterator.next();
			if(ownerRect.intersects(current.getCollider())){
				owner.collide(current);
			}
		
		}
	}

}
