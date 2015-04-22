package game.graphics;

import game.level.Level;
import game.level.tiles.Tile;


public class RenderingLevel extends Rendering{

	public RenderingLevel(int w, int h) {
		super(w, h);
	}

	//xOffset e yOffset represetan l desplazamiento de los tiles
	public void renderByColors(int[] tiles, int tileSize, int xOffset, int yOffset){ 
		int xn, yn;
		
		// TODO Falta corregir errores outOfBounds cuando el desplazamiento mueve la camara mas alla del array de tiles
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				
				xn = (x+xOffset)/tileSize;
				yn = (y+yOffset)/tileSize;
				
				pixels[x+y*width]=tiles[xn+yn*width];
				
				
			}
		}
	}
	
	
	public void renderTile(int xp, int yp, int xRest, int yRest, Tile tile){ // (xp,yp) es la posicion del tile respecto a la pantalla 
		//(xRest,yRest) respresentan el desplazamiento del sprite. el valor maximo de este desplazamiento
		// es 31(el tamano del tile-1). el desplazamiento sera hacia la izquierda con respecto a la pantalla
		
		Sprite sprite = tile.sprite;
		
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
	
	//TODO renderEntity
//	public void renderEntity(int xp, int yp, Sprite sprite){ // (xp,yp) es la posicion del sprite respecto al mundo 
//		yp = yp * sprite.HEIGHT;
//		xp = xp * sprite.WIDTH; // transformamos a medida pixel
//		
//		for(int y=0;y<sprite.HEIGHT;y++){// (x,y) es la posicion del pixel respecto al sprite
//			int ya = y + yp;   				//(xa,ya) es la posicion del pixel respecto al mundo(en medida pixel)
//			for(int x=0;x<sprite.WIDTH;x++){
//				int xa = x + xp;
//				
//				if(xa < -tile.sprite.SIZE || xa >= width || ya < -tile.sprite.SIZE || ya >= height){break;}
//				if(xa < 0){xa=0;}
//				if(ya < 0){ya=0;}
//				pixels[xa+ya*width]= tile.sprite.pixels[x+y*tile.sprite.SIZE];
//			}
//		}
//	}

}
