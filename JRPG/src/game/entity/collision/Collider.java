package game.entity.collision;

import game.entity.Entity;
import game.entity.list.EntityIterator;
import game.entity.list.EntityList;
import game.level.Level;

import java.awt.Rectangle;

public class Collider extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Entity owner;
	private Rectangle ownerRect;
	private EntityList el;
	
	public Collider(int x, int y, int w,int h, Entity e) {
		super(x, y, w*Level.TILESIZE, h*Level.TILESIZE);
		ownerRect = e.getCollider();
		owner = e;
		el = e.level.getEntityList();
		
	}
	
	public void checkCollisions(){
		EntityIterator iterator = (EntityIterator) el.getIterator();
		ownerRect = owner.getCollider();
		while(iterator.hasNext()){
			Entity current = iterator.next();
			if(ownerRect.intersects(current.getCollider())){
				current.collide(owner);
			}
		
		}
	}

}
