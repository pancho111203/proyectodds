package game.entity.weapons;

import game.entity.Entity;

public class Sword implements Weapon{

	public Entity parent;
	
	public Sword(Entity ent){
		parent = ent;
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
