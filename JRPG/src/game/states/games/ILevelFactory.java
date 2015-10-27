package game.states.games;

import game.level.Level;

public interface ILevelFactory {
	public Level getLevel(String ident,int spX, int spY);
}
