package game.entity.movement;

import game.level.Level;

public class ForwardMovement extends Movement{
//este tipo de movimiento sigue un camino recto con la velocidad horizontal y vertical especificada(startingX, startingY)
//cuando encuentra un obstaculo cambia de direccion

	private int direcV, direcH;
	
	public ForwardMovement(Level level, int startingX, int startingY) {
		super(level);
		
		direcV = startingY;
		direcH = startingX;
	}

	@Override
	public void updateAux() {
		move(direcH,direcV);
		timeToMove();
	
	}
	
	@Override
	protected void horizontalCollision(){
		direcH = (-1)*direcH;
	}
	
	@Override
	protected void verticalCollision(){
		direcV = (-1)*direcV;
	}

	@Override
	protected boolean collisionWithState(int s) {
		// TODO Auto-generated method stub
		return (s==1)||(s==9); // colision con *solid* y con *void*
	}
	
	

}
