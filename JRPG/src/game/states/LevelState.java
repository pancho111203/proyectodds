package game.states;

import game.AssetManager;
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
	
	public LevelState(GameStart game, int w, int h){
		this.game = game; 
		render = new RenderingLevel(w,h);
		AM = AssetManager.getSingleton(); 
		AM.load("FirstLevel");
		
		WIDTH = w;
		HEIGHT = h;
		
		changeLevel("FirstLevel");
		
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
	
	public void changeLevel(String newL){
		
		
		if(newL.equals("FirstLevel")){

			
			curLevel = new FirstLevel(80,0,AM.getImage("level3"),WIDTH,HEIGHT,this); 
			
		}
		
		
	}


}
