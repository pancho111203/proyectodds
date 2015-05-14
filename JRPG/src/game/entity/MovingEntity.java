package game.entity;

import game.entity.movement.Movement;


public abstract class MovingEntity extends Entity{

	protected Movement mov;
	public MovingEntity(int x, int y, int w, int h, Movement mov) {
		super(x, y, w, h);
		
		this.mov = mov;
		
	}
	
	public abstract void move(int movX, int movY);


}
