package game.entity.movement;

import game.entity.Entity;
import game.level.Level;

public class EasyPFMovment extends Movement{
	private int speed;
	private Entity target;
	private int eX,eY,tX,tY;
	private boolean nextVertical=false;
	private boolean nextHorizontal=false;
	
	private boolean movedH = false, movedV = false;
	public EasyPFMovment(Level level, Entity target) {
		super(level);
		this.target = target;
		this.speed = 1;
	}

	@Override
	public void updateAux() {
		eX = ent.getX()+ent.getWidth()/2;
		eY = ent.getY()+ent.getHeight()/2;
		tX = target.getX()+target.getWidth()/2;
		tY = target.getY()+target.getHeight()/2;
		
		
		int horDistance = Math.abs(tX - eX);
		int verDistance = Math.abs(tY - eY);
		
		
		if(nextHorizontal){
			moveHorizontal();
		}else if(nextVertical){
			moveVertical();
		}
		
		if(horDistance>verDistance){
			moveHorizontal();
		}else if(horDistance<verDistance){
			moveVertical();
		}else{
			moveVertical();
			moveHorizontal();
		}
		
		movedH = false;
		movedV = false;
		timeToMove();
	}

	private void moveHorizontal() {
		if(movedH)return; //solo permite una llamada por frame
		movedH = true;
		nextHorizontal = false;
		if(eX>tX){
			move(-speed,0); //moverse a la izq			
		}else if(eX<tX){
			move(speed,0); //derecha
		}		
	}

	private void moveVertical() {
		if(movedV)return; //solo permite una llamada por frame
		movedV = true;
		nextVertical = false;
		if(eY>tY){
			move(0,-speed); //arriba
		}else if(eY<tY){
			move(0,speed); //abajo
		}
	}
	
	@Override
	protected void horizontalCollision(){
		nextVertical = true;
	}
	
	@Override
	protected void verticalCollision(){
		nextHorizontal = true;
	}

}
