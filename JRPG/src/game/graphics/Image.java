package game.graphics;

import game.states.MainMenuState;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	int x,y,w,h;
	public int[] pixels;
	
	public Image(String img) {
			try {
				BufferedImage image = ImageIO.read(MainMenuState.class.getResource(img));
				w=image.getWidth();
				h=image.getHeight();
				pixels = new int[w*h];
				image.getRGB(0, 0, w, h, pixels, 0, w);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public int getHeight(){
		return h;
	}
	
	public int getWidth(){
		return w;
	}

}
