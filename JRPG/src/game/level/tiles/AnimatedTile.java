package game.level.tiles;

import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;

public class AnimatedTile implements Tile{

	
	//TODO en proceso
	private Animator anim;
	
	public AnimatedTile(){
		
	}
	
	@Override
	public SingleSprite getSprite() {
		return anim.getActual();
	}

	@Override
	public void render(int x, int y, int xRest, int yRest, RenderingLevel render) {
		render.renderTile(x, y,xRest,yRest, this);		
	}


}
