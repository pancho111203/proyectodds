package game;

import game.entity.Entity;
import game.entity.implementations.Enemy;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityWithStats;
import game.events.EventGenerator;
import game.events.EventListener;

import java.util.ArrayList;

public class GameMaster implements EventGenerator{
	
	private int enemyKilledCounter = 0;
	
	private static GameMaster instance;
	//usa observer design pattern para notificar a sus listeners el numero de kills que ha hecho el player
	//posiblemente pasar este funcionamiento a otra clase y dejar esta solo para metodos estaticos(performAttack, kill, eetc)
	private ArrayList<EventListener> listeners = new ArrayList<EventListener>();
	

	
	public static GameMaster getSingleton(){
		if(instance==null){
			instance = new GameMaster();
		}
		return instance;
	}
	
	private GameMaster(){
		
	}
	
	//faltan comprobaciones de que no sean aliados, etc (todavia no he implementado aliados)
	public void performAttack(DamagingEntity from, EntityWithStats to, Entity source){
		if(from.isActive()){
			int dmg = from.getDmg();
			to.receiveDmg(dmg, source);
			from.dealtDamage(dmg);
		}		
	}
	public void kill(Entity e){
		e.setToDestroy();
		if(e instanceof Enemy){
			enemyKilledCounter++;
			e.level.enemyDestroyed((Enemy)e);
			notifyObservers();
		}
	}
	
	public int getEnemyCounter(){
		return enemyKilledCounter;
	}
	
	public void resetEnemyCounter(){
		enemyKilledCounter = 0;
	}
	

	
	
	public void addListener(EventListener e){
		listeners.add(e);
	}
	public void removeListener(EventListener e){
		listeners.remove(e);
	}
	
	public void notifyObservers(){
		for(int i = 0; i< listeners.size(); i++){
			listeners.get(i).notifyMe();
		}
	}

}
