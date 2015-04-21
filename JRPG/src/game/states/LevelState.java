package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.Level;
import game.level.RandomLevel;

public class LevelState implements IState{

	@SuppressWarnings("unused")
	private StateMachine game;
	private RenderingLevel render;
	private Level curLevel;
	
	public LevelState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingLevel(w,h);
		curLevel = new RandomLevel(64,64); 
	}
	

	@Override
	public void update() {
		
		curLevel.update();
		
	}
	@Override
	public void render() {
		
		curLevel.render(render);
		
	}

	@Override
	public void onExit() {
		// TODO onExit in LevelState
	}

	@Override
	public void onEnter(String params) {
		// TODO onEnter in LevelState
	}

	@Override
	public Rendering getRender() {
		return render;
	}

}
