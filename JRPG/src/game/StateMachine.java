package game;
import game.graphics.Rendering;
import game.states.IState;
import game.states.StateEmpty;

import java.util.HashMap;

// Maneja el cambio de estados(menu, mapa local, mapa global, etc)
public class StateMachine {
	private HashMap<String,IState> states;
	private IState currentS;
	private GameStart game;
	
	public StateMachine(GameStart gameStart){
		states = new HashMap<String,IState>();
		currentS = new StateEmpty();
		game = gameStart;
	}
	
	public void update(){
		currentS.update();
	}
	
	
	public void render(){
		currentS.render();
	}
	
	
	//cambia el estado actual
	//al cambiar se cierra el estado anterior (onExit()) y se abre el estado siguiente (onEnter())
	public void change(String newState, String params){
		currentS.onExit();
		currentS = states.get(newState);
		currentS.onEnter(params);
		
		game.setRender(getRender());
	}
	
	
	//añade un nuevo estado
	public void add(String stName, IState state){
		states.put(stName, state);
	}
	

	public Rendering getRender(){
		return currentS.getRender();
	}
}
