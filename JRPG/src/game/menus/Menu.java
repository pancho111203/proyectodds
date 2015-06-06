package game.menus;

import game.graphics.RenderingMenu;
import game.input.GameInput;
import auxiliar.AssetManager;

public abstract class Menu {

	protected GameInput gi;
	protected AssetManager AM;
	
	protected String changeTo;
	protected boolean exit=false;
	
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
	
	public boolean exit(){
		return exit;
	}

	public String change(){
		String ch = changeTo;
		changeTo = null;
		return ch;
	}
}
