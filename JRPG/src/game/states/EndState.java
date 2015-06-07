package game.states;

import game.GameStart;
import game.graphics.Rendering;
import game.graphics.RenderingMenu;
import game.menus.CreditsMenu;
import game.menus.MainMenu;
import game.menus.Menu;
 
public class EndState implements IState{

	private GameStart game;
	private RenderingMenu render;
	

	private Menu curMenu;
	

	public EndState(GameStart game, int w, int h){
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
		String ch = curMenu.change();
		if(ch!=null){
			if(ch.equals("level1")){
				game.change("level1", "init");
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

}