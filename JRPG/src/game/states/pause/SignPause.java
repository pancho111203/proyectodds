package game.states.pause;

import game.AssetManager;
import game.GameStart;
import game.graphics.Image;
import game.graphics.Rendering;
import game.input.GameInput;

import java.awt.Graphics;

public class SignPause implements PausedState{
	
	private int stringStartPosX = 20;
	private int stringStartPosY = 190;
	private String content;
	
	public SignPause(String content){
		this.content = content;
	}

	@Override
	public void render(Rendering render) {
		render.renderImage(20, 190, new Image(AssetManager.getSingleton().getImage("bocadillo")));
	}

	@Override
	public void update() {
		if(GameInput.getSingleton().inputPressed(GameInput.getSingleton().ACTION)){
			GameStart.getSingleton().unpause();
		}
	}

	@Override
	public void renderGraphics(Graphics g) {
		g.setFont(GameStart.getSingleton().dialogFont);
		g.drawString(content, stringStartPosX, stringStartPosY);
	}
}
