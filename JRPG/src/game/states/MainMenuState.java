package game.states;

import game.StateMachine;
import game.graphics.Rendering;
import game.graphics.RenderingMainMenu;

public class MainMenuState implements IState{

	@SuppressWarnings("unused")
	private StateMachine game;
	private RenderingMainMenu render;
	//TODO Main Menu Methods
	public MainMenuState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingMainMenu(w,h);
	}
	
	@Override
	public void render() {
		
	}

	@Override
	public void update() {
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void onEnter(String params) {
		
	}
	public Rendering getRender(){
		return render;
	}
	
}
