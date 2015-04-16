package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.input.Keyboard;

import java.awt.event.KeyEvent;

public class LevelState implements IState{

	@SuppressWarnings("unused")
	private StateMachine game;
	private RenderingLevel render;
	private Keyboard key;
	
	private int offsetX=0, offsetY=0;
	
	public LevelState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingLevel(w,h);
		render.randomLevel();
		key = Keyboard.getSingleton();
	}
	

	@Override
	public void update() {
		// TEST STUFF
		if(key.keyDown(KeyEvent.VK_W))offsetY--;
		if(key.keyDown(KeyEvent.VK_S))offsetY++;
		if(key.keyDown(KeyEvent.VK_A))offsetX--;
		if(key.keyDown(KeyEvent.VK_D))offsetX++;

		
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
