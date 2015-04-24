package game.level;

import game.graphics.RenderingLevel;
import game.graphics.SingleSprite;
import game.graphics.SpriteAnim;
import game.graphics.Spritesheet;
import game.level.tiles.StaticTile;
import game.level.tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FirstLevel extends Level {
	
	private String imgLevelPath;
	private Tile player; //player del nivel añadido para probar cosas

	public FirstLevel(int startPosX, int startPosY, String pathImgToLvL, int w, int h){
		super(startPosX,startPosY,w,h);
		imgLevelPath = pathImgToLvL;
		loadLevel();
		player = new StaticTile(new SpriteAnim(16, 0, 2, Spritesheet.tiles)); //creo un ejemplo de tile
		//(SpriteAnim)(player.sprite).addAction(16,0,2,3);      //no puedo utilizarlo como SpriteAnim por que no he hecho el rollo del patron con interfaz etc... tomorrou more
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
		
		return iniTiles.get("voidTile");
	}
	
	//he añadido la función render aquí para renderizar cosas de este nivel y no directamente en la clase padre generica level (creo que es más limpio)
	public void render(RenderingLevel render){
		super.render(render);
		player.render(3, 4, 0, 0, render);
	}

	@Override
	public void initializeSpritesAndTiles() {
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
