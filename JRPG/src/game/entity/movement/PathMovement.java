package game.entity.movement;

import game.level.Level;

public class PathMovement extends Movement{
// este tipo de movimiento va desde el punto inicial hasta alcanzar una disttancia dada(distanceX y distanceY) y vuelve
// me ha quedado complicado porque tiene que andar en diagonal
// NO TIENE EN CUENTA COLISIONES 
	private boolean highX;
	private int speedH,speedV;
	private int maxD, minD,counter;
	private double rel;
	private int index;
	public PathMovement(Level level, int distanceX, int distanceY, int speed) {
		super(level);

		speedV = speed;
		speedH = speed;
		counter = 0;
		index = 0;	
		if(distanceX<0){
			speedH = -speedH;
		}
		
		if(distanceY<0){
			speedV = -speedV;
		}
		
		if(Math.abs(distanceX)>Math.abs(distanceY)){
			maxD = distanceX;
			minD = distanceY;
			highX = true;
		}else{
			maxD = distanceY;
			minD = distanceX;
			highX = false;
		}

		if(minD!=0){
			rel = maxD/minD;
		}else{
			rel=0;
		}
		
	}
	
	@Override
	public void updateAux() {
		if(maxD==0){
			return;
		}
		counter+=Math.abs(speedH);

		if(highX){
			move(speedH,0);
		}else{
			move(0, speedV);
		}
		
		
		if(rel!=0&&(double)Math.abs(counter)>=rel*index){
			if(highX){
				move(0,speedV);
			}else{
				move(speedH, 0);
			}
			index ++;
		}
		
		if(Math.abs(counter)>=Math.abs(maxD)){
			counter=0;
			index=0;
			speedH = -speedH;
			speedV = -speedV;
		}
			
		timeToMove();
	}

	protected boolean checkCollision(int movX, int movY, int dir){
		return false; //este tipo de movimiento nunca colisiona(cambiarlo si hace falta)
	}
	
	@Override
	protected boolean collisionWithState(int s) {
		return false;
	}
}
