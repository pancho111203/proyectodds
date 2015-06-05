package game.entity.implementations;

import game.entity.Entity;
import game.entity.types.EntityActionable;
import game.graphics.RenderingLevel;
import game.level.Level;

public class Door extends Entity implements EntityActionable{
	private String targetLevel;
	private int spXNL, spYNL;
	public Door(int x, int y, int w, int h, Level level, String targetLevel, int spawnXNewLVL,int spawnYNewLvl) {
		super(x, y, w, h, level);
		
		spXNL = spawnXNewLVL;
		spYNL = spawnYNewLvl;
		
		this.targetLevel = targetLevel;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(RenderingLevel render) {
		debug(render);		
	}

	@Override
	public void action(Player e) {
		e.changeZone(targetLevel, spXNL, spYNL);
	}

}
