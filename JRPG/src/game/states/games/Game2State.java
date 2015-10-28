package game.states.games;

import game.AssetManager;
import game.GameStart;
import game.entity.modules.Module;
import game.graphics.Rendering;
import game.graphics.Rendering;
import game.input.GameInput;
import game.level.Level;
import game.level.game1.FirstLevel;
import game.states.IState;
import game.states.pause.GamePause;

import java.awt.Graphics;
import java.util.ArrayList;

public class Game2State implements IState, IGameState{

	
	private AssetManager AM;
	public GameStart game;
	private Rendering render;
	private Level curLevel;
	private int WIDTH,HEIGHT;
	private ILevelFactory fact;
	
	public Game2State(GameStart game, int w, int h){
		this.game = game; 
		AM = AssetManager.getSingleton(); 
		AM.load("Level");

		render = new Rendering(w,h);
		
		WIDTH = w;
		HEIGHT = h;
		
		fact = new Game2Factory(WIDTH, HEIGHT, this);

	}	

	private void startFirstLevel(){
		AssetManager.getSingleton().stop("music");
		AssetManager.getSingleton().getSounds().setVol("music",-5);
		AssetManager.getSingleton().playSound("music");
		
		changeLevel("FirstLevel",FirstLevel.START_POS_X, FirstLevel.START_POS_Y);
	}
	
	@Override
	public void update() {
		if(GameInput.getSingleton().inputPressed(GameInput.getSingleton().PAUSE)){
			game.pause(new GamePause());
		}
		curLevel.update();		
	}
	
	@Override
	public void render() {
		
		curLevel.render(render);
		
	}

	@Override
	public void onExit() {
		curLevel = null;
	}

	@Override
	public void onEnter(String params) {
		if(curLevel==null){
			 startFirstLevel();
		}
	}

	public void restart(String params){
		onExit();
		onEnter(params);
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

	public void died() {
		restart("");
		
	}

	@Override
	public void renderGraphics(Graphics g) {
	}

	public void win() {
		
	}


}
