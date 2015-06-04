package game.entity;

import game.graphics.Animator;

public class SpriteContainerWithReceiver extends SpriteContainer implements SpriteFinishReceiver{
	
	//esta clase sirve para facilitar la ceacion de sprites animador con receptor funcionando como intermediaria para los eventos
	
	private SpriteFinishReceiver receptor;
	
	public SpriteContainerWithReceiver(SpriteFinishReceiver recv){
		super();
		
		receptor = recv;
	}

	public void addAnimatorWithReceiver(String name, Animator sprite, String identForReceiver){
		sprite.addNotifictionReceiver(this, identForReceiver);
		sprites.put(name, sprite);
		
	}
	

	@Override
	public void spriteFinished(String id) {
		receptor.spriteFinished(id);
	}
}
