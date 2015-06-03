package game.entity.movestate;

import game.entity.SpriteContainer;
import game.graphics.Animator;
import game.graphics.Sprite;
//no permite cambio de animator mientras el estado este activo, solo asignacion al entrar en el estado con onEnter
//tampoco permite movimiento(si quiero volver a meterle movimiento basta con quitar los comments en los metodos)
public class LockMove implements IMove{

	private SpriteContainer container;
	private Sprite currentSprite;
	//private int movX, movY;
	
	public LockMove(SpriteContainer sc){
		
		container = sc;
		currentSprite = container.getSprite("8");
	}
	
	@Override
	public void update() {
		if(currentSprite instanceof Animator){
			((Animator)currentSprite).update();
		} 
	}

	@Override
	public void onEnter(int dir) {
		
		currentSprite = container.getSprite(dir+"");
		currentSprite.startAgain();
		
	}

	
	@Override
	public void changeDirection(int dir) { // esta clase solo permite cambio de sprite en la entrada del estado ("onEnter")
	}
	
	

	@Override
	public int getMovX() {
		//return movX;
		return 0;
	}

	@Override
	public int getMovY() {
		//return movY;
		return 0;
	}

	@Override
	public void move(int movX, int movY) {
		//this.movX = movX;
		//this.movY = movY;
	}

	@Override
	public Sprite getSprite() {
		return currentSprite;
	}

}
