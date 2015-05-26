package game.level;

import game.AssetManager;
import game.entity.implementations.Player;
import game.entity.list.EntityList;
import game.entity.movement.Movement;
import game.entity.movement.PlayerMovement;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.UserIface;
import game.level.tiles.SpriteTileFacade;
import game.level.tiles.Tile;
import game.states.LevelState;

import java.awt.Rectangle;

public abstract class Level {
	protected EntityList entList;
	
	public static final int TILESIZE = 16;
	
	protected Tile voidTile = new Tile(new SingleSprite(Level.TILESIZE, 0x000000),9,9,9,9);
	
	public AssetManager AM;
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	
	public int screenW;
	public int screenH;
	
	public LevelState parent;
	
	protected Player player;
	protected UserIface UI;

	protected SpriteTileFacade spr_t;
		
	public Level(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, LevelState l){
		
		parent = l;
		
		AM=AssetManager.getSingleton();
		AM.load(this.getClass().getSimpleName()); 
		//TODO creo que ahora se puede acceder a am desde todo el juego y cargará todo pero me falta testear cuando cambia el lvl
		
		entList = new EntityList(this);
		entList.clearList();
		
		screenW = w;
		screenH = h;
		
		spr_t = new SpriteTileFacade();

		Movement mov = new PlayerMovement(this, 1);
		Rectangle playerTileOffs = new Rectangle(8,36,23,45);
		player = new Player(spawnPosXPlayer,spawnPosYPlayer,2,3,mov,this, playerTileOffs);
		mov.initializeEntity(player);
		UI = new UserIface(player);
		entList.addPlayer(player);
		//TODO interfaz (patron Facade?) que haga estos 4 pasos llamando a un solo metodo, para simplificar
		
	    moveFocus();

		initializeSpritesAndTiles();
	}
	public void update(){
		entList.update();
		spr_t.updateAnims(); //update de sprites animados
		UI.update();
	}
	public void render(RenderingLevel render){
		renderTiles(render);
		
		entList.render(render);
		moveFocus();
		
		UI.render(render);
	}
	
	public abstract void loadLevel();
	
	public abstract void initializeSpritesAndTiles();
	
	public void moveFocus(){ 
		
		int px = player.getX();
		int py = player.getY();
			
		
		xOffset = px - screenW/2 + player.getWidth()/2;
		yOffset = py - screenH/2 + player.getHeight()/2;
		
		if(xOffset<0)xOffset=0;
		if(yOffset<0)yOffset=0;
		if(xOffset+screenW>width*TILESIZE)xOffset = width*TILESIZE-screenW;
		if(yOffset+screenH>height*TILESIZE)yOffset = height*TILESIZE-screenH;
	}
	
	public Tile getTile(int x, int y) {  // (x,y) 
		
		if(x < 0 || y < 0 || x >= width || y>= height){return voidTile;}
		
		Tile t = spr_t.getTile(tiles[(x+y*width)]);
		
		if(t!=null){
			return t;
		}
		
		return voidTile;
	}

	
	public int getXPosScreen(){
		return xOffset;
	}
	public int getYPosScreen(){
		return yOffset;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
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
	public EntityList getEntityList() {
		return entList;
	}
	public void finish() {
		parent.finish();
	}
}