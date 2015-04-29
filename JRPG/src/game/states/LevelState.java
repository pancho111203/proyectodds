package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.FirstLevel;
import game.level.Level;

public class LevelState implements IState{

	@SuppressWarnings("unused")
	private StateMachine game;
	private RenderingLevel render;
	private Level curLevel;
	
	public LevelState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingLevel(w,h);
		curLevel = new FirstLevel(0,0,"/images/level.png",w,h); 
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
