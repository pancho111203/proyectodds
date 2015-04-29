package game.level;

import game.entity.EntityList;
import game.entity.Player;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.level.tiles.SpriteTileFacade;
import game.level.tiles.Tile;

public abstract class Level {
	private EntityList entList;
	
	public static final int TILESIZE = 16;
	
	protected final int START_POS_X; // posiciones iniciales de la camara
	protected final int START_POS_Y;
	
	protected Tile voidTile = new Tile(new SingleSprite(Level.TILESIZE, 0x000000));
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	public int screenW;

	public int screenH;
	
	protected Player player;

	protected SpriteTileFacade spr_t;
	
	//TODO separar sprites en background y foreground
	
	public Level(int stX, int stY, int w, int h){
		START_POS_X = stX;
		START_POS_Y = stY;
		entList = new EntityList();
		xOffset = START_POS_X;
		yOffset = START_POS_Y;
		
		screenW = w;
		screenH = h;
		
		spr_t = new SpriteTileFacade();
		
		
		player = new Player(xOffset,yOffset,this);
		
		initializeSpritesAndTiles();
	}
	public void update(){
		entList.update();
		player.update();
		spr_t.updateAnims(); //update de sprites animados
	}
	public void render(RenderingLevel render){
		renderTiles(render);
		
		entList.render(render);
		player.render(render);
	}
	
	public abstract void loadLevel();
	
	public abstract void initializeSpritesAndTiles();
	
	public void moveFocus(int offX,int offY){ 
		xOffset += offX;
		yOffset += offY;
		
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