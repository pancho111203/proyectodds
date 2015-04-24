package game.level;

import game.entity.EntityList;
import game.entity.Player;
import game.graphics.Animator;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.level.tiles.Tile;

import java.util.HashMap;

public abstract class Level {
	private EntityList entList;
	
	public static final int TILESIZE = 16;
	
	protected final int START_POS_X; // posiciones iniciales de la camara
	protected final int START_POS_Y;
	
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	public int screenW;

	public int screenH;
	
	protected Player player;
	
	protected HashMap<String,SingleSprite> iniSprites;
	protected HashMap<String,Animator> iniAnimSprites; //TODO
	protected HashMap<String,Tile> iniTiles;
	
	
	public Level(int stX, int stY, int w, int h){
		START_POS_X = stX;
		START_POS_Y = stY;
		entList = new EntityList();
		xOffset = START_POS_X;
		yOffset = START_POS_Y;
		
		screenW = w;
		screenH = h;
		
		iniTiles = new HashMap<String, Tile>();
		iniSprites = new HashMap<String, SingleSprite>();
		
		
		player = new Player(xOffset,yOffset,this);
		
		initializeSpritesAndTiles();
	}
	public void update(){
		entList.update();
		player.update();
	}
	public void render(RenderingLevel render){
		renderTiles(render);
		
		entList.render(render);
		player.render(render);
	}
	
	public abstract void loadLevel();
	
	public abstract void initializeSpritesAndTiles();
	
	//TODO cambiar el movimiento al player, el cual llama desde su metodo mover a moveFocus
	// tambien falta implementar una funcionalidad en moveFocus: checkea si es posible mover el focus de la camara para evitar
	// que se salga de el nivel - si el check da false, la camara no se mueve, solo el player
	
	
	public void moveFocus(int offX,int offY){ 
		xOffset += offX;
		yOffset += offY;
		
	}
	
	public abstract Tile getTile(int x, int y);
	
	public int getXPosScreen(){
		return xOffset;
	}
	public int getYPosScreen(){
		return yOffset;
	}
	
	
	protected void renderTiles(RenderingLevel render) {
		int tsize = Level.TILESIZE;
		
		int xRest = xOffset%tsize;
		int yRest = yOffset%tsize;
		
		int xOffTile = xOffset/tsize;
		int yOffTile = yOffset/tsize;// (xOffTile,yOffTile) es el desplazamiento de la camara con tile como medida
		
		int h = (render.height/tsize)+yOffTile+1; // posicion del tile maximo que hay que dibujar(puede ser uno menos)
		int w = (render.width/tsize)+xOffTile+1;
				
		
		if(xRest == 0)w--; // si la posicion del tile cuadra con la pantalla, hay que pintar uno menos
		if(yRest == 0)h--;
		
		for(int y=yOffTile;y<h;y++){
			for(int x=xOffTile;x<w;x++){
				getTile(x,y).render(x-xOffTile,y-yOffTile,xRest,yRest,render);
			}
		}
		
		
	}
}