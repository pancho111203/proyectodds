package game.events;

import game.GameMaster;
import game.level.Level;

public class TileChanger implements EventListener{
	//este changer cambia un tile cuando el player ha matado a un numero de enemigos determinado
	private int enemyKillsNeeded, xPos, yPos;
	private int tileId;
	private Level level;
	
	public TileChanger(int x, int y, int enemyKills, int tileIdent, Level level) {
		tileId = tileIdent;
		xPos = x;
		yPos = y;
		enemyKillsNeeded = enemyKills;
		this.level = level;
		
		GameMaster.getSingleton().addListener(this);
	}
	
	@Override
	public void notifyMe() {
		if(GameMaster.getSingleton().getEnemyCounter()>=enemyKillsNeeded){
			level.setTile(xPos, yPos, tileId);
		}
	}

}
