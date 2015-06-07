package game.entity.implementations.enemies.zeus;

import game.entity.weapons.Weapon;

public class EnragePhase implements ZeusPhase{
	
	private int contTillThunder = 0, thunderInterval = 90;
	
	private Zeus zeus;
	public EnragePhase(Zeus zeus) {
		this.zeus = zeus;
	}
	@Override
	public void update() {
		contTillThunder++;
		if(contTillThunder>=thunderInterval){
			contTillThunder=0;
			Weapon thunder = zeus.weps.get("ThunderBall");
			thunder.attack(0);
			thunder.attack(1);
			thunder.attack(2);
			thunder.attack(3);
			thunder.attack(4);
			thunder.attack(5);
			thunder.attack(6);
			thunder.attack(7);
		}
		
	}
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDmgDealt() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDmgReceived() {
		// TODO Auto-generated method stub
		
	}

}
