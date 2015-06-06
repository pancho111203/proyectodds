package game.menus;

import game.graphics.Image;
import game.graphics.RenderingMenu;
import auxiliar.AssetManager;

public class MainMenu extends Menu {
	
	private Image bg;
	private Image btn1,btn2,btn3;
	private int select=0;

	public MainMenu(){
		bg =new Image(AM.getImage("bg"));
		btn1 = new Image(AM.getImage("btnJugar"));
		btn2 = new Image(AM.getImage("btnCredts"));
		btn3 = new Image(AM.getImage("btnSalir"));
	}
	
	public void update(){
		
		if(gi.inputPressed(gi.UP)){
			select = (select==0) ? 2 : select-1;
		}
		if(gi.inputPressed(gi.DOWN)){
			select = (select==2) ? 0 : select+1;
		}
		if(gi.inputPressed(gi.ACTION)){
			if(select==0){
			AssetManager.getSingleton().stop("music");
			AssetManager.getSingleton().playSound("music");
			changeTo = "level1";
			}else if(select==1){
				changeTo="credits";
			}else{
				exit=true;
			}
		}
	}
	
	public void render(RenderingMenu render){
		render.renderImage(0, 0, bg);
		if(select==0) render.renderImageColored(105, 130, btn1, 0xEEEEEE);
		else render.renderImage(105, 130, btn1);
		if(select==1) render.renderImageColored(105, 165, btn2, 0xEEEEEE);
		else render.renderImage(105, 165, btn2);
		if(select==2) render.renderImageColored(105, 200, btn3, 0xEEEEEE);
		else render.renderImage(105, 200, btn3);
	//	render.renderImage(sel.getX(), sel.getY(), sel);	
	}

}
