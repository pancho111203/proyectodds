package game.graphics;

public class RenderingMenu extends Rendering{

	public RenderingMenu(int width, int height) {
		super(width, height);
	}
	
	public void renderImage(int xp, int yp, Image sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)

		super.render(sp.getHeight(),sp.getWidth(),xp,yp,sp.pixels);
	
	}
}
