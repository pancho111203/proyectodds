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
		int size = Level.TILESIZE;
		
		int yPixel = yp * size; 
		int xPixel = xp * size; // transformamos a medida pixel
		
		super.render(size,size,(xPixel-xRest),(yPixel-yRest),tile.getSprite().pixels);	
	}
	
	public void renderEntity(int xp, int yp, Sprite sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
		SingleSprite sprite = sp.getActual();
		
		super.render(sprite.getHeight(),sprite.getWidth(),xp,yp,sprite.pixels);
	}
	
	public void renderPart(int h, int w, int px, int py, int[] pixels, float p){
		int nw = ((int)(w*p));
		int aux[]= new int[nw*h];
		
		for(int y=0;y<h;y++){
			for(int x=0;x<nw;x++){
				aux[x+y*nw]=pixels[x+y*w];
			}
		}
		
		super.render(h,nw,px,py,aux);
	}

}
