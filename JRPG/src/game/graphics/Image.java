package game.graphics;

import game.states.MenuState;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image implements Sprite{
	
	private int x,y,w,h;
	public int[] pixels;
	private int xOff=0, yOff=0;
	
	public Image(String img) {
			try {
				BufferedImage image = ImageIO.read(MenuState.class.getResource(img));
				w=image.getWidth();
				h=image.getHeight();
				pixels = new int[w*h];
				image.getRGB(0, 0, w, h, pixels, 0, w);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public Image(BufferedImage img) {
		try {
			BufferedImage image = img;
			w=image.getWidth();
			h=image.getHeight();
			pixels = new int[w*h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	public int getHeight(){
		return h;
	}
	
	public int getWidth(){
		return w;
	}

	@Override
	public SingleSprite getActual() {
		return null;
	}
	public SingleSprite getFirst() {
		return null;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public void setX(int nx) {
		x=nx;
	}

	public void setY(int ny) {
		y=ny;
	}

	@Override
	public void FlipAll() {
		
	}

	@Override
	public void startAgain() {
	}

	@Override
	public void addSprite(Sprite e) {
	}

	@Override
	public void removeSprite(Sprite e) {
	}

	@Override
	public Sprite getSprite(int i) {
		return null;
	}

	@Override
	public int getXOffset() {
		return xOff;
	}

	@Override
	public int getYOffset() {
		return yOff;
	}

	@Override
	public void setXOffset(int a) {
		xOff = a;
	}

	@Override
	public void setYOffset(int a) {
		yOff = a;
	}

}
