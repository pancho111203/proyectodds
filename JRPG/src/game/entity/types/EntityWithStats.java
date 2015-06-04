package game.entity.types;

import game.entity.Entity;
import game.entity.Stats;

public interface EntityWithStats {
	public Stats stats=null;
	public void receiveDmg(int dmg, Entity e);
	public Stats getStats();
}
