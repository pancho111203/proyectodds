package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private String path;
	private BufferedImage spriteSheet;
	public int WIDTH;
	public int HEIGHT;
	public int[] pixels;
	public final int indexSize= 16;
	
	
	
	//public static Spritesheet tiles = new Spritesheet("/images/spritesheet.png",16);
	public static Spritesheet test = new Spritesheet("/images/BloqueSprites01.png", 16);
	
	public Spritesheet(String path){
		this.path=path;
		this.spriteSheet=null;
		
		load();
	}
	
	public Spritesheet(BufferedImage img){
		this.path=null;
		this.spriteSheet=img;
		
		load();
	}
	
	private void load(){
		try {
			BufferedImage image;
			if(path==null){
				image = this.spriteSheet;
			} else{
				image = ImageIO.read(Spritesheet.class.getResource(path));
			}
			WIDTH= image.getWidth();
			HEIGHT=image.getHeight();
			pixels = new int[WIDTH*HEIGHT];
			image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}