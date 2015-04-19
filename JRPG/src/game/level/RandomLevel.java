package game.level;

import game.graphics.RenderingLevel;

import java.util.Random;

public class RandomLevel extends Level {

	private Random random = new Random();
	private final int TILESIZE = 32;
	
	public RandomLevel(int w, int h){
		super();
		width = w;
		height = h;
		tiles = new int[width*height];
		loadLevel();
	}
	
	@Override
	public void loadLevel() {
		for(int i=0;i<width*height;i++){
			tiles[i] = random.nextInt(10000000);
		}
	}

	public void render(RenderingLevel render){
		render.renderByColors(tiles,TILESIZE);
		super.render(render);
	}
}
