package game.entity.movement;

import game.entity.MovingEntity;
import game.level.Level;
import game.level.tiles.Tile;

import java.awt.Rectangle;

public abstract class Movement { // maneja el movimiento
	// no permite que haya movimiento hacia dos lados contrarios a la vez(evita fallos)
	// maneja colisiones
	
	protected int stop = 0; //representa los proximus updates que no habra movimiento(si es negativo sera infinito)
	
	
	protected int prevVertical;
	protected int prevHorizontal;
	protected int vertical, verticalStopped;
	protected int horizontal, horizontalStopped;
	
	protected MovingEntity ent;
	protected boolean doMove;
	
	protected final int TS;
	
	protected Level level;
	
	private int startColX, startColY, endColX, endColY;
	
	public Movement(Level level){
		
		
		prevVertical = 0;
		prevHorizontal = 0;
		horizontal = 0;
		vertical = 0;
		verticalStopped = 0;
		horizontalStopped = 0;
		
		doMove = false;
		
		this.level = level;
		
		TS= Level.TILESIZE;
		
		
	}
	
	public void initializeEntity(MovingEntity e){
		
		ent = e;
		
		Rectangle offs = ent.getSpriteOffsets();
		
		startColX = (int)offs.getX();
		endColX = (int)offs.getWidth();
		startColY = (int)offs.getY();
		endColY = (int)offs.getHeight();
	}
	
	
	public void update(){
		if(ent!=null){
			updateAux();
		}
	};
	
	public abstract void updateAux();
	
	protected void timeToMove(){
		if(stop<0)return;
		
		if(stop>0){
			executeMovement(true);
			stop--;//si esta parado, solo ejecuta el movimiento obligatorio
		}else{			
			executeMovement(false);// si no esta parado, ejecuta todo el mov
		}
	}
	
	protected void executeMovement(boolean onlyStoppedMov){

		// variable con valores de 0 a 7 indicando la direccion(tmb diagonal), se usa sobre todo para elegir sprite
		//si tiene valor 8 es pq el pj esta quieto
		int dir = 8;
		if(horizontal<0){
			if(vertical<0){
				dir = 7;
			}else if(vertical>0){
				dir = 5;
			}else{
				dir = 6;
			}
		}else if(horizontal>0){
			if(vertical<0){
				dir = 1;
			}else if(vertical>0){
				dir = 3;
			}else{
				dir = 2;
			}
		}else{
			if(vertical<0){
				dir = 0;
			}else if(vertical>0){
				dir = 4;
			}
		}
		
		ent.changeDirection(dir);

		if(onlyStoppedMov){
			horizontal = horizontalStopped;
			vertical = verticalStopped;
		}else{
			horizontal = horizontal + horizontalStopped; 
			vertical = vertical + verticalStopped;
		}
		
		
		if(doMove){
			
			if(horizontal!=0){
				int auxDir = (horizontal>0 ? 1 : 3);
				if(!checkCollision(horizontal, 0, auxDir)){
					ent.move(horizontal, 0);
				}else{
					horizontalCollision();
				}
			}
			if(vertical!=0){
				int auxDir = (vertical>0 ? 2 : 0);
				if(!checkCollision(0, vertical, auxDir)){
					ent.move(0, vertical);
				}else{
					verticalCollision();
				}
			}
			doMove = false;
			prevVertical = vertical;
			prevHorizontal = horizontal;
			vertical = 0;
			horizontal = 0;
			verticalStopped=0;
			horizontalStopped=0;
		}
	}
	protected void horizontalCollision(){//estos dos metodos funcionan como manejadores de eventos
		//pueden estar vacios pero si una clase movimiento los implementa puede realizar acciones cuando ocurra una colision
		
	}
	
	protected void verticalCollision(){
		
	}
	
	
	public void move(int movX, int movY){
		doMove = true;
		
		
		
		if(movX!=0){
			if(prevHorizontal * movX >= 0){ // si este frame esta llendo hacia la misma direccion que el anterior
				horizontal = movX;
			}else{
				if(horizontal == 0){horizontal = movX;}
			}
		}
		
		
		if(movY!=0){
			if(prevVertical * movY >= 0){ // si este frame esta llendo hacia la misma direccion que el anterior
				vertical = movY;
			}else{
				if(vertical == 0){vertical = movY;}
			}
		}	
		
	}
	
