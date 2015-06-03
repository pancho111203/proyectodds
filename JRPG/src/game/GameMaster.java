package game;

import game.entity.Entity;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityWithStats;

public class GameMaster {
	//TODO comprobaciones de que no sean aliados, etc
	public static void performAttack(DamagingEntity from, EntityWithStats to, Entity source){
		int dmg = from.getDmg();
		to.receiveDmg(dmg, source);
		from.dealtDamage(dmg);
	}
}
