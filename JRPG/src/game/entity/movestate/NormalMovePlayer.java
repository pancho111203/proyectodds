package game.entity.movestate;

import game.entity.SpriteContainer;

public class NormalMovePlayer extends NormalMove{

	
	//es como el movimiento normal, pero añade que cuando la entity se par se queda apuntando en la direccion a la que estaba llendo
	//TODO lo he puesto aparte porque puede dar muchos errores esta implementacion, cambiarla mas adelante si es posible
	public NormalMovePlayer(SpriteContainer normalState) {
		super(normalState);
	}
	
	@Override
	public void changeDirection(int dir) {
		
		if(dir==8){
			currentSprite = currentSprite.getFirst();
		}else{
			currentSprite = ns.getSprite(dir+"");
			currentSprite.startAgain();
		}
		

	}

}
