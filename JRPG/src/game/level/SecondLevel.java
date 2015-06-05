package game.level;

import game.entity.implementations.Enemy;
import game.entity.implementations.Rayo;
import game.entity.implementations.enemies.CaballitoMarbao;
import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.events.TileChanger;
import game.graphics.RenderingLevel;
import game.states.LevelState;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class SecondLevel extends Level {
	
	public static final int START_POS_X = 239; 
	public static final int START_POS_Y = 506;
	
	private BufferedImage imgToLvL;
		
	public SecondLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, LevelState p){
		super(spawnPosXPlayer,spawnPosYPlayer,w,h,p);
		
		//AM.load("Level"); 
		
		imgToLvL = AM.getImage("level4");
		loadLevel();
		
		Rectangle enemyCollider = new Rectangle(0, 16, 64, 64);
		
		Movement mov = new EasyPFMovment(this,player);
	    Rectangle enemy1TileOffs = new Rectangle(25,57,42,62);
	    Enemy malo1 = new CaballitoMarbao(200,100,64,64,mov,this,enemy1TileOffs); // hay que ajustar los offsets
		malo1.addCustomCollider(enemyCollider);
	    mov.initializeEntity(malo1);
		entList.addEntity(malo1);
			
		//Door door1 = new Door(224, 38, 32, 32, this,"CustomLevel", 224, 350); 
		//entList.addEntity(door1);
		
		Rayo rayo2 = new Rayo(this, 112);
		Rayo rayo = new Rayo(this, 163);
		entList.addEntity(rayo);
		entList.addEntity(rayo2);
		
		TileChanger t1 = new TileChanger(15, 1, 1, 0xffff2745, this);
		TileChanger t2 = new TileChanger(16, 1, 1, 0xffff2746, this);
		TileChanger t3 = new TileChanger(15, 2, 1, 0xffff2745, this);
		TileChanger t4 = new TileChanger(16, 2, 1, 0xffff2746, this);
		TileChanger t5 = new TileChanger(15, 3, 1, 0xffff2745, this);
		TileChanger t6 = new TileChanger(16, 3, 1, 0xffff2746, this);
		
		
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
		
		//AM.playSound("footsteps"); 
	}


}
