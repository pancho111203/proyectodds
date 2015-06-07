package game.entity.implementations.enemies.zeus;

import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.entity.movement.NoMovement;
import game.entity.weapons.ammo.ThunderBall;
import game.entity.weapons.ammo.ThunderBallGreen;

import java.util.ArrayList;


public class MiddlePhase implements ZeusPhase{

	private ArrayList<ThunderBall> topZoneMines;
	private Zeus zeus;
	private int cont = 0, timeTillSpawnFollower = 60;
	private ArrayList<ThunderBallGreen> followers;
	public MiddlePhase(Zeus zeus) {
		this.zeus = zeus;
		topZoneMines = new ArrayList<ThunderBall>();
		followers = new ArrayList<ThunderBallGreen>();
	}
	
	@Override
	public void update() {
		cont++;
		if(cont>=timeTillSpawnFollower){
			cont=0;
			Movement mov = new EasyPFMovment(zeus.level, zeus.level.getPlayer());
			ThunderBallGreen current = new ThunderBallGreen(zeus.getX()+zeus.getWidth()/2,zeus.getY()+zeus.getHeight()/2, zeus.level, mov, 15, true);
			mov.initializeEntity(current);
			followers.add(current);
			zeus.level.addEntityToList(current);
		}
		
		
		for(int i = 0;i<followers.size();i++){
			followers.get(i).update();
		}
		for(int i = 0;i<topZoneMines.size();i++){
			topZoneMines.get(i).update();
		}
	}

	
	@Override
	public void onEnter() {
		
		for(int y = 62; y<135;y+=32 ){
			for(int x = 49; x< 370; x+=32){
				ThunderBall current = new ThunderBall(x, y, zeus.level, new NoMovement(zeus.level), 10, false);
				topZoneMines.add(current);
				zeus.level.addEntityToList(current);
			}
		}
		
		
		
	}
	@Override
	public void onExit() {
		for(int i = 0;i<topZoneMines.size();i++){
			topZoneMines.get(i).setToDestroy();
		}
		for(int i = 0;i<followers.size();i++){
			followers.get(i).setToDestroy();
		}
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