package game.graphics;

import game.level.Level;
import game.level.tiles.Tile;


public class RenderingLevel extends Rendering{

	public RenderingLevel(int w, int h) {
		super(w, h);
	}

		
	public void renderTile(int xp, int yp, int xRest, int yRest, Tile tile){ // (xp,yp) es la posicion del tile respecto a la pantalla 
		//(xRest,yRest) respresentan el desplazamiento del sprite. el valor maximo de este desplazamiento
		// es 31(el tamano del tile-1). el desplazamiento sera hacia la izquierda con respecto a la pantalla
		
		SingleSprite sprite = tile.getSprite();
		
		int size = Level.TILESIZE;
		
		int yPixel = yp * size; 
		int xPixel = xp * size; // transformamos a medida pixel
		
		for(int y=0;y<size;y++){// (x,y) es la posicion del pixel respecto al sprite
			int ya = (yPixel+y)-yRest; // (xa,ya) posicion del pixel al que hay que pintar en la pantalla
			for(int x=0;x<size;x++){
				int xa = (xPixel+x)-xRest;
				
				
				if(xa >= width || xa<0 || ya >= height || ya<0){
					continue;
				}

				pixels[xa+ya*width]= sprite.pixels[x+y*size];
			}
		}
	}
	
	public void renderEntity(int xp, int yp, Sprite sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
		SingleSprite sprite = sp.getActual();
		
		int spriteHeight = sprite.getHeight();
		int spriteWidth = sprite.getWidth();
		
		for(int y=0;y<spriteHeight;y++){// (x,y) es la posicion del pixel respecto al sprite
			int ya = (yp+y); // (xa,ya) posicion del pixel al que hay que pintar en la pantalla
			for(int x=0;x<spriteWidth;x++){
				int xa = (xp+x);
				
				
				if(xa >= width || xa<0 || ya >= height || ya<0){
					continue;
				}

				pixels[xa+ya*width]= sprite.pixels[x+y*spriteWidth];
			}
		}
	}

}
