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
    public final int SIZE;
	protected Spritesheet sheet;
	
	
	public Sprite (int size, int x, int y, Spritesheet sheet){
		this.SIZE = size;
		this.x= x*size;
		this.y= y*size;
		this.sheet=sheet;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	public Sprite(int size, int colour){
		SIZE=size;
		pixels = new int[SIZE*SIZE];
		
		for(int i=0;i<SIZE*SIZE;i++){
			pixels[i]=colour;
		}
	}
	
	public void load(){
			for(int y=0;y<SIZE;y++){
				for(int x=0;x<SIZE;x++){
					pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.WIDTH];
				}
			}
		
	}
}
