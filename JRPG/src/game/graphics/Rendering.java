package game.graphics;

public abstract class Rendering {
	protected int width, height;
	protected int pixels[];
	
	public Rendering(int width,int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	

	//set all the pixels to 0
	public void clear() {
		
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0;
		}
		
	}
	public int[] getPixels(){
		return pixels;
	}
}
