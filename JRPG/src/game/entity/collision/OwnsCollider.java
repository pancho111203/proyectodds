package game.entity.collision;

import game.entity.Entity;

public interface OwnsCollider {
	public void collide(Entity e);
	public boolean isPaused();
}
