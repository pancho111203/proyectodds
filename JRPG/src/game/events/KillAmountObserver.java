package game.events;

import game.GameMaster;

import java.util.ArrayList;

public class KillAmountObserver implements EventListener, EventGenerator{
	private int killAmount;

	private ArrayList<EventListener> listeners = new ArrayList<EventListener>();
	
	
	public KillAmountObserver(int ka) {
		super();
		killAmount = ka;
		GameMaster.getSingleton().addListener(this);
	}
	@Override
	public void notifyMe() {
		if(GameMaster.getSingleton().getEnemyCounter()>=killAmount){
			notifyObservers();
		}
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
