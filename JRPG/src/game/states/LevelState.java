package game.states;

import game.AssetManager;
import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.Level;

public class LevelState implements IState{

	@SuppressWarnings("unused")
	private AssetManager AM;
	private StateMachine game;
	private RenderingLevel render;
	private Level curLevel;
	
	public LevelState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingLevel(w,h);
		
		
		/*AM = AssetManager.getSingleton();
		AM.load("FirstLevel");
		curLevel = new FirstLevel(0,0,AM.getImage("level"),w,h); */
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
		
	}

	@Override
	public void onEnter(String params) {
		
	}

	@Override
	public Rendering getRender() {
		return render;
	}

}
