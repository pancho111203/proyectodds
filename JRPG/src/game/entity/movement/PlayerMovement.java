package game.entity.movement;
import game.input.Gamepad;
import game.input.Keyboard;
import game.level.Level;

import java.awt.event.KeyEvent;

public class PlayerMovement extends Movement{

	
	private int speed;
	
	private Gamepad pad;
	private Keyboard key;
	
	public PlayerMovement(Level level,int s){
		super(level);
		
		key = Keyboard.getSingleton();
		pad = new Gamepad();
		
		speed = s;
		
	}
	
	public void updateAux(){

		if(key.keyDown(KeyEvent.VK_W)||key.keyDown(KeyEvent.VK_UP)||pad.getPadState(pad.Lup)||pad.getButtonValue(pad.UP)){
			moveUp();
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)||pad.getPadState(pad.Ldown)||pad.getButtonValue(pad.DOWN)){
			moveDown();
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)||pad.getPadState(pad.Lleft)||pad.getButtonValue(pad.LEFT)){
			moveLeft();
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)||pad.getPadState(pad.Lright)||pad.getButtonValue(pad.RIGHT)){
			moveRight();
		}
		
		timeToMove();
		
	}
	
	protected void moveLeft(){
		move(-speed,0);
	}
	protected void moveRight(){
		move(speed,0);
	}
	protected void moveUp(){
		move(0,-speed);
	}
	protected void moveDown(){
		move(0,speed);
	}

	@Override
	protected boolean collisionWithState(int s) {
		return s==1; //colision con *solid*
	}
	
	
}