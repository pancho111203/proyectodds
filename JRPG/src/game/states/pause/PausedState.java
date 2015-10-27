package game.states.pause;

import game.graphics.Rendering;

import java.awt.Graphics;

public interface PausedState {
	public void render(Rendering rendering);
	public void update();
	public void renderGraphics(Graphics g);
}
