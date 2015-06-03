package game.entity.implementations;

import game.entity.AtackingEntity;
import game.entity.Entity;
import game.entity.MovingEntity;
import game.entity.SpriteContainer;
import game.entity.SpriteFinishReceiver;
import game.entity.collision.Collider;
import game.entity.movement.Movement;
import game.entity.movestate.NoMove;
import game.entity.movestate.NormalMovePlayer;
import game.entity.weapons.Sword;
import game.entity.weapons.Weapon;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.GameInput;
import game.level.Level;

import java.awt.Rectangle;

import auxiliar.Vector2D;

public class Player extends MovingEntity implements AtackingEntity, SpriteFinishReceiver{

	
	private boolean dead = false, attacking = false;
	private Collider colls;
	int delta=-1;
	boolean red=false;
	private Stats stats;
	
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
		//currentAnim = new Sprite(16,16,0,2,Spritesheet.tiles);
		SpriteContainer normalState = new SpriteContainer();
		normalState.addSprite("0", currentAnimBack); // hay que añadir un sprite para cada direccion (obligatorio para que la statemachine funcione)
		normalState.addSprite("1", currentAnimDer); // despues puedo anadir los sprites que sean necesarios para cada estado diferente
		normalState.addSprite("2", currentAnimDer);
		normalState.addSprite("3", currentAnimDer);
		normalState.addSprite("4", currentAnimFront);
		normalState.addSprite("5", currentAnimIzq);
		normalState.addSprite("6", currentAnimIzq);
		normalState.addSprite("7", currentAnimIzq);
		normalState.addSprite("8", currentAnim);
		msm.add("normal", new NormalMovePlayer(normalState));
		msm.change("normal", "", false);
		
		Animator deadAnim = new Animator(54,48, 0, 0, 6, new Spritesheet(level.AM.getImage("minoDead")), 15,true);
		deadAnim.addNotifictionReceiver(this, "dead");
		msm.add("dead", new NoMove(deadAnim));
		
		Animator changeZoneAnim = new Animator(WIDTH,HEIGHT, 0, 0, 4, new Spritesheet(level.AM.getImage("minoDisolver")), 15,true);
		changeZoneAnim.addNotifictionReceiver(this, "disolve");
		msm.add("disolve", new NoMove(changeZoneAnim));
		
		
		colls = new Collider(this.x,this.y,w,h,this);
	
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		collider.setLocation((int)(this.x+colliderOffsets.getX()), (int)(this.y+colliderOffsets.getY()));

		stats = new Stats();
		stats.setHP(100);
		
		
		msm.unBlock();
		
		
		Sword sword = new Sword(this, "ataque");
		msm.add(sword.getType(),sword.getVisualMovement());
		
		
		//TODO empezar con "Unarmed"
		setWeapon(sword);
		
	}

	
	@Override
	public void update() {
		
		//TEST es temporal para probar ataque
		if(GameInput.getSingleton().inputPressed(4)){
			attack();
		}
		
		
		if(red&&!dead){
			if(delta<=20){
				delta++;
			}else{
				red=false;
				delta=-1;
			}
		}
		
		weapon.update();
		msm.update();
		mov.update();
		
		addEnergy(1);
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		updateCollider();
		colls.checkCollisions();
		
		if(!isAlive()){
			die();
		}
	}

	@Override
	public void render(RenderingLevel render) {
		if(red){
			render.renderEntityColored(xInScreen,yInScreen,msm.getSprite(),0xDD0000); 
		}
		else render.renderEntity(xInScreen,yInScreen,msm.getSprite());
		
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

		return s==1; //colision con *solid*
	}


	@Override
	public void collide(Entity e) {
	}

	public void takeDamage(int d, int ex, int ey){
		if(red==false&&changeLevel==null){
			push(new Vector2D(ex, ey), 20);
			stats.hit(d);
		}
		red = true;
	}
	
	public int getHP(){
		return stats.getHP();
	}
	
	public int substractEnergy(int x){
		return stats.substractEnergy(x);
	}
	public int addEnergy(int x){
		return stats.addEnergy(x);
	}

	public boolean isAlive(){
		return stats.isAlive();
	}
	
	private void die(){
		mov.stop(-1);
		msm.change("dead", "", true);
		dead = true;
		red = false;
		
	}
	
	private void finishGame(){
		
		msm.unBlock();
		level.finish();
		
	}


	public int getEnergy() {
		return stats.getEnergy();
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
		if(!attacking){
			weapon.attack();
			attacking = true;
			prevState  = msm.getCurrentStateName();
			msm.change(weapon.getType(), "", true);
		}
	}


	private void finishAttack() {
		attacking = false;
		weapon.stopAttack();
		msm.unBlock();
		msm.change(prevState, "", false);
	}


	@Override
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	
	@Override
	public void spriteFinished(String id) {
		switch(id){
		case "dead": finishGame(); break;
		case "disolve": level.parent.changeLevel(changeLevel.targetLevel, changeLevel.spawnX, changeLevel.spawnY);
						msm.unBlock();
						break;
		case "ataque": finishAttack(); break;
		}
	}

	
}
