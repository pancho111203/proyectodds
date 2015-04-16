package game;
import game.graphics.Rendering;
import game.states.IState;

import java.util.HashMap;
import java.util.LinkedList;
// Maneja el cambio de estados(menu, mapa local, mapa global, etc) creando un STACK!
// la version stack es mas util para manejar menus que aparecen en mitad de la ejecucion del juego
public class StateStack {
	private HashMap<String,IState> states;
	private LinkedList<IState> stateQueue;
	
	public StateStack(){
		states = new HashMap<String,IState>();
		stateQueue = new LinkedList<IState>();
	}
	
	public void update(){
		IState top = stateQueue.peek();
		top.update();
	}
	
	
	public void render(){
		IState top = stateQueue.peek();
		top.render();
	}
	
	
	//cambia el estado actual
	//al cambiar se cierra el estado anterior (onExit()) y se abre el estado siguiente (onEnter())
	//el estado anterior queda debajo para abrirse cuando este se cierre
	public void push(String newState, String params){
		
		IState state = states.get(newState);
		state.onEnter(params);
		stateQueue.push(state);
	}
	
	public IState pop()
    {
		stateQueue.peek().onExit();
        return stateQueue.pop();
    }
	
	
	//añade un nuevo estado
	public void add(String stName, IState state){
		states.put(stName, state);
	}
	
	

	public Rendering getRender(){
		return stateQueue.peek().getRender();
	}
	
}