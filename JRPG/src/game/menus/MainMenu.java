package game.menus;

import java.awt.event.KeyEvent;

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
		
		if(key.keyPressed(KeyEvent.VK_W)||key.keyPressed(KeyEvent.VK_UP)||pad.padChanged(pad.Lup)){
			sely = (sely > 30) ? (sely-25) : 70;
		}
		if(key.keyPressed(KeyEvent.VK_S)||key.keyPressed(KeyEvent.VK_DOWN)||pad.padChanged(pad.Ldown)){
			sely = (sely < 60) ? (sely+25) : 20;
		}
		sel.setY(sely);
		if(key.keyPressed(KeyEvent.VK_ENTER)||pad.getButtonValue(pad.START)||pad.getButtonValue(pad.CROS)){
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
