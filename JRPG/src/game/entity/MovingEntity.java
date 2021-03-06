package game.entity;

import game.entity.movement.Movement;
import game.entity.movestate.MoveStateMachin;
import game.level.Level;
import auxiliar.Vector2D;


public abstract class MovingEntity extends Entity{

	protected Movement mov;
	protected MoveStateMachin msm;
	protected int dir;
	protected boolean stayingStill = true;
	
	public MovingEntity(int x, int y, int w, int h, Level level, Movement mov) {
		super(x, y, w, h, level);
		
		this.mov = mov;
		
		msm = new MoveStateMachin(this);
		
	}
	
	public abstract void move(int movX, int movY); // dir es variable de 0 a 8 indicando direccion(8 es parado)

	public abstract boolean collidesWithState(int s);
	
	public void changeDirection(int dir){
		if(this.dir!=dir||stayingStill==true){
			if(dir==8){
				stayingStill = true;
			}else{
				stayingStill = false;
				this.dir = dir;
			}
			msm.changeDirection(this.dir,stayingStill);
		}
	}
	
	protected void updateCollider(){
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
		
	}
	
	public int getDir(){
		return dir;
	}
	
	protected void push(Vector2D eVec, int dist){
		
		Vector2D vec = new Vector2D(x,y);

		vec = vec.minus(eVec);

		vec = vec.normalizeToLength(dist);
		
		mov.moveWhenStopped((int)vec.x, (int)vec.y);
		
	}

}
