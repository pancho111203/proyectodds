package game.level.game1;

import game.entity.implementations.Door;
import game.entity.implementations.Enemy;
import game.entity.implementations.Hearth;
import game.entity.implementations.SignBoard;
import game.entity.implementations.enemies.Horseman;
import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.graphics.Rendering;
import game.level.Level;
import game.states.games.IGameState;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class FirstLevel extends Level {
	
	public static final int START_POS_X = 200; 
	public static final int START_POS_Y = 260;
	
	private BufferedImage imgToLvL;
		
	public FirstLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, IGameState p){
		super(spawnPosXPlayer,spawnPosYPlayer,w,h,p);
		
		//AM.load("Level"); 
		
		imgToLvL = AM.getImage("level3");
		loadLevel();
		
		Rectangle enemyCollider = new Rectangle(0, 16, 64, 64);
		
		Movement mov = new EasyPFMovment(this,player);
	    Rectangle enemy1TileOffs = new Rectangle(25,57,42,62);
	    Enemy malo1 = new Horseman(200,100,64,64,mov,this,enemy1TileOffs); // hay que ajustar los offsets
		malo1.addCustomCollider(enemyCollider);
	    mov.initializeEntity(malo1);
		entList.addEntity(malo1);
			
		Door door1 = new Door(244, 68, 1, 5, this,"SecondLevel", SecondLevel.START_POS_X, SecondLevel.START_POS_Y); 
		entList.addEntity(door1);
		
		Hearth hp = new Hearth(30, 100, 100, this);
		entList.addEntity(hp);
		
		String tut1Contents = "LOLOLOLOO AFSDFASDFASF ASDFASDFASDF sdfasfasdf";
		SignBoard tut1 = new SignBoard(190, 90, this, tut1Contents);
		entList.addEntity(tut1);
		
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
		int[] pixels=new int[width*height];
		image.getRGB(0, 0, width, height, pixels, 0, width); // se pasa la imagen al array tiles en formato RGB
											// mas adelante el metodo getTile se encarga de mapear cada color a un tile
		
		loadAllPixelsToTiles(pixels);
		
	}

	public void render(Rendering render){
		super.render(render);
		
		//AM.playSound("footsteps"); 
	}


}
