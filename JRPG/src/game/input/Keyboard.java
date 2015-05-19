package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private final int MAX_KEYS = 256;
	
	private static Keyboard keyboard;
	
	private StateKey[] keys;
	
	private boolean[] pressed;
	
	
	private enum StateKey{
		PRESSED,
		HOLD,
		RELEASED
	}
	
	public static Keyboard getSingleton(){
		if(keyboard==null){
			keyboard = new Keyboard();
		}
		return keyboard;
	}
	
	private Keyboard(){
		
		pressed = new boolean[MAX_KEYS];
		keys = new StateKey[MAX_KEYS];
		
		for(int i = 0;i<MAX_KEYS;i++){
			keys[i] = StateKey.RELEASED;
		}
	}
	
	public synchronized void update(){
		for(int i = 0;i<MAX_KEYS;i++){ // updates the array keys each time the update function is called
			if(pressed[i]){
				if(keys[i]==StateKey.RELEASED){
					keys[i] = StateKey.PRESSED;
				}else{
					keys[i] = StateKey.HOLD;
				}
			}else{
				keys[i] = StateKey.RELEASED;
			}
		}
	}

	
	//keyDown devuelve true cuando la tecla se esta manteniendo
	public boolean keyDown(int k){ // k = codigo de la tecla
		try{
			StateKey s = keys[k];
			return (s == StateKey.PRESSED || s == StateKey.HOLD);
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Called method keyDown(int k) of Keyboard with a key k that exceeds the MAX_KEYS limit");
			e.printStackTrace();
			return false;
		}
	}
	
	//keyDown devuelve true cuando la tecla se acaba de pulsar
	public boolean keyPressed(int k){ // k = codigo de la tecla
		try{
			StateKey s = keys[k];
			return (s == StateKey.PRESSED);
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Called method keyPressed(int k) of Keyboard with a key k that exceeds the MAX_KEYS limit");
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k>=0&&k<MAX_KEYS){
			pressed[k]=true;
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k>=0&&k<MAX_KEYS){
			pressed[k]=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No es necesario
		
	}

}
