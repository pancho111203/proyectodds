package game.entity;

import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;
import game.level.Level;

public class Marbao extends Entity {
	
	private boolean direc;
	private int fx,fy;
	private final int w=64,h=64;
	private Level level;
	
	Animator currAnim;
	
	public Marbao(int x, int y,Level level) {
		super(x, y);
		this.fx=x;
		this.fy=y;
		this.level=level;
		direc=false;
		
	    currAnim = new Animator(64,64, 0, 0, 4, new Spritesheet(level.AM.getImage("Caballitomarbao")), 30);
	}

	@Override
	public void update() {
		if(currAnim instanceof Animator){
			((Animator)currAnim).update();
		} 
		ia();
		
	}

	@Override
	public void render(RenderingLevel render) {
		int xInScreen = x-level.getXPosScreen();
		int yInScreen = y-level.getYPosScreen();

		render.renderEntity(xInScreen,yInScreen,currAnim);
	}

	public void ia(){
		if(x<fx+50&&!direc){
			x++;
		}else if(x>fx-50&&direc){
			x--;
		}else{
			direc=!direc;
		}
		
	}
	
}
