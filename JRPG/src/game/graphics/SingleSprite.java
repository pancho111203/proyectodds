package game.graphics;




public class SingleSprite implements Sprite{

	public int[] pixels;
	private int x, y;
	protected int width, height;
	protected Spritesheet sheet;
	
	
	public SingleSprite (int size, int x, int y, Spritesheet sheet){
		width = height = size;
		this.x= x*width;
		this.y= y*height;
		this.sheet=sheet;
		pixels = new int[height*width];
		load();
	}
	public SingleSprite (int w, int h, int x, int y, Spritesheet sheet){
		width = w;
		height = h;
		this.x= x*width;
		this.y= y*height;
		this.sheet=sheet;
		pixels = new int[height*width];
		load();
	}
	
	public SingleSprite(int size, int colour){
		width = height = size;
		pixels = new int[height*height];
		
		for(int i=0;i<height*height;i++){
			pixels[i]=colour;
		}
	}
	
	public void load(){
			for(int y=0;y<height;y++){
				for(int x=0;x<width;x++){
					pixels[x+y*width]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.WIDTH];
				}
			}
		
	}
	
	@Override
	public SingleSprite getActual() {
		return this;
	}
	public SingleSprite getFirst() {
		return this;
	}
	
	public void Flip() {
		int l = pixels.length;
		
		int temp[] = new int[l];
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				temp[x +y*width]=pixels[ ((width-x)-1) +y*width];
			}
		}
		
		
		
		//for(int i=0;i<l;i++){
			//temp[i]=pixels[l-i-1];
		//}
		
		pixels = temp;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public void FlipAll() {
		Flip();
	}
	@Override
	public void startAgain() {
	}
}
