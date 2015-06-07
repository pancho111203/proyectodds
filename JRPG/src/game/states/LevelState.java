package game.states;

import game.GameStart;
import game.entity.modules.Module;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.FirstLevel;
import game.level.Level;
import game.level.ThirdLevel;

import java.util.ArrayList;

import auxiliar.AssetManager;

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
		
		//changeLevel("FirstLevel",FirstLevel.START_POS_X, FirstLevel.START_POS_Y);
		changeLevel("ThirdLevel",ThirdLevel.START_POS_X, ThirdLevel.START_POS_Y);

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
		if(curLevel!=null){
			ArrayList<Module> r = curLevel.getPlayerModules();
			curLevel = fact.getLevel(newL,spawnX,spawnY);		
			curLevel.loadPlayer(r);
		}else{
			curLevel = fact.getLevel(newL,spawnX,spawnY);	
		}
	}

	public void finish() {
		game.change("end", "");
		curLevel = null;
	}


}
