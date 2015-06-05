package game.states;

import auxiliar.AssetManager;
import game.GameStart;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.FirstLevel;
import game.level.Level;

public class LevelState implements IState{

	
	private AssetManager AM;
	private GameStart game;
	private RenderingLevel render;
	private Level curLevel;
	private int WIDTH,HEIGHT;
	private LevelFactory fact;
	
	public LevelState(GameStart game, int w, int h){
		this.game = game; 
		render = new RenderingLevel(w,h);
		AM = AssetManager.getSingleton(); 
		AM.load("Level");
		
		WIDTH = w;
		HEIGHT = h;
		
		fact = new LevelFactory(WIDTH, HEIGHT, this);
		
		changeLevel("FirstLevel",FirstLevel.START_POS_X, FirstLevel.START_POS_Y);
		
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
		if(curLevel==null){
			changeLevel("FirstLevel",FirstLevel.START_POS_X, FirstLevel.START_POS_Y);
		}
	}

	@Override
	public Rendering getRender() {
		return render;
	}
	
	public void changeLevel(String newL, int spawnX, int spawnY){
		curLevel = fact.getLevel(newL,spawnX,spawnY);		
	}

	public void finish() {
		game.change("end", "");
		curLevel = null;
	}


}
