package game.level.game1;

import game.entity.implementations.Door;
import game.entity.implementations.Enemy;
import game.entity.implementations.Hearth;
import game.entity.implementations.PressurePlate;
import game.entity.implementations.enemies.Horseman;
import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.events.KillAmountObserver;
import game.events.TileChanger;
import game.graphics.Rendering;
import game.level.Level;
import game.level.tiles.Tile;
import game.states.games.IGameState;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class SecondLevel extends Level {
	
	public static final int START_POS_X = 239; 
	public static final int START_POS_Y = 506;
	
	private BufferedImage imgToLvL;
	private Rectangle enemyCollider, enemy1TileOffs;
	private int difficulty = 0;
	private boolean left = true;
	public SecondLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, IGameState p){
		super(spawnPosXPlayer,spawnPosYPlayer,w,h,p);
		
		//AM.load("Level"); 
		
		imgToLvL = AM.getImage("level4");
		loadLevel();
		
		enemyCollider = new Rectangle(0, 16, 64, 64);
		Movement mov = new EasyPFMovment(this,player);
	    enemy1TileOffs = new Rectangle(25,57,42,62);
	    Enemy malo1 = new Horseman(200,100,64,64,mov,this,enemy1TileOffs); // hay que ajustar los offsets
		malo1.addCustomCollider(enemyCollider);
	    mov.initializeEntity(malo1);
		entList.addEntity(malo1);
			
		KillAmountObserver killObserver = new KillAmountObserver(10);
		
		
		Tile pasillo1 = spr_t.getTile("pasillo1");
		Tile pasillo2 = spr_t.getTile("pasillo2");
		TileChanger t1 = new TileChanger(killObserver,15, 1, pasillo1, this);
		TileChanger t2 = new TileChanger(killObserver,16, 1, pasillo2, this);
		TileChanger t3 = new TileChanger(killObserver,15, 2, pasillo1, this);
		TileChanger t4 = new TileChanger(killObserver,16, 2, pasillo2, this);
		TileChanger t5 = new TileChanger(killObserver,15, 3, pasillo1, this);
		TileChanger t6 = new TileChanger(killObserver,16, 3, pasillo2, this);
		
		
		Door door1 = new Door(253, 5, 1, 5, this,"ThirdLevel", ThirdLevel.START_POS_X, ThirdLevel.START_POS_Y); 
		entList.addEntity(door1);
		
		
		PressurePlate entranceCloseDoorPlate = new PressurePlate(239, 450, 32, 50, this);
		entList.addEntity(entranceCloseDoorPlate);
		
		Tile bordeGra2 = spr_t.getTile("bordeGra2");
		TileChanger ent1 = new TileChanger(entranceCloseDoorPlate,15, 34, bordeGra2, this);
		TileChanger ent2 = new TileChanger(entranceCloseDoorPlate,16, 34, bordeGra2, this);
		
		
		
	}
	
	public void enemyDestroyed(Enemy e){
		int y;
		
		y = (int) (Math.random()*10);
		if(y>6){
			Hearth hp = new Hearth(30, e.getX()+e.getWidth()/2, e.getY()+e.getHeight()/2, this);
			entList.addEntity(hp);
		}
		
		increaseDifficulty();

	}
	
	public void increaseDifficulty(){
		difficulty++;
		
		if(difficulty%2==1){
			if(left){
				spawnEnemyHorseLeftSide();
				left = false;
			}else{
				spawnEnemyHorseRightSide();
				left = true;
			}
		}else{
			spawnEnemyHorseLeftSide();
			spawnEnemyHorseRightSide();
		}
		
	}
	
	public void spawnEnemyHorseLeftSide(){
		int x, y;
		
		do{
			y = (int) (Math.random()*(getHeight()*Level.TILESIZE));
			x = (int) (Math.random()*(getWidth()/2*Level.TILESIZE));
			//comprueba que el enemigo no spawnea tocando algo solido ni dentro de la pantalla
		}while(!checkIfNotCollidingWithAnything(x, y, Horseman.WIDTH, Horseman.HEIGHT) || !checkIfNotOutsidePlayerView(x, y, Horseman.WIDTH, Horseman.HEIGHT));
		
		
		Movement mov = new EasyPFMovment(this,player);
		Enemy malo1 = new Horseman(x,y,64,64,mov,this,enemy1TileOffs);
		malo1.addCustomCollider(enemyCollider);
		mov.initializeEntity(malo1);
		entList.addEntity(malo1);
	}
	public void spawnEnemyHorseRightSide(){
		int x, y;
		
		do{
			y = (int) (Math.random()*(getHeight()*Level.TILESIZE));
			x = (int) (Math.random()*(getWidth()/2*Level.TILESIZE)+(getWidth()/2*Level.TILESIZE));
			//comprueba que el enemigo no spawnea tocando algo solido ni dentro de la pantalla
		}while(!checkIfNotCollidingWithAnything(x, y, Horseman.WIDTH, Horseman.HEIGHT) || !checkIfNotOutsidePlayerView(x, y, Horseman.WIDTH, Horseman.HEIGHT));
		
		
		Movement mov = new EasyPFMovment(this,player);
		Enemy malo1 = new Horseman(x,y,64,64,mov,this,enemy1TileOffs);
		malo1.addCustomCollider(enemyCollider);
		mov.initializeEntity(malo1);
		entList.addEntity(malo1);
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
		
	}


}
