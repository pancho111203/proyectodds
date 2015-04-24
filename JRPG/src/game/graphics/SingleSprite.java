package game.graphics;




public class SingleSprite implements Sprite{

	public int[] pixels;
	public int x, y;
	protected int width, height;
	protected Spritesheet sheet;
	
	
	public SingleSprite (int size, int x, int y, Spritesheet sheet){
		width = height = size;
		this.x= x*sheet.indexSize;
		this.y= y*sheet.indexSize;
		this.sheet=sheet;
		pixels = new int[height*width];
		load();
	}
	public SingleSprite (int w, int h, int x, int y, Spritesheet sheet){
		width = w;
		height = h;
		this.x= x*sheet.indexSize;
		this.y= y*sheet.indexSize;
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
	@Override
	public int getHeight() {
		return height;
	}
	@Override
	public int getWidth() {
		return width;
	}
}
