package game.states.games;

import game.states.IState;

public interface IGameState extends IState{
	public void died();
	public void win();
	public void changeLevel(String newL, int spawnX, int spawnY);
}
