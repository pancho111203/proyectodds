package game.entity.implementations;

import game.entity.Entity;
import game.entity.types.DamagingEntity;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.Level;

import java.awt.Rectangle;
import java.util.Random;

// 32x88
public class Rayo extends Entity implements DamagingEntity{

	private Animator anim;
	private int rate = 15, sprites=7;
	private int duration = rate*(sprites+1);
	private int timeUntilDmg = rate*1; //se usa para que el rayo no haga daño hasta haber llegado al suelo
	private int waitTime;
	private int cont = 0, contWait = 0;
	private boolean active = false;
	private int dmg = 20;
	
	
	private Random random = new Random();
	
	
	public Rayo(Level level, int waitTime) {
		super(0, 0, 32, 88, level);
		
		this.waitTime = waitTime;
		anim = new Animator(WIDTH, HEIGHT, 0, 0, 7, new Spritesheet(level.AM.getImage("rayo")), 15, true);
		
		colliderOffsets = new Rectangle(0,60,WIDTH,HEIGHT);
		
		collider = new Rectangle((int)(colliderOffsets.getWidth()-colliderOffsets.getX()),(int)(colliderOffsets.getHeight()-colliderOffsets.getY()));
		
		reset();
	}

	@Override
	public void update() {
		
		if(!active){
			contWait++;
			if(contWait>waitTime){
				contWait=0;
				start();
			}
		}else{
			xInScreen = x-level.getXPosScreen();
			yInScreen = y-level.getYPosScreen();
			anim.update();
			cont++;
			if(cont>duration){
				cont = 0;
				reset();
			}
		}
		
	}

	private void start() {
		active = true;
		anim.startAgain();
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
	}

	private void reset() {
		active = false;
		x = random.nextInt(level.getWidth()*Level.TILESIZE);
		y = random.nextInt(level.getHeight()*Level.TILESIZE);
		collider.setLocation((int)(x+colliderOffsets.getX()), (int)(y+colliderOffsets.getY()));
	}

	@Override
	public void render(RenderingLevel render) {
		Sprite cur = anim.getActual();
		if(active){
			render.renderEntity(xInScreen+cur.getXOffset(),yInScreen+cur.getYOffset(),cur);
			debug(render);
		}
	}

	@Override
	public int getDmg() {
		return dmg;
	}

	@Override
	public void dealtDamage(int d) {
	}

	@Override
	public boolean isActive() {
		return (active&&cont>timeUntilDmg);
	}

}
