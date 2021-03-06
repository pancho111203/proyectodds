package game.entity.implementations;

import game.GameStart;
import game.entity.Entity;
import game.entity.types.EntityActionable;
import game.graphics.Rendering;
import game.graphics.SingleSprite;
import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.input.GameInput;
import game.level.Level;
import game.level.tiles.Tile;
import game.states.pause.PausedState;
import game.states.pause.SignPause;

public class SignBoard extends Entity implements EntityActionable{
//TODO letras cartel scrollable
	private String content;
	private Sprite disp;
	private boolean cartelActivo = false;
	private Level level;
	private PausedState pausedState;
	
	public SignBoard(int x, int y, Level level, String content) {
		super(x-x%Level.TILESIZE, y-y%Level.TILESIZE, 32, 32, level); // al ser una entity solida tiene que estar alineada con los tiles
		
		this.level = level;
		
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
		
		
		
		this.content = content;
		
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
		
		pausedState = new SignPause(content);
		
		disp = new SingleSprite(32, 1, 0, new Spritesheet(level.AM.getImage("signboard")));
	}

	@Override
	public void action(Player e) {
		if(GameInput.getSingleton().inputPressed(GameInput.getSingleton().ACTION)){
			System.out.println("LEER CARTEL");
			if(GameStart.getSingleton().isPaused()){
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
		GameStart.getSingleton().pause(pausedState);
	}
	
	private void desactivarCartel(){
		cartelActivo = false;
		
	}

	@Override
	public void update() {
		xInScreen = x-level.getXPosScreen();
		yInScreen = y-level.getYPosScreen();
	}

	@Override
	public void render(Rendering render) {
		render.renderEntity(xInScreen,yInScreen,disp);
		debug(render);
	}

}
