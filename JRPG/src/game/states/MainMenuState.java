package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingMainMenu;

public class MainMenuState implements IState{

	private StateMachine game;
	private RenderingMainMenu render;
	
	public MainMenuState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingMainMenu(w,h);
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnter(String params) {
		// TODO Auto-generated method stub
		
	}
	public Rendering getRender(){
		return render;
	}
	
}
