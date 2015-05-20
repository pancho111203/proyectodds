package game.entity;

import game.graphics.RenderingLevel;
import game.level.Level;

public class NullEntity extends Entity {

	public NullEntity(Level level) {
		super(0, 0, 0, 0,level);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(RenderingLevel render) {
	}

	@Override
	public void collide(Entity e) {
	}

}
