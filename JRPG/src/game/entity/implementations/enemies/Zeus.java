package game.entity.implementations.enemies;

import game.entity.SpriteContainer;
import game.entity.implementations.Enemy;
import game.entity.modules.DMGModule;
import game.entity.modules.HPModule;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;

public class Zeus extends Enemy{

	
	public Zeus(int x, int y, int w, int h, Movement mov, Level level, Rectangle tileOffs) {
		super(x, y, w, h, mov, level, tileOffs);
		
		MAXHP = 1000;
		DMG = 10;
		IMMUNETIME = 25;
		
		Sprite currentAnim = new SingleSprite(90,124, 0, 0, new Spritesheet(level.AM.getImage("zeus")));
  		Sprite currentAnimSplit =new SingleSprite(90,124, 0, 0, new Spritesheet(level.AM.getImage("zeus")));
  		currentAnimSplit.FlipAll();
  		
  		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnim); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprites(3,currentAnimSplit); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprites(5, currentAnim);
		msm.add("normal", new NormalMove(normalState,false));
		msm.change("normal", "", false);
			
		hp_mod = new HPModule(MAXHP, MAXHP, IMMUNETIME);
		dmg_mod = new DMGModule(DMG);
		//TODO hace object pool para los rayos de zeus y lo corazns, etc
	}

}
