package game.level;



public class CustomLevel extends Level {

	public CustomLevel(int w, int h, int startPosX, int startPosY,int wi, int hi){
		super(startPosX,startPosY,wi,hi);
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
	public void initializeSpritesAndTiles() {
		
		spr_t.initSpriteOnTileOnHex("verde", 0x00ff00, 1234);
		spr_t.initSpriteOnTileOnHex("blanco", 0xffffff, 4321);
		//spr_t.initSpriteOnTileOnHex("rojo", 2, 2, Spritesheet.tiles, 1111);
		
	}
	
	


}
