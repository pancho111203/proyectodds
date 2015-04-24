package game.graphics;

import game.level.Level;



public class Sprite {
	
	//TODO buscar la manera de resolver errores introduciendo un tile de tamaño insadecuado
	//usar Level.TILESIZE para todos los sprites que vayan a ser usados por un tile
	//TEST sprite
	public static Sprite verde=new Sprite(Level.TILESIZE,0x00ff00);
	public static Sprite negro=new Sprite(Level.TILESIZE,0x000000);
	public static Sprite blanco=new Sprite(Level.TILESIZE,0xffffff);
	public static Sprite rojo=new Sprite(Level.TILESIZE,1,1,Spritesheet.tiles);
	//
	
	
	public int[] pixels;
	public int x, y;
	public int width, height;
	protected Spritesheet sheet;
	
	
	public Sprite (int size, int x, int y, Spritesheet sheet){
		width = height = size;
		this.x= x*sheet.indexSize;
		this.y= y*sheet.indexSize;
		this.sheet=sheet;
		pixels = new int[height*width];
		load();
	}
	public Sprite (int w, int h, int x, int y, Spritesheet sheet){
		width = w;
		height = h;
		this.x= x*sheet.indexSize;
		this.y= y*sheet.indexSize;
		this.sheet=sheet;
		pixels = new int[height*width];
		load();
	}
	
	public Sprite(int size, int colour){
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
}
