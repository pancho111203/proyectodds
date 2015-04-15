package game.states;

import game.graphics.Rendering;

public interface IState {
	public void render();
	public void update(float elapsedTime);
	public void onExit();
	public void onEnter(String params);
	public Rendering getRender();
}
