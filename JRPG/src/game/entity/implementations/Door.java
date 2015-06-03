package game.entity.implementations;

import game.entity.Entity;
import game.graphics.RenderingLevel;
import game.input.GameInput;
import game.level.Level;

public class Door extends Entity{
	private GameInput gi;
	private String targetLevel;
	private int spXNL, spYNL;
	public Door(int x, int y, int w, int h, Level level, String targetLevel, int spawnXNewLVL,int spawnYNewLvl) {
		super(x, y, w, h, level);
		
		spXNL = spawnXNewLVL;
		spYNL = spawnYNewLvl;
		
		this.targetLevel = targetLevel;
		
		gi = GameInput.getSingleton();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(RenderingLevel render) {
		debug(render);		
	}

	@Override
	public void collide(Entity e,String args) {
		if(gi.inputPressed(9)&&args.equals("player")){
			((Player)e).changeZone(targetLevel, spXNL, spYNL);
		}
	}

}
