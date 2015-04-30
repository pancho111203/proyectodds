package game.states;

import game.StateMachine;
import game.graphics.Image;
import game.graphics.Rendering;
import game.graphics.RenderingMainMenu;
import game.input.Keyboard;

import java.awt.event.KeyEvent;

public class MainMenuState implements IState{

	private StateMachine game;
	private RenderingMainMenu render;
	private Keyboard key;
	
	//AssetManager AM= AssetManager.getSingleton();
	
	private Image bg;
	private Image btn;
	private Image sel;
	int selx,sely;
	
	//TODO Main Menu Methods
	public MainMenuState(StateMachine game, int w, int h){
		this.game = game;
		render = new RenderingMainMenu(w,h);
		key = Keyboard.getSingleton();
		bg =new Image("/images/bg.png");
		btn = new Image("/images/botonsito.png");
		sel = new Image("/images/selectorp.png");
		selx=265;
		sely=20;
	}
	
	@Override
	public void render() {
		render.renderImage(0, 0, bg);
		render.renderImage(200, 30, btn);
		render.renderImage(200, 55, btn);
		render.renderImage(200, 80, btn);
		render.renderImage(selx, sely, sel);		
	}

	@Override
	public void update() {
		if(key.keyPressed(KeyEvent.VK_W)||key.keyPressed(KeyEvent.VK_UP)){
			sely = (sely > 30) ? (sely-25) : 70;
		}
		if(key.keyPressed(KeyEvent.VK_S)||key.keyPressed(KeyEvent.VK_DOWN)){
			sely = (sely < 60) ? (sely+25) : 20;
		}
		if(key.keyPressed(KeyEvent.VK_ENTER)){
			game.change("level1", "init");
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
