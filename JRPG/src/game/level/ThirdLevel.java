package game.level;

import game.entity.implementations.Hearth;
import game.entity.implementations.PressurePlate;
import game.entity.implementations.Rayo;
import game.entity.implementations.enemies.zeus.Zeus;
import game.entity.movement.HorizontalMovement;
import game.entity.movement.Movement;
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
		
		Rayo rayo2 = new Rayo(this, 60);
		Rayo rayo = new Rayo(this, 100);
		entList.addEntity(rayo);
		entList.addEntity(rayo2);
		
		Hearth hp = new Hearth(30, 218,400, this);
		entList.addEntity(hp);
		hp = new Hearth(30, 218,360, this);
		entList.addEntity(hp);
		hp = new Hearth(30, 218,320, this);
		entList.addEntity(hp);
		
		Movement zeusMov = new HorizontalMovement(this, 240, 1);
		Zeus zeus = new Zeus(55, 40, 90, 124, zeusMov, this);
		zeusMov.initializeEntity(zeus);
		entList.addEntity(zeus);
		
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
