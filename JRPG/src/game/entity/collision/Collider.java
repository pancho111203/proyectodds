package game.entity.collision;

import game.entity.Entity;
import game.entity.list.EntityIterator;
import game.entity.list.EntityList;

import java.awt.Rectangle;

public class Collider{
	
	private Entity owner;
	private Rectangle ownerRect;
	private EntityList el;
	
	public Collider(Entity e) { // constructor que toma un Entity y usa su collider
		ownerRect = e.getCollider();
		owner = e;
		el = e.level.getEntityList();
		
	}
	public Collider(Entity e, Rectangle r) { // constructor que toma el rectangulo directamente, y un entity al que mandar el evento
		ownerRect = r;
		owner = e;
		el = e.level.getEntityList();
		
	}
	public Rectangle getRect(){
		return ownerRect;
	}
	
	
	//TODO toca mejorar muchisimo las comunicaciones, ahora mismo en cada clase que tenga el metodo collider hay que hacer varios switches con cases para ver que esta pasando
	//ademas no se puede tener una clase arma que sea usada por el player y por el enemy pq los msjes son iguales.. no se puede saber quien te esta atacando
	public void checkCollisions(String args){
		EntityIterator iterator = (EntityIterator) el.getIterator();
		while(iterator.hasNext()){
			Entity current = iterator.next();
			if(ownerRect.intersects(current.getCollider())){
				current.collision(owner,args);
			}
		
		}
	}

}
