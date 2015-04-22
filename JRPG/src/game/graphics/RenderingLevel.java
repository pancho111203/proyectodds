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
	
	
	
	
//	public void renderTile(int xp, int yp, Sprite sprite, int xOffset, int yOffset){
//		xp-= xOffset;
//		yp-= yOffset;
//		for(int y=0;y<tile.sprite.SIZE;y++){
//			int ya = y + yp;
//			for(int x=0;x<tile.sprite.SIZE;x++){
//				int xa = x + xp;
//				if(xa < -tile.sprite.SIZE || xa >= width || ya < -tile.sprite.SIZE || ya >= height){break;}
//				if(xa < 0){xa=0;}
//				if(ya < 0){ya=0;}
//				pixels[xa+ya*width]= tile.sprite.pixels[x+y*tile.sprite.SIZE];
//			}
//		}
//	}

}
