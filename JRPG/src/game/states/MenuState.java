package game.states;

import game.GameStart;
import game.graphics.Rendering;
import game.graphics.RenderingMenu;
import game.menus.MainMenu;
import game.menus.Menu;
 
public class MenuState implements IState{

	private GameStart game;
	private RenderingMenu render;
	

	private Menu curMenu;
	

	public MenuState(GameStart game, int w, int h){
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
