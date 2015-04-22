package game.graphics;

public class Sprite {
	
	public int[] pixels;
	public int x, y;
    public final int SIZE;
	private Spritesheet sheet;
	
	
	public Sprite (int size, int x, int y, Spritesheet sheet){
		this.SIZE = size;
		this.x= x*size;
		this.y= y*size;
		this.sheet=sheet;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	public void load(){
			for(int y=0;y<SIZE;y++){
				for(int x=0;x<SIZE;x++){
					pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.WIDTH];
				}
			}
		
	}
	//TODO sprite class
}
