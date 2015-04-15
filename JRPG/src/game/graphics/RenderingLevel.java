package game.graphics;
import java.util.Random;

public class RenderingLevel extends Rendering{

	private int worldHeight=64;
	private int worldWidth=64;
	
	public int[] worldTiles;
	
	private Random random = new Random();
	private int tileSize = 32;
	
	public RenderingLevel(int w, int h) {
		super(w, h);
		worldTiles = new int[worldWidth*worldHeight];
	}

	public void randomLevel() {

		for(int i=0;i<worldTiles.length;i++){
			worldTiles[i] = random.nextInt(10000000);
		}
		
	}
	
	
	// NO FUNCIONA BIEN TODAVIA, SEGUIR TESTEANDO
	public void renderTiles(int offsetX, int offsetY){
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				int xn = (x+offsetX)/tileSize; 
				int yn = (y+offsetY)/tileSize; // estos valores posicionan el tile en el que se encuentra el pixel actual
				if(xn<0||xn>=worldWidth||yn<0||yn>=worldHeight){continue;}
				pixels[x+y*width]=worldTiles[xn+yn*width];
				
				
			}
		}
	}

}
