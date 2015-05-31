package game.entity.movement;
import game.entity.implementations.Player;
import game.input.GameInput;
import game.level.Level;

public class PlayerMovement extends Movement{

	
	private int speed;
	private int sprint = 1;
	private GameInput gi;
	
	public PlayerMovement(Level level,int s){
		super(level);
		
		gi= GameInput.getSingleton();
		 
		speed = s;
	}
	
	public void updateAux(){
		if(gi.inputDown(10)){			
			sprint = ((Player)ent).substractEnergy(10)>=0?2:1;
		}else{
			sprint = 1;
		}

		if(gi.inputDown(gi.UP)){
			moveUp();
		}
		if(gi.inputDown(gi.DOWN)){
			moveDown();
		}
		if(gi.inputDown(gi.LEFT)){
			moveLeft();
		}
		if(gi.inputDown(gi.RIGHT)){
			moveRight();
		}
		
		timeToMove();
		
	}
	
	protected void moveLeft(){
		move(-speed*sprint,0);
	}
	protected void moveRight(){
		move(speed*sprint,0);
	}
	protected void moveUp(){
		move(0,-speed*sprint);
	}
	protected void moveDown(){
		move(0,speed*sprint);
	}
	
	
}