package game.input;

import game.GameStart;

import java.awt.event.KeyEvent;

public class GameInput {
	
	
	private final int NUMACTIONS = 11;
	//TODO refactoring de esto pasandolo a un enum para evitar errores
	public final int UP=0,DOWN=1,RIGHT=2,LEFT=3,ATTACK=4,JUMP=5,PAUSE=6,ESC=7, F9=8, ACTION=9, SPRINT=10;
	public boolean actions[],actionsPressed[]; 

	private Keyboard key;
	private Gamepad pad;
	private Mouse mouse;
	
	private static GameInput gi;
	
	public static GameInput getSingleton(){
		if(gi==null){
			gi = new GameInput();
		}
		return gi;
	}
	
	public GameInput() {
		key= Keyboard.getSingleton();
		pad= Gamepad.getSingleton();
		mouse= Mouse.getSingleton();
		
		actions=new boolean[NUMACTIONS];
		actionsPressed=new boolean[NUMACTIONS];
		
		for(int i=0;i<NUMACTIONS;i++){
			actions[i]=false;
			actionsPressed[i]=false;
		}
	}
	
	public boolean inputDown(int i){
		return actions[i];
	}
	
	public boolean inputPressed(int i){
		return actionsPressed[i];
	}
	
	public void update(){
		key.update();
		mouse.update();
		
		//limpiar imputs
		for(int i=0;i<NUMACTIONS;i++){
			actions[i]=false;
			actionsPressed[i]=false;
		}
		
		//leerDOWN
		if(key.keyDown(KeyEvent.VK_W)||key.keyDown(KeyEvent.VK_UP)||pad.getPadState(pad.Lup)||pad.getButtonValue(pad.UP)){
			actions[UP]=true;
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)||pad.getPadState(pad.Ldown)||pad.getButtonValue(pad.DOWN)){
			actions[DOWN]=true;
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)||pad.getPadState(pad.Lleft)||pad.getButtonValue(pad.LEFT)){
			actions[LEFT]=true;
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)||pad.getPadState(pad.Lright)||pad.getButtonValue(pad.RIGHT)){
			actions[RIGHT]=true;
		}
		if(key.keyDown(KeyEvent.VK_F9)){
			actions[F9]=true;
		}
		if(key.keyDown(KeyEvent.VK_ENTER)||pad.getButtonValue(pad.CROS)||pad.getButtonValue(pad.START)){
			actions[ACTION]=true;
		}
		if(key.keyDown(KeyEvent.VK_SHIFT)||pad.getButtonValue(pad.SQUA)){
			actions[SPRINT]=true;
		}
		if(key.keyDown(KeyEvent.VK_SPACE)||key.keyDown(KeyEvent.VK_Z)||pad.getButtonValue(pad.CIRC)){
			actions[ATTACK]=true;
		}
		
		if(key.keyDown(KeyEvent.VK_ESCAPE)){
			actions[PAUSE]=true;
		}
		
		//leerPRESSED
		if(key.keyPressed(KeyEvent.VK_W)||key.keyPressed(KeyEvent.VK_UP)||pad.padChanged(pad.Lup)||pad.buttonChanged(pad.UP)){
			actionsPressed[UP]=true;
		}
		if(key.keyPressed(KeyEvent.VK_S)||key.keyPressed(KeyEvent.VK_DOWN)||pad.padChanged(pad.Ldown)||pad.buttonChanged(pad.DOWN)){
			actionsPressed[DOWN]=true;
		}
		if(key.keyPressed(KeyEvent.VK_A)||key.keyPressed(KeyEvent.VK_LEFT)||pad.padChanged(pad.Lleft)||pad.buttonChanged(pad.LEFT)){
			actionsPressed[LEFT]=true;
		}
		if(key.keyPressed(KeyEvent.VK_D)||key.keyPressed(KeyEvent.VK_RIGHT)||pad.padChanged(pad.Lright)||pad.buttonChanged(pad.RIGHT)){
			actionsPressed[RIGHT]=true;
		}
		if(key.keyPressed(KeyEvent.VK_F9)){
			actionsPressed[F9]=true;
		}
		if(key.keyPressed(KeyEvent.VK_ENTER)||pad.buttonChanged(pad.CROS)||pad.buttonChanged(pad.START)){
			actionsPressed[ACTION]=true;
		}
		if(key.keyPressed(KeyEvent.VK_SHIFT)||pad.buttonChanged(pad.SQUA)){
			actionsPressed[SPRINT]=true;
		}
		if(key.keyPressed(KeyEvent.VK_SPACE)||key.keyPressed(KeyEvent.VK_Z)||pad.buttonChanged(pad.CIRC)){
			actionsPressed[ATTACK]=true;
		}
		if(key.keyPressed(KeyEvent.VK_ESCAPE)){
			actionsPressed[PAUSE]=true;
		}
	}
	
	public void addListeners(GameStart gs){
		gs.addKeyListener(key);
		gs.addMouseListener(mouse);
	}

}
