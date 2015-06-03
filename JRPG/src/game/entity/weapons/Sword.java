package game.entity.weapons;

import game.entity.Entity;

public class Sword implements Weapon{

	private Entity parent;
	private boolean active = false;
	public Sword(Entity ent){
		parent = ent;
	}
	public void update(){
		if(active){
			//System.out.println("atacando");
		}
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub

		active = true;
	}

	@Override
	public void stopAttack() {
		// TODO Auto-generated method stub
		active = false;
	}

}
