package game.level;

import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.Spritesheet;
import game.level.tiles.StaticTile;
import game.level.tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FirstLevel extends Level {
	
	private String imgLevelPath;

	public FirstLevel(int startPosX, int startPosY, String pathImgToLvL, int w, int h){
		super(startPosX,startPosY,w,h);
		imgLevelPath = pathImgToLvL;
		loadLevel();
	}
	
	@Override
	public void loadLevel(){
		loadLevelFromImage(imgLevelPath);
	}
	
	
	public void loadLevelFromImage(String path){
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width=image.getWidth();
			height=image.getHeight();			
			tiles=new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width); // se pasa la imagen al array tiles en formato RGB
												// mas adelante el metodo getTile se encarga de mapear cada color a un tile
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error! No se ha podido cargar el nivel.");
		}
		
		
	}

	@Override
	public Tile getTile(int x, int y) {  // (x,y) 
		
		if(x < 0 || y < 0 || x >= width || y>= height){return iniTiles.get("voidTile");}
		
		
		//TEST probando mapping de pixel a tile
		if (tiles[(x+y*width)]==0xff22b14c){return iniTiles.get("rojo");}
		if (tiles[(x+y*width)]==0xff7f7f7f){return iniTiles.get("rojo");}
		if (tiles[(x+y*width)]==0xffb5e61d){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xffefe4b0){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xffff7f27){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xfffff200){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xff7092be){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xff00a2e8){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xffa349a4){return iniTiles.get("verde");}
		if (tiles[(x+y*width)]==0xff3f48cc){return iniTiles.get("blanco");}
		//	
		
		
		
		if (tiles[(x+y*width)]==0xff00f6ff){return iniTiles.get("a1");}
		if (tiles[(x+y*width)]==0xff00f6f1){return iniTiles.get("a2");}
		if (tiles[(x+y*width)]==0xff00f6f2){return iniTiles.get("a3");}
		if (tiles[(x+y*width)]==0xff00f6f3){return iniTiles.get("a4");}
		if (tiles[(x+y*width)]==0xff00f6f4){return iniTiles.get("a5");}
		if (tiles[(x+y*width)]==0xff00f6f5){return iniTiles.get("a6");}
		if (tiles[(x+y*width)]==0xff00f6f6){return iniTiles.get("a7");}
		if (tiles[(x+y*width)]==0xff00f6f7){return iniTiles.get("a8");}
		if (tiles[(x+y*width)]==0xff00f6f8){return iniTiles.get("a9");}
		if (tiles[(x+y*width)]==0xff00f6f9){return iniTiles.get("a11");}
		if (tiles[(x+y*width)]==0xff00f6fa){return iniTiles.get("a12");}
		if (tiles[(x+y*width)]==0xff00f6fb){return iniTiles.get("a13");}
		if (tiles[(x+y*width)]==0xff00f6fc){return iniTiles.get("a14");}
		if (tiles[(x+y*width)]==0xff00f6fd){return iniTiles.get("a15");}
		if (tiles[(x+y*width)]==0xff00f6fe){return iniTiles.get("a16");}
		if (tiles[(x+y*width)]==0xff00f700){return iniTiles.get("a17");}
		if (tiles[(x+y*width)]==0xff00f701){return iniTiles.get("a18");}
		if (tiles[(x+y*width)]==0xff00f702){return iniTiles.get("a19");}
		if (tiles[(x+y*width)]==0xff00f703){return iniTiles.get("a21");}
		if (tiles[(x+y*width)]==0xff00f704){return iniTiles.get("a22");}
		if (tiles[(x+y*width)]==0xff00f705){return iniTiles.get("a23");}
		if (tiles[(x+y*width)]==0xff00f706){return iniTiles.get("a24");}
		if (tiles[(x+y*width)]==0xff00f707){return iniTiles.get("a25");}
		if (tiles[(x+y*width)]==0xff00f708){return iniTiles.get("a26");}
		
		
		return iniTiles.get("voidTile");
	}
	
	//he añadido la función render aquí para renderizar cosas de este nivel y no directamente en la clase padre generica level (creo que es más limpio)
	public void render(RenderingLevel render){
		super.render(render);

		
		//SoundManager.getSingleton().play(0); 
		//SoundManager.getSingleton().play(1);  //recomiendo solo uno a la vez, era para comprobar que se pueden solapar varios
	}

	@Override
	public void initializeSpritesAndTiles() { //TODO herramienta para facilitar creacion de sprites y tiles
		iniSprites.put("a1", new SingleSprite(TILESIZE,0,0,Spritesheet.test));
		iniSprites.put("a2", new SingleSprite(TILESIZE,1,0,Spritesheet.test));
		iniSprites.put("a3", new SingleSprite(TILESIZE,2,0,Spritesheet.test));
		iniSprites.put("a4", new SingleSprite(TILESIZE,3,0,Spritesheet.test));
		iniSprites.put("a5", new SingleSprite(TILESIZE,0,1,Spritesheet.test));
		iniSprites.put("a6", new SingleSprite(TILESIZE,1,1,Spritesheet.test));
		iniSprites.put("a7", new SingleSprite(TILESIZE,2,1,Spritesheet.test));
		iniSprites.put("a8", new SingleSprite(TILESIZE,3,1,Spritesheet.test));
		iniSprites.put("a9", new SingleSprite(TILESIZE,0,2,Spritesheet.test));
		iniSprites.put("a11", new SingleSprite(TILESIZE,1,2,Spritesheet.test));
		iniSprites.put("a12", new SingleSprite(TILESIZE,2,2,Spritesheet.test));
		iniSprites.put("a13", new SingleSprite(TILESIZE,3,2,Spritesheet.test));
		iniSprites.put("a14", new SingleSprite(TILESIZE,0,3,Spritesheet.test));
		iniSprites.put("a15", new SingleSprite(TILESIZE,1,3,Spritesheet.test));
		iniSprites.put("a16", new SingleSprite(TILESIZE,2,3,Spritesheet.test));
		iniSprites.put("a17", new SingleSprite(TILESIZE,3,3,Spritesheet.test));
		iniSprites.put("a18", new SingleSprite(TILESIZE,0,4,Spritesheet.test));
		iniSprites.put("a19", new SingleSprite(TILESIZE,1,4,Spritesheet.test));
		iniSprites.put("a21", new SingleSprite(TILESIZE,2,4,Spritesheet.test));
		iniSprites.put("a22", new SingleSprite(TILESIZE,3,4,Spritesheet.test));
		iniSprites.put("a23", new SingleSprite(TILESIZE,0,5,Spritesheet.test));
		iniSprites.put("a24", new SingleSprite(TILESIZE,1,5,Spritesheet.test));
		iniSprites.put("a25", new SingleSprite(TILESIZE,2,5,Spritesheet.test));
		iniSprites.put("a26", new SingleSprite(TILESIZE,3,5,Spritesheet.test));
		
		
		iniTiles.put("a1",new StaticTile(iniSprites.get("a1")));
		iniTiles.put("a2",new StaticTile(iniSprites.get("a2")));
		iniTiles.put("a3",new StaticTile(iniSprites.get("a3")));
		iniTiles.put("a4",new StaticTile(iniSprites.get("a4")));
		iniTiles.put("a5",new StaticTile(iniSprites.get("a5")));
		iniTiles.put("a6",new StaticTile(iniSprites.get("a6")));
		iniTiles.put("a7",new StaticTile(iniSprites.get("a7")));
		iniTiles.put("a8",new StaticTile(iniSprites.get("a8")));
		iniTiles.put("a9",new StaticTile(iniSprites.get("a9")));
		iniTiles.put("a11",new StaticTile(iniSprites.get("a11")));
		iniTiles.put("a12",new StaticTile(iniSprites.get("a12")));
		iniTiles.put("a13",new StaticTile(iniSprites.get("a13")));
		iniTiles.put("a14",new StaticTile(iniSprites.get("a14")));
		iniTiles.put("a15",new StaticTile(iniSprites.get("a15")));
		iniTiles.put("a16",new StaticTile(iniSprites.get("a16")));
		iniTiles.put("a17",new StaticTile(iniSprites.get("a17")));
		iniTiles.put("a18",new StaticTile(iniSprites.get("a18")));
		iniTiles.put("a19",new StaticTile(iniSprites.get("a19")));
		iniTiles.put("a21",new StaticTile(iniSprites.get("a21")));
		iniTiles.put("a22",new StaticTile(iniSprites.get("a22")));
		iniTiles.put("a23",new StaticTile(iniSprites.get("a23")));
		iniTiles.put("a24",new StaticTile(iniSprites.get("a24")));
		iniTiles.put("a25",new StaticTile(iniSprites.get("a25")));
		iniTiles.put("a26",new StaticTile(iniSprites.get("a26")));
		
		
		
		iniSprites.put("verde", new SingleSprite(TILESIZE,0x00ff00));
		iniSprites.put("rojo", new SingleSprite(TILESIZE,2,2,Spritesheet.tiles));
		iniSprites.put("blanco", new SingleSprite(TILESIZE,0xffffff));
		iniSprites.put("negro", new SingleSprite(TILESIZE,0x000000));
		
		
		
		
		iniTiles.put("verde",new StaticTile(iniSprites.get("verde")));
		iniTiles.put("rojo",new StaticTile(iniSprites.get("rojo")));
		iniTiles.put("blanco",new StaticTile(iniSprites.get("blanco")));
		iniTiles.put("voidTile",new StaticTile(iniSprites.get("negro")));		
		
	
	}


}
