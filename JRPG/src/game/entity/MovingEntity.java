package game.entity;

import game.entity.movement.Movement;
import game.entity.movestate.MoveStateMachin;
import game.level.Level;


public abstract class MovingEntity extends Entity{

	protected Movement mov;
	protected MoveStateMachin msm;
	protected int dir;
	
	public MovingEntity(int x, int y, int w, int h, Level level, Movement mov) {
		super(x, y, w, h, level);
		
		this.mov = mov;
		
		msm = new MoveStateMachin();
		
	}
	
	public abstract void move(int movX, int movY); // dir es variable de 0 a 8 indicando direccion(8 es parado)

	public abstract boolean collidesWithState(int s);
	
	public void changeDirection(int dir){
		if(this.dir!=dir)msm.changeDirection(dir);
		this.dir = dir;
	}
	
	protected void updateCollider(){
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
		
	}


}
