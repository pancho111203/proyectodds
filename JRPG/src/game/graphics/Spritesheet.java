package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private String path;
	public int WIDTH;
	public int HEIGHT;
	public int[] pixels;
	public int indexSize;
	
	public static Spritesheet tiles = new Spritesheet("/spritesheet.png",16);
	
	public Spritesheet(String path, int ind){
		this.path=path;
		indexSize=ind;
		
		load();
	}
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			WIDTH= image.getWidth();
			HEIGHT=image.getHeight();
			pixels = new int[WIDTH*HEIGHT];
			image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}