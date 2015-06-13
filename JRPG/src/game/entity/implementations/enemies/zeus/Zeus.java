package game.entity.implementations.enemies.zeus;

import game.entity.Entity;
import game.entity.SpriteContainer;
import game.entity.implementations.Enemy;
import game.entity.implementations.Hearth;
import game.entity.modules.DMGModule;
import game.entity.modules.HPModule;
import game.entity.movement.Movement;
import game.entity.movestate.NormalMove;
import game.entity.weapons.Thrower;
import game.entity.weapons.Weapon;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.util.HashMap;

public class Zeus extends Enemy{
	private int contHPSPawn = 0, spawnHPonHits = 4;
	private int cont = 0;
	public HashMap<String,Weapon> weps;
	private HashMap<String,ZeusPhase> phases;
	private ZeusPhase currentPhase;
	
	private int low_hp;
	
	public Zeus(int x, int y, int w, int h, Movement mov, Level level) {
		super(x, y, w, h, mov, level);
		
		MAXHP = 500;
		DMG = 5;
		IMMUNETIME = 25;
		
		Sprite currentAnim = new SingleSprite(90,124, 0, 0, new Spritesheet(level.AM.getImage("zeus")));
  		Sprite currentAnimSplit =new SingleSprite(90,124, 0, 0, new Spritesheet(level.AM.getImage("zeus")));
  		currentAnimSplit.FlipAll();
  		
  		
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnimSplit); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprites(3,currentAnim); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprites(5, currentAnimSplit);
		msm.add("normal", new NormalMove(normalState,false));
		msm.change("normal", "", false);
			
		hp_mod = new HPModule(MAXHP, MAXHP, IMMUNETIME);
		dmg_mod = new DMGModule(DMG);
		//TODO hace object pool para los rayos de zeus y lo corazns, etc

		
		weps = new HashMap<String, Weapon>();
		
		weps.put("RayZeus",new Thrower(this, 5, "RayZeus", 2));
		weps.put("ThunderBall",new Thrower(this, 2, "ThunderBall", 1));
		weps.put("ThunderBallGreen",new Thrower(this, 2, "ThunderBallGreen", 1));
		
		phases = new HashMap<String, ZeusPhase>();
		
		addPhase("initial", new InitialPhase(this));
		addPhase("middle", new MiddlePhase(this));
		addPhase("enrage", new EnragePhase(this));
		
		changePhase("initial");
		
		low_hp = MAXHP/5;
		
	}
	
	public void addPhase(String name, ZeusPhase z){
		phases.put(name, z);
	}
	public void changePhase(String name){
		if(currentPhase!=null)currentPhase.onExit();
		currentPhase = phases.get(name);
		currentPhase.onEnter();
	}
	
	@Override
	public void receiveDmg(int dmg, Entity e) {
		if(!hp_mod.isImmune()&&active){
			contHPSPawn++;
			if(contHPSPawn>=spawnHPonHits){
				contHPSPawn=0;
				Hearth hp = new Hearth(30, getX()+45,getY()+130, level);
				level.addEntityToList(hp);
			}
			hp_mod.hit(dmg);
			
			currentPhase.onDmgReceived();
		}
	}
	
	@Override
	public void dealtDamage(int d) {	
		currentPhase.onDmgDealt();
	}
	
	@Override
	public void update() {
		super.update();
		
		cont++;
			
			
		if(hp_mod.getHP()>low_hp){
			if(cont%900==0){
				changePhase("middle");
			}
			
			if(cont%900==300){
				changePhase("initial");
			}
		}else{
			changePhase("enrage");
		}
		

		currentPhase.update();

		
		
	}
	


}
