package game.menus;

import game.graphics.Image;
import game.graphics.RenderingMenu;

public class MainMenu extends Menu {
	
	private Image bg;
	private Image btn;
	private Image sel;

	public MainMenu(){
		bg =new Image(AM.getImage("bg"));
		btn = new Image(AM.getImage("botonsito"));
		sel = new Image(AM.getImage("selectorp"));
		sel.setX(265);
		sel.setY(20);
	}
	
	public void update(){
		int sely=sel.getY();
		
		if(gi.inputPressed(gi.UP)){
			sely = (sely > 30) ? (sely-25) : 70;
		}
		if(gi.inputPressed(gi.DOWN)){
			sely = (sely < 60) ? (sely+25) : 20;
		}
		sel.setY(sely);
		if(gi.inputPressed(gi.PAUSE)||gi.inputPressed(gi.ATACK)){
			changeTo = "level1";
		}
	}
	
	public void render(RenderingMenu render){
		render.renderImage(0, 0, bg);
		render.renderImage(200, 30, btn);
		render.renderImage(200, 55, btn);
		render.renderImage(200, 80, btn);
		render.renderImage(sel.getX(), sel.getY(), sel);	
	}

}
