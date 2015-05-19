package game.entity.movestate;

import game.graphics.Sprite;

import java.util.HashMap;

public class MoveStateMachin {
	// cada estado diferente se puede usar para cambiar estas cosas dependiendo del estado en el que se este:
	// 1. los valores movX y movY(velocidad de movimiento)
	// 2.los sprites usados
	// en el metodo update de cada estado se puede implementar cualquier funcionalidad
	// p ejemplo: en el estado nadando, puedo poner un contador en el update y si se pasa nadando mas de 10 segundos se muere
	private IMove current;
	private HashMap<String,IMove> states;
	private int dir;
	
	public MoveStateMachin(){
		states = new HashMap<String,IMove>();
		current = new EmptyMove();
	}
	
	public void move(int movX, int movY){
		current.move(movX, movY);
	}
	
	public int getMovX(){
		return current.getMovX();
	}
	public int getMovY(){
		return current.getMovY();
	}
	
	public void update(){
		current.update();
	}
	
		
	public void change(String newState, String params){
		current = states.get(newState);
		current.onEnter(dir);
	}
	
	
	//añade un nuevo estado
	public void add(String stName, IMove state){
		states.put(stName, state);
	}

	public void changeDirection(int dir) {
		this.dir = dir;
		current.changeDirection(dir);
	}

	public Sprite getSprite() {
		return current.getSprite();
	}
	
	
}