	//TODO puede que este mal, check (he hecho copy paste)
	public void moveWhenStopped(int movX, int movY){
		doMove = true;
		
		
		
		if(movX!=0){
			if(prevHorizontal * movX >= 0){ // si este frame esta llendo hacia la misma direccion que el anterior
				horizontalStopped = movX;
			}else{
				if(horizontalStopped == 0){horizontalStopped = movX;}
			}
		}
		
		
		if(movY!=0){
			if(prevVertical * movY >= 0){ // si este frame esta llendo hacia la misma direccion que el anterior
				verticalStopped = movY;
			}else{
				if(verticalStopped == 0){verticalStopped = movY;}
			}
		}	
		
	}
	
	protected boolean checkCollision(int movX, int movY, int dir) {
		
		
		int nextX = movX + ent.getX();
		int nextY = movY + ent.getY();
				
		
		switch (dir){
		case 0: //va hacia arriba
			
			if(isSolid(nextX+startColX,nextY+startColY, -1) || isSolid(nextX+endColX,nextY+startColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColX < endColX ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX+(i*TS),nextY+startColY, 2)){return true;}
			}
			
			return false;
			
		case 1: //va hacia derecha
			
			if(isSolid(nextX+endColX,nextY+endColY, -1) || isSolid(nextX+endColX,nextY+startColY, -1)){
				
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColY < endColY ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+endColX,nextY+startColY+(i*TS), 3)){return true;}
			}
			
			return false;
			
		case 2: //va hacia abajo
			
			if(isSolid(nextX+endColX,nextY+endColY, -1) || isSolid(nextX+startColX,nextY+endColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColX < endColX ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX+(i*TS),nextY+endColY, 0)){return true;}
			}
			
			return false;
						
		case 3: //va hacia izq
			
			if(isSolid(nextX+startColX,nextY+endColY, -1) || isSolid(nextX+startColX,nextY+startColY, -1)){
				return true; //prueba para las esquinas
			}
			for(int i = 1;(i*TS)+startColY < endColY ; i++){
				//prueba para las uniones entre subtiles de 16x16(del player)
				if(isSolid(nextX+startColX,nextY+startColY+(i*TS), 1)){return true;}
			}
			
			return false;
			
		}
		
		return false;
	}
	
	private boolean isSolid(int nx, int ny, int side){ 
		//si side no es -1, el metodo comprueba ambos subtiles del lado indicado
		
		int restX = nx%TS;
		int restY = ny%TS;
		
		int middle = TS/2;
		
		int pos, posSideX, posSideY;
		
		Tile hit = level.getTile(nx/TS,ny/TS );
		
		if(restX<middle){ // parte izquierda
			if(restY<middle){//arriba
				pos = 0;
				posSideX = 1;
				posSideY = 2;
			}else{//abajo
				pos = 2;
				posSideX = 3;
				posSideY = 0;
			}
		}else{ // parte derecha
			if(restY<middle){//arriba
				pos = 1;
				posSideX = 0;
				posSideY = 3;
			}else{//abajo
				pos = 3;
				posSideX = 2;
				posSideY = 1;
			}
		}
		
		
		if(side==0||side==2){ // check horizontal
			return (collisionWithState(hit.getState(pos)))||(collisionWithState(hit.getState(posSideX)));
		}else if(side==1||side==3){// check vertical
			return (collisionWithState(hit.getState(pos)))||(collisionWithState(hit.getState(posSideY)));
		}else{
			return collisionWithState(hit.getState(pos));
		}
	}
	
	protected boolean collisionWithState(int s){
		//esta implementacion permite que se defina dentro de la MovingEntity los elementos con los que esta colisiona. 
		//pero lo bueno de esta implementacion es que algunas clases que extiendan Movement pueden sobreesribir el metodo, creando
		// movimientos con unos colliders especificos
		
		
		
		return ent.collidesWithState(s);
		// checkea si el tipo de movimiento genera colisiones cn el estado dado
	}
	//p ejemplo, para unidades voladoras el estado 1(solido) no generara colisiones

	public void stop(int i) {
		stop = i;
		
	}
}
