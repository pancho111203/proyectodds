package game.entity.implementations;


import static org.junit.Assert.assertFalse;
import game.AssetManager;
import game.GameMaster;
import game.entity.AtackingEntity;
import game.entity.Entity;
import game.entity.MovingEntity;
import game.entity.SpriteContainer;
import game.entity.SpriteFinishReceiver;
import game.entity.collision.Collider;
import game.entity.collision.OwnsCollider;
import game.entity.modules.DMGModule;
import game.entity.modules.EnergyModule;
import game.entity.modules.HPModule;
import game.entity.modules.Module;
import game.entity.movement.Movement;
import game.entity.movestate.NoMove;
import game.entity.movestate.NormalMove;
import game.entity.types.DamagingEntity;
import game.entity.types.EntityActionable;
import game.entity.types.EntityWithStats;
import game.entity.weapons.Sword;
import game.entity.weapons.Weapon;
import game.graphics.Animator;
import game.graphics.Rendering;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.GameInput;
import game.level.Level;
import game.sound.SoundManager;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import auxiliar.Vector2D;

public class Player extends MovingEntity implements AtackingEntity, SpriteFinishReceiver, OwnsCollider, EntityWithStats{

	
	private Collider colls;
	private EnergyModule energy_mod;
	private HPModule hp_mod;
	private DMGModule dmg_mod;
	
	public final int MAXENERGY= 1000, MAXHP=100, DMG = 20;
	private final int IMMUNETIME = 25;
	
	private int low_hp;
	private boolean low = false;
	private boolean dead = false;
	private ChangeLevel changeLevel;
	private Weapon weapon;
	private String prevState = "normal";
		
