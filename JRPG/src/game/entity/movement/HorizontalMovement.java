package game.entity.movement;

import game.entity.MovingEntity;
import game.level.Level;

public class HorizontalMovement extends Movement{
	private int startX, endX, distance, speed, direction;
	
	public HorizontalMovement(Level level, int distance, int speed) {
		super(level);
		this.distance = distance;
		this.speed = speed;
	}
	
	public void initializeEntity(MovingEntity e){
		super.initializeEntity(e);
		
		startX = e.getX();
		endX = startX + distance;
		if(startX<=endX){
			direction = 1;
		}else{
			direction = -1;
			int aux = startX;
			startX = endX;
			endX = aux;
		}
	}

	@Override
	public void updateAux() {
		if(direction==1){
			if(ent.getX()<endX){
				move(speed, 0);
				
			}else{
				direction = -1;
			}
		}else{
			if(ent.getX()>startX){
				move(-speed, 0);
				
			}else{
				direction = 1;
			}
		}
		timeToMove();
	}
	
	protected boolean checkCollision(int movX, int movY, int dir){
		return false; //este tipo de movimiento nunca colisiona(cambiarlo si hace falta)
	}

}
