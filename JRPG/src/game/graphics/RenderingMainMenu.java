package game.graphics;

public class RenderingMainMenu extends Rendering{

	public RenderingMainMenu(int width, int height) {
		super(width, height);
	}
	
	public void renderImage(int xp, int yp, Image sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
		
		int spriteHeight = sp.getHeight();
		int spriteWidth = sp.getWidth();
		
		for(int y=0;y<spriteHeight;y++){// (x,y) es la posicion del pixel respecto al sprite
			int ya = (yp+y); // (xa,ya) posicion del pixel al que hay que pintar en la pantalla
			for(int x=0;x<spriteWidth;x++){
				int xa = (xp+x);
				
				
				if(xa >= width || xa<0 || ya >= height || ya<0){
					continue;
				}

				pixels[xa+ya*width]= sp.pixels[x+y*spriteWidth];
			}
		}
	}
}
