package game.entity;

import game.entity.weapons.Weapon;

public interface AtackingEntity {
	public void attack();
	public void setWeapon(Weapon weapon);
}
