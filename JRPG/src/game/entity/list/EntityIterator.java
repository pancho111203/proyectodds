package game.entity.list;

import game.entity.Entity;

public class EntityIterator implements Iterator{
	private int index;
	private EntityList list;
	
	public EntityIterator(EntityList e){
		index = 0;
		list = e;
	}
	
	public boolean hasNext() {
		return index < list.getSize();
	}

	public Entity next() {
		if(hasNext()){
			return list.getEntity(index++);
		}
		return null;
	}

}
