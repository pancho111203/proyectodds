package game.events;


public interface EventGenerator {

	public void addListener(EventListener e);
	public void removeListener(EventListener e);
	
	public void notifyObservers();
}
