package game.graphics;

public abstract class Rendering {
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
}
