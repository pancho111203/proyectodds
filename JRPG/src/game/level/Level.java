package game.level;

import game.AssetManager;
import game.entity.Enemy;
import game.entity.Player;
import game.entity.list.EntityList;
import game.entity.movement.ForwardMovement;
import game.entity.movement.Movement;
import game.entity.movement.PlayerMovement;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.UserIface;
import game.level.tiles.SpriteTileFacade;
import game.level.tiles.Tile;

public abstract class Level {
	private EntityList entList;
	
	public static final int TILESIZE = 16;
	
	protected final int START_POS_X; // posiciones iniciales de la camara
	protected final int START_POS_Y;
	
	protected Tile voidTile = new Tile(new SingleSprite(Level.TILESIZE, 0x000000),9,9,9,9);
	
	public AssetManager AM;
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	
	public int screenW;
	public int screenH;
	
	protected Player player;
	protected UserIface UI;

	protected SpriteTileFacade spr_t;
		
	public Level(int stX, int stY, int w, int h){
		START_POS_X = stX;
		START_POS_Y = stY;
		
		AM=AssetManager.getSingleton();
		AM.load(this.getClass().getSimpleName()); 
		//TODO creo que ahora se puede acceder a am desde todo el juego y cargará todo pero me falta testear cuando cambia el lvl
		
		entList = new EntityList(this);
		entList.clearList();
		
		xOffset = START_POS_X;
		yOffset = START_POS_Y;
		
		screenW = w;
		screenH = h;
		
		spr_t = new SpriteTileFacade();
		
		int startpointPlayerX = xOffset + screenW/2;
		int startpointPlayerY = yOffset + screenH/2;
		

		Movement mov = new PlayerMovement(this, 1);
		player = new Player(startpointPlayerX,startpointPlayerY,2,3,mov,this, 8, 22, 36, 45);
		mov.initializeEntity(player);
		UI = new UserIface(player);
		entList.addPlayer(player);
	    //TEST probando lo diferentes tipos de movimiento de enemigos
		//TODO interfaz (patron Facade?) que haga estos 4 pasos llamando a un solo metodo, para simplificar
	    mov = new ForwardMovement(this, 1,1);
	    Enemy malo1 = new Enemy(startpointPlayerX,startpointPlayerY,4,4,mov,this,25,42,57,62); // hay que ajustar los offsets
		mov.initializeEntity(malo1);
		entList.addEntity(malo1);
		
		
//		mov = new PathMovement(this, -100,-100,1);
//	    Enemy malo2 = new Enemy(startpointPlayerX,startpointPlayerY,4,4,mov,this,25,28,57,62);
//		mov.initializeEntity(malo2);
//		entList.addEntity(malo2);
		//

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
}