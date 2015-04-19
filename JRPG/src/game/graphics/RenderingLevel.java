package game.graphics;

public class RenderingLevel extends Rendering{

	public RenderingLevel(int w, int h) {
		super(w, h);
	}

	
	// NO FUNCIONA BIEN TODAVIA, SEGUIR TESTEANDO
	public void renderByColors(int[] tiles, int tileSize){
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				pixels[x+y*width]=tiles[(x/tileSize)+(y/tileSize)*width];
				
				
			}
		}
	}

}
