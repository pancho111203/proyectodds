package game.graphics;

import game.level.Level;
import game.level.tiles.Tile;

public class Rendering {
	public int width, height;
	protected int pixels[];
	
	protected final int[] ALPHA= {0xffff1ef2, 0xffff00de, 0xff808080};
	
	public Rendering(int width,int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}

	//set all the pixels to 0
	public void clear() {
		
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0;
		}
		
	}
	public int[] getPixels(){
		return pixels;
	}
	
	//ahora ALPHA es una array simplifica render, y nos permite comprobar otras cosas si hiciera falta
	public boolean isALPHA(int pixel){
		
		for(int i=0;i<ALPHA.length;i++){
			if(pixel==ALPHA[i]){
				return true;
			}
		}
		
		return false;
	}
	
	public void render(int h, int w, int xp, int yp, int pixels[]){
		
		for(int y=0;y<h;y++){// (x,y) es la posicion del pixel respecto al sprite
			int ya = (yp+y); // (xa,ya) posicion del pixel al que hay que pintar en la pantalla
			for(int x=0;x<w;x++){
				int xa = (xp+x);
				
				
				if(xa >= width || xa<0 || ya >= height || ya<0){
					continue;
				}
				
				int pixel = pixels[x+y*w];
				if(!isALPHA(pixel))
				this.pixels[xa+ya*width]= pixel;
			}
		}
		
	}
	
	public void renderRect(int x, int y, int w, int h, int color){ 
		for(int i=0;i<w;i++){
			if(x+i < width && x+i > 0 ){
				if(y< height && y > 0)pixels[x+i+y*width] = color;
				if(y+h < height && y+h > 0)pixels[x+i+(y+h)*width] = color;
			}
		}
		for(int i=0;i<h;i++){
			if(y+i < height && y+i > 0 ){
				if(x < width && x > 0)pixels[x+(y+i)*width] = color;
				if(x+w<width && x+w >0)pixels[(x+w)+(y+i)*width] = color;
			}
		}
		
	}
	
	public void renderRectFilled(int x, int y, int w, int h, int color){ 
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				if(y+j< height && y+j > 0 && x+i < width && x+i > 0){
					pixels[x+i+(y+j)*width] = color;
				}
			}
			
		}
			
	}
	
	public void renderImage(int xp, int yp, Image sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)

		render(sp.getHeight(),sp.getWidth(),xp,yp,sp.pixels);
	
	}
	
	public void renderImageColored(int xp, int yp, Image sp,int color){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
	
		int pix[] = new int[sp.pixels.length];
		
		for(int i=0;i<pix.length;i++){
			
			if(!isALPHA(sp.pixels[i])){
			
				pix[i]=sp.pixels[i]&color;
			
			}else pix[i]=sp.pixels[i];
			
		}
		
		render(sp.getHeight(),sp.getWidth(),xp,yp,pix);
	}
		
	public void renderTile(int xp, int yp, int xRest, int yRest, Tile tile){ // (xp,yp) es la posicion del tile respecto a la pantalla 
		//(xRest,yRest) respresentan el desplazamiento del sprite. el valor maximo de este desplazamiento
		// es 31(el tamano del tile-1). el desplazamiento sera hacia la izquierda con respecto a la pantalla	
		int size = Level.TILESIZE;
		
		int yPixel = yp * size; 
		int xPixel = xp * size; // transformamos a medida pixel
		
		render(size,size,(xPixel-xRest),(yPixel-yRest),tile.getSprite().pixels);	
	}
	
	public void renderEntity(int xp, int yp, Sprite sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
		SingleSprite sprite = sp.getActual();
		
		render(sprite.getHeight(),sprite.getWidth(),xp,yp,sprite.pixels);
	}
	
	public void renderEntityColored(int xp, int yp, Sprite sp,int color){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
		SingleSprite sprite = sp.getActual();
		int pix[] = new int[sprite.pixels.length];
		
		for(int i=0;i<pix.length;i++){
			
			if(!isALPHA(sprite.pixels[i])){
			
				pix[i]=sprite.pixels[i]|color;
			
			}else pix[i]=sprite.pixels[i];
			
		}
		
		render(sprite.getHeight(),sprite.getWidth(),xp,yp,pix);
	}
	
	public void renderPart(int h, int w, int px, int py, int[] pixels, float p){
		int nw = ((int)(w*p));
		int aux[]= new int[nw*h];
		
		for(int y=0;y<h;y++){
			for(int x=0;x<nw;x++){
				aux[x+y*nw]=pixels[x+y*w];
			}
		}
		
		render(h,nw,px,py,aux);
	}
}
