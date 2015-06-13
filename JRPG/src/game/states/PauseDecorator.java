package game.states;

import game.GameStart;
import game.graphics.Rendering;
import game.input.GameInput;

public class PauseDecorator implements IState{

	private GameStart game;
	private IState state;
	
	public PauseDecorator(GameStart g, IState prev) {
		state = prev;
		game = g;
	}
	
	@Override
	public void render() {
		state.render();
	}

	@Override
	public void update() {
		if(GameInput.getSingleton().inputPressed(GameInput.getSingleton().PAUSE)){
			game.unpause();
		}
	}

	@Override
	public void onExit() {
		state.onExit();
	}

	@Override
	public void onEnter(String params) {
		state.onEnter(params);
	}

	@Override
	public Rendering getRender() {
		return state.getRender();
	}

	public IState getPrevState() {
		return state;
	}
	
}
