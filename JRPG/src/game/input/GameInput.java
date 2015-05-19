package game.input;

import game.GameStart;

import java.awt.event.KeyEvent;

public class GameInput {
	
	public final int UP=0,DOWN=1,RIGHT=2,LEFT=3,ATACK=4,JUMP=5,PAUSE=6,ESC=7;
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
		
		actions=new boolean[8];
		actionsPressed=new boolean[8];
		
		for(int i=0;i<8;i++){
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
		for(int i=0;i<8;i++){
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
		if(key.keyPressed(KeyEvent.VK_ENTER)||pad.getButtonValue(pad.START)||pad.getButtonValue(pad.CROS)){
			actionsPressed[ATACK]=true;
			actionsPressed[PAUSE]=true;
		}
		
	}
	
	public void addListeners(GameStart gs){
		gs.addKeyListener(key);
		gs.addMouseListener(mouse);
	}

}
