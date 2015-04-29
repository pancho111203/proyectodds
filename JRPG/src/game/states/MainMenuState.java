package game.states;

import java.io.File;

import game.StateMachine;
import game.graphics.Image;
import game.graphics.Rendering;
import game.graphics.RenderingMainMenu;
import game.graphics.Spritesheet;

public class MainMenuState implements IState{

	@SuppressWarnings("unused")
	private StateMachine game;
	private RenderingMainMenu render;
	
	private Image bg;
	private Image btn;
	private Image sel;
	
	//TODO Main Menu Methods
	public MainMenuState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingMainMenu(w,h);
		bg = new Image("/images/pilar_pequeno.png");
	}
	
	@Override
	public void render() {
		render.renderImage(0, 0, bg);
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
