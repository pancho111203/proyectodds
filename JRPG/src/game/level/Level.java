package game.level;

import game.entity.EntityList;
import game.graphics.RenderingLevel;
import game.input.Keyboard;
import game.level.tiles.Tile;

import java.awt.event.KeyEvent;

public abstract class Level {
	private EntityList entList;
	
	public static final int TILESIZE = 16;
	
	protected final int START_POS_X; // posiciones iniciales de la camara
	protected final int START_POS_Y;
	
	
	// enteros que representan la distancia x e y del punto central del nivel al punto actual donde la camara apunta
	protected int xOffset, yOffset; 
	
	protected int width, height;
	protected int[] tiles;
	
	
	//TEST Movimiento desde el nivel, mas adelante planeo hacerlo desde player
	private Keyboard key;
	private int speed = 1;
	//
	
	
	public Level(int stX, int stY){
		START_POS_X = stX;
		START_POS_Y = stY;
		entList = new EntityList();
		xOffset = START_POS_X;
		yOffset = START_POS_Y;
		
		key = Keyboard.getSingleton();
				
	}
	public void update(){
		//TEST mov
		if(key.keyDown(KeyEvent.VK_W)||key.keyDown(KeyEvent.VK_UP)){
			moveFocus(0, -speed);
		}
		if(key.keyDown(KeyEvent.VK_S)||key.keyDown(KeyEvent.VK_DOWN)){
			moveFocus(0, speed);
		}
		if(key.keyDown(KeyEvent.VK_A)||key.keyDown(KeyEvent.VK_LEFT)){
			moveFocus(-speed, 0);
		}
		if(key.keyDown(KeyEvent.VK_D)||key.keyDown(KeyEvent.VK_RIGHT)){
			moveFocus(speed, 0);
		}
		//
		entList.update();
	}
	public void render(RenderingLevel render){
		
		
		renderTiles(render);
		entList.render(render);
		
	}
	
	public abstract void loadLevel();
	
	public void moveFocus(int movX,int movY){ // movX y movY son el delta que se tiene que añadir a los offset correspondientes
		xOffset += movX;
		yOffset += movY;
		
		/*		movimiento:
		 * 			derecha (0+,0)
		 * 			izquierda (0-,0)
		 * 			arriba(0,0-)
		 * 			abajo(0,0+)
		 */
	}
	
	public abstract Tile getTile(int x, int y);
	
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