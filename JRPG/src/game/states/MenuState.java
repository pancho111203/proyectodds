package game.states;

import game.GameStart;
import game.graphics.Rendering;
import game.graphics.Rendering;
import game.menus.CreditsMenu;
import game.menus.MainMenu;
import game.menus.Menu;

import java.awt.Graphics;
 
public class MenuState implements IState{

	private GameStart game;
	private Rendering render;
	
	private Menu curMenu;
	

	public MenuState(GameStart game, int w, int h){
		this.game = game;
		render = new Rendering(w,h);
		
		curMenu = new MainMenu();
	}
	
	@Override
	public void render() {
		curMenu.render(render);
	}

	@Override
	public void update() {
		curMenu.update();
		String ch = curMenu.change();
		if(ch!=null){
			if(ch.equals("level1")){
				game.change("game1", "init");
			}else if(ch.equals("credits")){
				curMenu = new CreditsMenu();
			} else if(ch.equals("mainmenu")){
				curMenu = new MainMenu();
			}
		}
		if(curMenu.exit())game.stop();
	}

	@Override
	public void onExit() {
	}

	@Override
	public void onEnter(String params) {
		curMenu = new MainMenu();
	}
	
	public Rendering getRender(){
		return render;
	}

	@Override
	public void renderGraphics(Graphics g) {
	}
	


}
