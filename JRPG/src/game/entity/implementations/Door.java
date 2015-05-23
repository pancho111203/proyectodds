package game.entity.implementations;

import game.entity.Entity;
import game.graphics.RenderingLevel;
import game.input.GameInput;
import game.level.Level;

import java.awt.Rectangle;

public class Door extends Entity{
	private GameInput gi;
	private String targetLevel;
	public Door(int x, int y, int w, int h, Level level, String targetLevel) {
		super(x, y, w, h, level);
		
		this.targetLevel = targetLevel;
		
		gi = GameInput.getSingleton();
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(this.x+colliderOffsets.getX()), (int)(this.y+colliderOffsets.getY()));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(RenderingLevel render) {
		debug(render);		
	}

	@Override
	public void collide(Entity e) {
		if(gi.inputPressed(9)){
			System.out.println("DOOR OPENED");
			level.parent.changeLevel(targetLevel);
		}
	}

}
