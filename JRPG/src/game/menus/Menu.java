package game.menus;

import game.AssetManager;
import game.graphics.RenderingMenu;
import game.input.GameInput;

public abstract class Menu {

	protected GameInput gi;
	protected AssetManager AM;
	
	protected String changeTo;
	
	public Menu() {
		changeTo=null;
		AM= AssetManager.getSingleton();
		AM.load("MainMenu");
		gi= GameInput.getSingleton();
	}
	
	public void update(){		
	}

	public void render(RenderingMenu render){
		 
	}
	

	public String change(){
		return changeTo;
	}
}
