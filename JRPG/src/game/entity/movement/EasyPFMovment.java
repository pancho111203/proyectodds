package game.entity.movement;

import game.entity.Entity;
import game.level.Level;
import game.level.tiles.Tile;

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
			if(!collides(-speed,0)) move(-speed,0); //moverse a la izq
			else if(!collides(0,speed))move(0,speed);
			else if(!collides(0,-speed))move(0,-speed);
		}else if(eX<=tX-10){
			if(!collides(speed,0)) move(speed,0); //derecha
			else if(!collides(0,speed))move(0,speed);
			else if(!collides(0,-speed))move(0,-speed);
		}
		
		if(eY>tY+10){
			if(!collides(0,-speed)) move(0,-speed); //arriba
			else if(!collides(speed,0))move(speed,0);
			else if(!collides(-speed,0))move(-speed,0);
		}else if(eY<=tY-10){
			if(!collides(0,speed)) move(0,speed); //abajo
			else if(!collides(speed,0))move(speed,0);
			else if(!collides(-speed,0))move(-speed,0);
		}
		
		
		timeToMove();
	}
	
	private boolean collides(int x, int y){
		Tile next = ent.level.getTile(x, y);
		return (ent.collidesWithState(next.getState(0))||ent.collidesWithState(next.getState(1))
				||ent.collidesWithState(next.getState(2))||ent.collidesWithState(next.getState(3)));
	}

}
