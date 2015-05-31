package game.entity.movement;

import game.entity.Entity;
import game.level.Level;

public class EasyPFMovment extends Movement{
	private int speed;
	private Entity target;
	public EasyPFMovment(Level level, Entity target) {
		super(level);
		this.target = target;
		this.speed = 1;
	}

	@Override
	public void updateAux() {
		int eX = ent.getX();
		int eY = ent.getY();
		int tX = target.getX();
		int tY = target.getY();
		
		if(eX>tX+10){
			move(-speed,0); //moverse a la izq
		}else if(eX<=tX-10){
 			move(speed,0); //derecha
		}
		
		if(eY>tY+10){
			move(0,-speed); //arriba
		}else if(eY<=tY-10){
			move(0,speed); //abajo
		}
		
		
		timeToMove();
	}

}
