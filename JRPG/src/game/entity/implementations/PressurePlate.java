package game.entity.implementations;

import game.entity.Entity;
import game.entity.types.EntityActionable;
import game.events.EventGenerator;
import game.events.EventListener;
import game.graphics.RenderingLevel;
import game.level.Level;

import java.util.ArrayList;

public class PressurePlate extends Entity implements EntityActionable, EventGenerator{

	private ArrayList<EventListener> listeners = new ArrayList<EventListener>();

	
	public PressurePlate(int x, int y, int w, int h, Level level) {
		super(x, y, w, h, level);

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

	@Override
	public void action(Player e) {
		notifyObservers();
	}

	@Override
	public void update() {
	}

	@Override
	public void render(RenderingLevel render) {
		debug(render);
	}
	

}
