package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.input.Keyboard;

public class LevelState implements IState{

	private StateMachine game;
	private RenderingLevel render;
	
	private int offsetX=0, offsetY=0;
	
	public LevelState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingLevel(w,h);
		
		render.randomLevel();
	}
	

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		offsetX+=Keyboard.getSingleton().horizontal;
		offsetY+=Keyboard.getSingleton().vertical;
		
	}
	@Override
	public void render() {
		
		
		render.renderTiles(offsetX,offsetY);
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnter(String params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rendering getRender() {
		// TODO Auto-generated method stub
		return render;
	}

}
