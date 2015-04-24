package game.level;

import game.graphics.Sprite;
import game.graphics.Spritesheet;
import game.level.tiles.StaticTile;
import game.level.tiles.Tile;


public class CustomLevel extends Level {

	public CustomLevel(int w, int h, int startPosX, int startPosY){
		super(startPosX,startPosY);
		width = w;
		height = h;
		tiles = new int[width*height];
		loadLevel();
	}
	
	@Override
	public void loadLevel() {
		for(int i=0;i<width*height;i++){
			if(i%2==0)tiles[i] = 1234;
			else if(i%3==0)tiles[i] = 4321;
			else if(i%5==0)tiles[i] = 1111;
			else{
				tiles[i] = 0000;
			}
			
		}
	}

	@Override
	public Tile getTile(int x, int y) {  // (x,y) 
		
		if(x < 0 || y < 0 || x >= width || y>= height){return iniTiles.get("voidTile");}
		
		if(tiles[x+y*width]==1234){return iniTiles.get("verde");}
		if(tiles[x+y*width]==4321){return iniTiles.get("blanco");}
		if(tiles[x+y*width]==1111){return iniTiles.get("rojo");}
			
		return iniTiles.get("voidTile");
	}

	@Override
	public void initializeSpritesAndTiles() {
		iniSprites.put("verde", new Sprite(TILESIZE,0x00ff00));
		iniSprites.put("rojo", new Sprite(TILESIZE,2,2,Spritesheet.tiles));
		iniSprites.put("blanco", new Sprite(TILESIZE,0xffffff));
		iniSprites.put("negro", new Sprite(TILESIZE,0x000000));
		
		
		
		iniTiles.put("verde",new StaticTile(iniSprites.get("verde")));
		iniTiles.put("rojo",new StaticTile(iniSprites.get("rojo")));
		iniTiles.put("blanco",new StaticTile(iniSprites.get("blanco")));
		iniTiles.put("voidTile",new StaticTile(iniSprites.get("negro")));
	}
	
	


}
