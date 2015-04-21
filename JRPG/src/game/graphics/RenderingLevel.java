package game.graphics;

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

}
