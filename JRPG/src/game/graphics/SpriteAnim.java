package game.graphics;

import java.util.ArrayList;

public class SpriteAnim extends Sprite {

	public ArrayList<Animator> actions; //cada sprite animado tendr� un animator para cada cosa que sepa hacer (andar, saltar, atacar, cagar...)
	
	//se puede crear como un Sprite normal y corriente y usarlo como tal o a�adirle despues acciones..
	public SpriteAnim(int size, int x, int y, Spritesheet sheet) {
		super(size, x, y, sheet);
	}
	//no se si es util hacer un constructor con un Arraylist de Animators ya defindos 

	//a�dir una acci�n nueva
	public void addAction(int s, int x,int y, int n){
		actions.add(new Animator(x, y, s, y, sheet));
	}
	
	//(ahora mismo en mi cabeza tiene sentido) le dices el id de la acci�n que est� realizando y pasa al siguiente sprite de esta.
	public void updateSprite(int action){
		this.pixels = actions.get(action).updateSprite().pixels;
	}


}
