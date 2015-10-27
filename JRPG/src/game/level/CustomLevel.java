package game.level;

import game.entity.implementations.Enemy;
import game.entity.implementations.enemies.Horseman;
import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.states.games.IGameState;

import java.awt.Rectangle;



public class CustomLevel extends Level {

	public static final int START_POS_X = 50; 
	public static final int START_POS_Y = 50;
	
	public CustomLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, IGameState p){
		super(spawnPosXPlayer,spawnPosXPlayer,w,h,p);
		width = w;
		height = h;
		
		Movement mov = new EasyPFMovment(this,player);
	    Rectangle enemy1TileOffs = new Rectangle(25,57,42,62);
	    Enemy malo1 = new Horseman(200,100,64,64,mov,this,enemy1TileOffs); // hay que ajustar los offsets
		mov.initializeEntity(malo1);
		entList.addEntity(malo1);
		
		loadLevel();
	}
	
	@Override
	public void loadLevel() {
		int[] pixels = new int[width*height];
		for(int i=0;i<width*height;i++){
			if(i%2==0)pixels[i] = 1234;
			else if(i%3==0)pixels[i] = 4321;
			else if(i%5==0)pixels[i] = 1111;
			else{
				pixels[i] = 0000;
			}
			
		}
		loadAllPixelsToTiles(pixels);
	}

	@Override
	public void initializeSpritesAndTiles() {
		
		spr_t.initSpriteOnTileOnHex("verde", 0x00ff00, 1234);
		spr_t.initSpriteOnTileOnHex("blanco", 0xffffff, 4321);
		spr_t.initSpriteOnTileOnHex("rojo", 0xff00ff, 1111);
		
	}
	
	


}
