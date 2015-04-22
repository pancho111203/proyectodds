package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private String path;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public static Spritesheet tiles = new Spritesheet("/spritesheet.png",96,48);
	
	public Spritesheet(String path,int width,int height){
		this.path=path;
		WIDTH = width;
		HEIGHT= height;
		pixels = new int[WIDTH*HEIGHT];
		load();
	}
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			int w= image.getWidth();
			int h=image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}