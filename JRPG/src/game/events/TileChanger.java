package game.events;

import game.level.Level;

public class TileChanger implements EventListener{
	//este changer cambia un tile cuando el player ha matado a un numero de enemigos determinado
	private int xPos, yPos;
	private int tileId;
	private Level level;
	private EventGenerator eventGen;
	
	public TileChanger(EventGenerator eGen, int x, int y, int tileIdent, Level level) {
		tileId = tileIdent;
		xPos = x;
		yPos = y;
		this.level = level;
		eventGen = eGen;
		
		eventGen.addListener(this);
	}
	
	@Override
	public void notifyMe() {
		level.setTile(xPos, yPos, tileId);
	}

}
