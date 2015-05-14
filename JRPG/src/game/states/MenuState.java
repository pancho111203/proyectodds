package game.states;

import game.AssetManager;
import game.StateMachine;
import game.graphics.Image;
import game.graphics.Rendering;
import game.graphics.RenderingMenu;
import game.input.Gamepad;
import game.input.Keyboard;
import game.level.Level;
import game.menus.MainMenu;
import game.menus.Menu;

import java.awt.event.KeyEvent;

public class MenuState implements IState{

	private StateMachine game;
	private RenderingMenu render;
	

	private Menu curMenu;
	

	public MenuState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingMenu(w,h);
		
		curMenu = new MainMenu();
	}
	
	@Override
	public void render() {
		curMenu.render(render);
	}

	@Override
	public void update() {
		curMenu.update();
		if(curMenu.change()!= null){
			game.change(curMenu.change(), "init");
		}
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
