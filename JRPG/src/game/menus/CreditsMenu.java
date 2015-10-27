package game.menus;

import game.graphics.Image;
import game.graphics.Rendering;

public class CreditsMenu extends Menu{

	private Image bg;
	
	public CreditsMenu() {
		bg = new Image(AM.getImage("creditos"));
	}

	public void update(){
		if(gi.inputPressed(gi.ACTION)){
			changeTo = "mainmenu";
		}
	}
	
	public void render(Rendering render){
		render.renderImage(0, 0, bg);
	}
	
}
