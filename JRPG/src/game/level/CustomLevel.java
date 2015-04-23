package game.level;

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
		
		if(x < 0 || y < 0 || x >= width || y>= height){return Tile.voidTile;}
		
		if(tiles[x+y*width]==1234){return Tile.verde;}
		if(tiles[x+y*width]==4321){return Tile.blanco;}
		if(tiles[x+y*width]==1111){return Tile.rojo;}
			
		return Tile.voidTile;
	}
	
	


}
