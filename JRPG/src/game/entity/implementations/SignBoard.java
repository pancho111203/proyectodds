package game.entity.implementations;

import game.entity.Entity;
import game.entity.types.EntityActionableImmuneToPause;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.GameInput;
import game.level.Level;
import game.level.tiles.Tile;

public class SignBoard extends Entity implements EntityActionableImmuneToPause{

	private String lines[];
	private Sprite disp;
	private boolean cartelActivo = false;
	
	public SignBoard(int x, int y, Level level, String[] lines) {
		super(x-x%Level.TILESIZE, y-y%Level.TILESIZE, 32, 32, level); // al ser una entity solida tiene que estar alineada con los tiles
		
		System.out.println(x+" "+y);
		
		Tile t1 = level.getTile(x/Level.TILESIZE, y/Level.TILESIZE).clone();
		t1.setAllStates(1);
		level.setTile(x/Level.TILESIZE, y/Level.TILESIZE, t1);
		
		Tile t2 = level.getTile(x/Level.TILESIZE+1, y/Level.TILESIZE).clone();
		t2.setAllStates(1);
		level.setTile(x/Level.TILESIZE+1, y/Level.TILESIZE, t2);
		
		Tile t3 = level.getTile(x/Level.TILESIZE, y/Level.TILESIZE+1).clone();
		t3.setAllStates(1);
		level.setTile(x/Level.TILESIZE, y/Level.TILESIZE+1, t3);
		
		Tile t4 = level.getTile(x/Level.TILESIZE+1, y/Level.TILESIZE+1).clone();
		t4.setAllStates(1);
		level.setTile(x/Level.TILESIZE+1, y/Level.TILESIZE+1, t4);
		
		
		
		this.lines = lines;
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		
		disp = new SingleSprite(32, 1, 0, new Spritesheet(level.AM.getImage("signboard")));
	}

	@Override
	public void action(Player e) {
		if(GameInput.getSingleton().inputPressed(GameInput.getSingleton().ACTION)){
			System.out.println("LEER CARTEL");
			if(cartelActivo){
				desactivarCartel();
			}else{
				activarCartel();
			}
//			protected Image gameover;
//			gameover = new Image(AM.getImage("gameover"));
//			if(!player.isAlive())render.renderImage(25, 50, gameover);
		}

	}
	
	private void activarCartel(){
		cartelActivo = true;
		level.pause();
	}
	
	private void desactivarCartel(){
		cartelActivo = false;
		level.unpause();
		
	}

	@Override
	public void update() {
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
	}

	@Override
	public void render(RenderingLevel render) {
		render.renderEntity(xInScreen,yInScreen,disp);
		debug(render);
	}

}
