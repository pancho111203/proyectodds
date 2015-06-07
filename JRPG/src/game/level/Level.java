package game.level;

import game.entity.Entity;
import game.entity.implementations.Enemy;
import game.entity.implementations.Player;
import game.entity.list.EntityList;
import game.entity.modules.Module;
import game.entity.movement.Movement;
import game.entity.movement.PlayerMovement;
import game.graphics.Image;
import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Spritesheet;
import game.graphics.UserIface;
import game.level.tiles.SpriteTileFacade;
import game.level.tiles.Tile;
import game.states.LevelState;

import java.awt.Rectangle;
import java.util.ArrayList;

import auxiliar.AssetManager;

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
	protected Image gameover;

	protected SpriteTileFacade spr_t;
		
	public Level(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, LevelState l){
		
		parent = l;
		
		AM=AssetManager.getSingleton();
		//AM.load("Level"); 
		//TODO creo que ahora se puede acceder a am desde todo el juego y cargar� todo pero me falta testear cuando cambia el lvl
		
		entList = new EntityList(this);
		entList.clearList();
		
		screenW = w;
		screenH = h;
		
		spr_t = new SpriteTileFacade();

		Movement mov = new PlayerMovement(this, 1);
		Rectangle playerCollider = new Rectangle(0, 5, 32, 45);
		Rectangle playerTileOffs = new Rectangle(8,36,23,45);
		player = new Player(spawnPosXPlayer,spawnPosYPlayer,32,48,mov,this, playerTileOffs);
		player.addCustomCollider(playerCollider);
		mov.initializeEntity(player);
		UI = new UserIface(player);
		entList.addPlayer(player);
		
		gameover = new Image(AM.getImage("gameover"));
		
	    moveFocus();

		initializeSpritesAndTiles();
	}
	
	public void loadPlayer(ArrayList<Module> r){
		player.loadModules(r);
	}
	public ArrayList<Module> getPlayerModules(){
		return player.getModules();
	}
	
	public void addEntityToList(Entity e){
		entList.addEntity(e);
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
		
		if(!player.isAlive())render.renderImage(25, 50, gameover);
	}
	
	public abstract void loadLevel();
	
	//public abstract void initializeSpritesAndTiles();
	public void initializeSpritesAndTiles() {
		Spritesheet ss = new Spritesheet(AM.getImage("BloqueSprites02"));
		//pilar
		int pilar = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pilar"+x+y, x, y, ss, 0xff00f6f0+pilar,1,1,1,1);
				pilar++;
			}
		}
		//pilar Oscuro
		int pilarOscuro = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pilarOscuro"+x+y, x+12, y, ss, 0xff309b10+pilarOscuro,1,1,1,1);
				pilarOscuro++;
			}
		}
		//suelo
		spr_t.initSpriteOnTileOnHex("suelo", 1, 4, ss, 0xffff7f27); 
		//suelo2
		spr_t.initSpriteOnTileOnHex("suelo2", 0, 4, ss, 0xffac1e6e); 
		//suelo oscuro
		spr_t.initSpriteOnTileOnHex("sueloOscuro", 14, 0, ss, 0xfff3e224);
		//pasillo
		spr_t.initSpriteOnTileOnHex("pasillo1", 0, 5, ss, 0xffff2745);
		spr_t.initSpriteOnTileOnHex("pasillo2", 1, 5, ss, 0xffff2746);
		//borde grande1
		spr_t.initSpriteOnTileOnHex("bordeGra1", 1, 3, ss, 0xfffd0691, 1, 1, 1, 1);
		//borde lateral derecha1
		spr_t.initSpriteOnTileOnHex("bordeDer1", 2, 2, ss, 0xfffff200, 1, 0, 1, 0);
		//borde lateral izquierda1
		spr_t.initSpriteOnTileOnHex("bordeIzq1", 3, 2, ss, 0xfffd0634, 0, 1, 0, 1);
		//borde grande2
		spr_t.initSpriteOnTileOnHex("bordeGra2", 14, 1, ss, 0xff13369b, 1, 1, 1, 1);
		//borde lateral derecha2
		spr_t.initSpriteOnTileOnHex("bordeDer2", 15, 0, ss, 0xff13369c, 1, 0, 1, 0);
		//borde lateral izquierda2
		spr_t.initSpriteOnTileOnHex("bordeIzq2", 15, 1, ss, 0xff13369d, 0, 1, 0, 1);
		//borde lateral derecha3
		spr_t.initSpriteOnTileOnHex("bordeDer3", 5, 8, ss, 0xff13369e, 1, 0, 1, 0);
		//borde lateral izquierda3
		spr_t.initSpriteOnTileOnHex("bordeIzq3", 6, 8, ss, 0xff13369f, 0, 1, 0, 1);
		//cielo
		spr_t.initSpriteOnTileOnHex("agua", 0, 3, ss, 0xffa349a4, 3,3,3,3);
		//puerta
		int puerta = 0;
		for(int y = 0;y<6;y++){
			for(int x=0;x<8;x++){
				spr_t.initSpriteOnTileOnHex("puerta"+x+y, 4+x, y, ss, 0xff28a661+puerta, 1, 1, 1, 1);
				puerta++;
			}
		}
		//pared
		int pared = 0;
		for(int y = 0;y<2;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pared"+x+y, 2+x, y, ss, 0xff06599c+pared, 1, 1, 1, 1);
				pared++;
			}
		}
		//sombreado
		//parte1
		int sombra = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("sombraP1"+x+y, x+2, y+3, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		
		//parte2
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("sombraP2"+x+y, x+12, y+3, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		//parte3
		for(int y = 0;y<2;y++){
			for(int x=0;x<14;x++){
				spr_t.initSpriteOnTileOnHex("sombraP3"+x+y, x, y+6, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		
		//puerta metal
		int p1 = 0;
		for(int y = 0;y<2;y++){
			for(int x=0;x<3;x++){
				spr_t.initSpriteOnTileOnHex("puerta_met_a"+y+x, 14+y, 2+x, ss, 0xff41ace0+p1, 1, 1, 1, 1);
				p1++;
			}
		}
		//escala
		for(int x = 0;x<5;x++){
		spr_t.initSpriteOnTileOnHex("escal"+x, x, 8, ss, 0xff77c650+x);
		}
	}
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
	
	public void setTile(int x, int y, int subst){
		tiles[(x+y*width)] = subst;
	}

	public void enemyDestroyed(Enemy e) {
	}

	public Entity getPlayer() {
		return player;
	}
	
}
