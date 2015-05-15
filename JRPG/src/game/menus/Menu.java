package game.menus;

import game.AssetManager;
import game.graphics.RenderingMenu;
import game.input.Gamepad;
import game.input.Keyboard;

public abstract class Menu {

	protected Keyboard key;
	protected Gamepad pad;
	
	protected AssetManager AM;
	
	protected String changeTo;
	
	public Menu() {
		changeTo=null;
		pad = Gamepad.getSingleton();
		AM= AssetManager.getSingleton();
		AM.load("MainMenu");
		key = Keyboard.getSingleton();
	}
	
	public void update(){		
	}

	public void render(RenderingMenu render){
		
	}
	

	public String change(){
		return changeTo;
	}
}
