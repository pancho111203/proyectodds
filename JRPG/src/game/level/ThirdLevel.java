package game.level;

import game.entity.implementations.PressurePlate;
import game.events.TileChanger;
import game.graphics.RenderingLevel;
import game.states.LevelState;

import java.awt.image.BufferedImage;


public class ThirdLevel extends Level {
	
	public static final int START_POS_X = 208; 
	public static final int START_POS_Y = 431;
	
	private BufferedImage imgToLvL;
		
	public ThirdLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, LevelState p){
		super(spawnPosXPlayer,spawnPosYPlayer,w,h,p);
		
		imgToLvL = AM.getImage("level5");
		loadLevel();
		
		
		PressurePlate entranceCloseDoorPlate = new PressurePlate(208, 380, 32, 50, this);
		entList.addEntity(entranceCloseDoorPlate);
		
		TileChanger ent1 = new TileChanger(entranceCloseDoorPlate,13, 29, 0xff13369b, this);
		TileChanger ent2 = new TileChanger(entranceCloseDoorPlate,14, 29, 0xff13369b, this);
		
	}
	@Override
	public void loadLevel(){
		loadLevelFromImage();
		
	}
	
	
	public void loadLevelFromImage(){
		BufferedImage image;
		image = imgToLvL;
		width=image.getWidth();
		height=image.getHeight();			
		tiles=new int[width*height];
		image.getRGB(0, 0, width, height, tiles, 0, width); // se pasa la imagen al array tiles en formato RGB
											// mas adelante el metodo getTile se encarga de mapear cada color a un tile
		
		
	}

	public void render(RenderingLevel render){
		super.render(render);
		
	}


}
