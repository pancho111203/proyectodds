package game.entity.weapons;

import game.AssetManager;
import game.entity.SpriteContainerWithReceiver;
import game.entity.SpriteFinishReceiver;
import game.entity.movestate.IMove;
import game.entity.movestate.LockMove;
import game.graphics.Animator;
import game.graphics.Spritesheet;

public class Sword implements Weapon{

	private SpriteFinishReceiver parent;
	private boolean active = false;
	private IMove move;
	
	//las clases de armas se encargan de definir los sprites que van a usar y crean el IMove
	//tambien se ecargan de gestionar las colisiones, etc
	//pero la clase que usa el arma tiene que recibir el evento de cuando el ataque termina y gestionar el cambio de estado por si misma
	
	public Sword(SpriteFinishReceiver ent, String attackFinishEvent){
		parent = ent;
		
		
		Animator attackAnimSword = new Animator(60, 68, 0, 0, 3, new Spritesheet(AssetManager.getSingleton().getImage("ataqueFrente")), 15,true);
		SpriteContainerWithReceiver attackStateContainer = new SpriteContainerWithReceiver(parent);
		attackStateContainer.addAnimatorWithReceiver("0", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("1", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("2", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("3", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("4", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("5", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("6", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("7", attackAnimSword, attackFinishEvent);
		attackStateContainer.addAnimatorWithReceiver("8", attackAnimSword, attackFinishEvent);
		move = new LockMove(attackStateContainer);
	}
	public void update(){
		if(active){
			//System.out.println("atacando");
		}
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub

		active = true;
	}

	@Override
	public void stopAttack() {
		// TODO Auto-generated method stub
		active = false;
	}
	@Override
	public String getType() {
		return "Sword";
	}
	public IMove getVisualMovement() {
		return move;
	}

}
