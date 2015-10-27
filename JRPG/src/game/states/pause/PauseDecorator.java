package game.states.pause;

import game.GameStart;
import game.graphics.Rendering;
import game.input.GameInput;
import game.states.IState;

import java.awt.Graphics;

public class PauseDecorator implements IState{

	private GameStart game;
	private IState state;
	private PausedState pausedState;
	
	public PauseDecorator(GameStart g, IState prev, PausedState pau) {
		state = prev;
		game = g;
		pausedState = pau;
	}
	
	@Override
	public void render() {
		state.render();
		pausedState.render(state.getRender());
	}

	@Override
	public void update() {
		pausedState.update();
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

	@Override
	public void renderGraphics(Graphics g) {	
		pausedState.renderGraphics(g);
	}
	
	
}
