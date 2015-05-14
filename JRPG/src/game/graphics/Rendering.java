package game.graphics;

public abstract class Rendering {
	public int width, height;
	protected int pixels[];
	
	private final int ALPHA2=0xffff1ef2;
	private final int ALPHA=0xffff00de;
	private final int ALPHA3=0xff808080;
	
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
	
	public void render(int h, int w, int xp, int yp, int pixels[]){
		
		for(int y=0;y<h;y++){// (x,y) es la posicion del pixel respecto al sprite
			int ya = (yp+y); // (xa,ya) posicion del pixel al que hay que pintar en la pantalla
			for(int x=0;x<w;x++){
				int xa = (xp+x);
				
				
				if(xa >= width || xa<0 || ya >= height || ya<0){
					continue;
				}
				int aux = pixels[x+y*w];
				if(aux!=ALPHA&&aux!=ALPHA2&&aux!=ALPHA3)
				this.pixels[xa+ya*width]= pixels[x+y*w];
			}
		}
		
	}
}