	public Player(int x, int y,int w, int h, Movement mov, Level level, Rectangle tileOffs) {
		super(x, y,w,h,level, mov);
	    spriteOffsets = tileOffs;// siempre asignarlas antes de inicializar mov!!
		
		
	    Sprite currentAnimFront = new Animator(WIDTH, HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoFront")), 15,false);
	    Sprite currentAnim = new SingleSprite(WIDTH, HEIGHT, 0, 0, new Spritesheet(level.AM.getImage("minoFront")));
	    Sprite currentAnimBack = new Animator(WIDTH, HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoBack")), 15,false);
	    Sprite currentAnimIzq = new Animator(WIDTH, HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoPerfilIzq")), 15,false);
		Sprite currentAnimDer = new Animator(WIDTH, HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoPerfilDer")), 15,false);
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnimBack); 
		normalState.addSprite("1", currentAnimBack); 
		normalState.addSprite("2", currentAnimDer); 
		normalState.addSprite("3", currentAnimFront); 
		normalState.addSprite("4", currentAnimFront); 
		normalState.addSprite("5", currentAnimFront); 
		normalState.addSprite("6", currentAnimIzq); 
		normalState.addSprite("7", currentAnimBack); 
		normalState.addSprite("8", currentAnim); 
		
		msm.add("normal", new NormalMove(normalState, false));
		msm.change("normal", "", false);
		
		Animator deadAnim = new Animator(54,48, 0, 0, 6, new Spritesheet(level.AM.getImage("minoDead")), 15,true);
		deadAnim.addNotifictionReceiver(this, "dead");
		msm.add("dead", new NoMove(deadAnim));
		
		Animator changeZoneAnim = new Animator(WIDTH,HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoDisolver")), 15,true);
		changeZoneAnim.addNotifictionReceiver(this, "disolve");
		msm.add("disolve", new NoMove(changeZoneAnim));
		
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		colls = new Collider(this,getCollider(),level);//siempre en este orden (o no usar new, simplemente modificar width y heght del rect anterior)
	
		
		collider.setLocation((int)(this.x+colliderOffsets.getX()), (int)(this.y+colliderOffsets.getY()));

		hp_mod = new HPModule(MAXHP, MAXHP, IMMUNETIME);
		energy_mod = new EnergyModule(MAXENERGY, MAXENERGY);
		dmg_mod = new DMGModule(DMG);
		
		msm.unBlock();
		

		Sword sword = new Sword(this,"ataque");
		setWeapon(sword);

		
		if(weapon.customSprite())msm.add(sword.getType(),sword.getVisualMovement());
		
		
		low_hp = MAXHP/5;
	}

	
	@Override
	public void update() {
		if(GameInput.getSingleton().inputPressed(4)&&!dead){
			attack();
		}

		hp_mod.update();
		
		weapon.update();
		msm.update();
		mov.update();
		
		addEnergy(1);
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		updateCollider();
		colls.checkCollisions();
		
		
		 //comprobar que si la vida llega a 0, el player est� muerto
		assertFalse((hp_mod.getHP()==0)&&(hp_mod.isAlive()));

		//comprobar que la energia no baja de 0 ni sobrepasa 1000
		assertFalse((energy_mod.getEnergy()<0)&&(energy_mod.getEnergy()>1000));
		
		if(!isAlive()){
			die();
		}
		if(getHP()<=low_hp&&low==false){
			low = true;
			AssetManager.getSingleton().stop("lowhp");
			AssetManager.getSingleton().playSound("lowhp",0);
			SoundManager.getSingleton().loop("lowhp",Clip.LOOP_CONTINUOUSLY);
			
		}else if(getHP()>low_hp){
			low = false;
			SoundManager.getSingleton().stop("lowhp");
		}
	}

	@Override
	public void render(Rendering render) {
		
		
		Sprite cur = msm.getSprite();
		if(hp_mod.isImmune()){
			render.renderEntityColored(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur,0xDD0000); 
		}
		else render.renderEntity(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur);
		
		
		weapon.render(render);
		debug(render);
	}
	
	public void move(int movX,int movY){
		
		msm.move(movX, movY);
		
		movX = msm.getMovX();
		movY = msm.getMovY();
		//  H=24   W=30
		// x/y=0 arriba izquierda   x=60 y=64
		x+=movX;
		y+=movY;
		

		
		/*		movimiento:
		 * 			derecha (0+,0)
		 * 			izquierda (0-,0)
		 * 			arriba(0,0-)
		 * 			abajo(0,0+)
		 */
	}

	

	public boolean isCentered(){
		
		if(x<0+level.screenW/2)return false;
		if(x>((level.getWidth()*TS)-(level.screenW/2)))return false;
		if(y<0+level.screenH/2)return false;
		if(y>((level.getHeight()*TS)-(level.screenH/2)))return false;
		
		return true;
	}


	@Override
	public boolean collidesWithState(int s) {

		return (s==1)||(s==9); //colision con *solid* y con  *void*
	}

	public int getHP(){
		return hp_mod.getHP();
	}
	public void heal(int hp){
		hp_mod.setHP(getHP()+hp);
		
		//asegurar que el player no obtiene de un coraz�n m�s del maximo de su vida
		assertFalse(hp_mod.getHP()>MAXHP);
	}
	public int substractEnergy(int x){
		return energy_mod.substractEnergy(x);
	}
	public int addEnergy(int x){
		return energy_mod.addEnergy(x);
	}

	public boolean isAlive(){
		return hp_mod.isAlive();
	}
	
	private void die(){
		mov.stop(-1);
		msm.change("dead", "", true);
		SoundManager.getSingleton().stop("lowhp");
		dead = true;
	}

	public int getEnergy() {
		return energy_mod.getEnergy();
	}

	public void changeZone(String targetLevel, int spXNL, int spYNL){
		msm.change("disolve", "", true);
		changeLevel = new ChangeLevel(targetLevel, spXNL, spYNL);
		
	}
	
	
	private class ChangeLevel {
		String targetLevel;
		int spawnX, spawnY;
		public ChangeLevel(String tl, int sx, int sy){
			targetLevel = tl;
			spawnX = sx;
			spawnY = sy;
		}
	}


	@Override
	public void attack() {
		if(weapon.canAttack()){
			weapon.attack(dir);
			if(weapon.customSprite()){
				prevState  = msm.getCurrentStateName();
				msm.change(weapon.getType(), "", true);
			}
		}
	}

	@Override
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	
	@Override
	public void spriteFinished(String id) {
		switch(id){
		case "dead": finishGame(); break;
		case "disolve": finishDisolve(); break;
		case "ataque": finishAttack(); break;
		}
	}
	
	private void finishGame(){
		msm.unBlock();
		GameMaster.getSingleton().resetEnemyCounter();
		level.died();
	}
	
	private void finishDisolve() {
		level.parent.changeLevel(changeLevel.targetLevel, changeLevel.spawnX, changeLevel.spawnY);
		GameMaster.getSingleton().resetEnemyCounter();
		msm.unBlock();
	}
	
	private void finishAttack() {
		weapon.stopAttack();
		msm.unBlock();
		msm.change(prevState, "", false);
	}


	@Override
	public void collide(Entity e) {
		
		if(this.equals(e)||dead){
			return;
		}

	
		if(e instanceof EntityActionable){
			((EntityActionable)e).action(this);
		}
		
		if(e instanceof DamagingEntity){
			GameMaster.getSingleton().performAttack((DamagingEntity)e, this, e);
		}
			
	}


	@Override
	public void receiveDmg(int dmg, Entity e) {
		if(!hp_mod.isImmune()&&changeLevel==null){
			//AssetManager.getSingleton().stop("hurt");
			//AssetManager.getSingleton().playSound("hurt",0);
			push(new Vector2D(e.getX(), e.getY()), 5);
			hp_mod.hit(dmg);
		}
	}


	@Override
	public int getDmg() {
		return dmg_mod.getDMG();
	}
	
	public ArrayList<Module> getModules(){
		ArrayList<Module> res = new ArrayList<Module>();
		res.add(hp_mod);
		res.add(energy_mod);
		res.add(dmg_mod);
		return res;
	}
	public void loadModules(ArrayList<Module> r){
		hp_mod = (HPModule)r.get(0);
		energy_mod = (EnergyModule)r.get(1);
		dmg_mod = (DMGModule)r.get(2);
	}

}
