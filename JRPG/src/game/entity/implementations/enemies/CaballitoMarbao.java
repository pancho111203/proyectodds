package game.entity.implementations.enemies;

import game.GameMaster;
import game.entity.SpriteContainer;
import game.entity.SpriteFinishReceiver;
import game.entity.implementations.Enemy;
import game.entity.modules.DMGModule;
import game.entity.modules.HPModule;
import game.entity.movement.Movement;
import game.entity.movestate.NoMove;
import game.entity.movestate.NormalMove;
import game.graphics.Animator;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

public class CaballitoMarbao extends Enemy implements SpriteFinishReceiver{

	
	public CaballitoMarbao(int x, int y, int w, int h, Movement mov, Level level, Rectangle tileOffs) {
		super(x, y, w, h, mov, level, tileOffs);
		
		MAXHP = 100;
		DMG = 10;
		IMMUNETIME = 25;
		
		Sprite currentAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
  		Sprite currentAnimSplit = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30,false);
  		currentAnimSplit.FlipAll();
  		
  		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnim); // hay que a�adir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprites(3,currentAnimSplit); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprites(5, currentAnim);
		msm.add("normal", new NormalMove(normalState,false));
		msm.change("normal", "", false);
		
		Animator deadAnim = new Animator(82,85, 0, 0, 4, new Spritesheet(level.AM.getImage("explosion")), 15,true);
		deadAnim.addNotifictionReceiver(this, "dead");
		msm.add("dead", new NoMove(deadAnim));
		
		
		hp_mod = new HPModule(MAXHP, MAXHP, IMMUNETIME);
		dmg_mod = new DMGModule(DMG);
	}

	
	@Override
	public void die(){
		mov.stop(-1);
		msm.change("dead", "", true);
		active = false;
	}
	
	@Override
	public void spriteFinished(String id) {
		if(id.equals("dead")){
			finishDead();
		}
	}

	private void finishDead() {
		GameMaster.getSingleton().kill(this);
	}

}
