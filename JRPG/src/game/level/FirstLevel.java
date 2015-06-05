package game.level;

import game.entity.implementations.Door;
import game.entity.implementations.Enemy;
import game.entity.implementations.Rayo;
import game.entity.implementations.enemies.CaballitoMarbao;
import game.entity.movement.EasyPFMovment;
import game.entity.movement.Movement;
import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;
import game.states.LevelState;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class FirstLevel extends Level {
	
	public static final int START_POS_X = 200; 
	public static final int START_POS_Y = 260;
	
	private BufferedImage imgToLvL;
		
	public FirstLevel(int spawnPosXPlayer, int spawnPosYPlayer, int w, int h, LevelState p){
		super(spawnPosXPlayer,spawnPosYPlayer,w,h,p);
		
		//AM.load("Level"); 
		
		imgToLvL = AM.getImage("level3");
		loadLevel();
		
		Rectangle enemyCollider = new Rectangle(0, 16, 64, 64);
		
		Movement mov = new EasyPFMovment(this,player);
	    Rectangle enemy1TileOffs = new Rectangle(25,57,42,62);
	    Enemy malo1 = new CaballitoMarbao(200,100,64,64,mov,this,enemy1TileOffs); // hay que ajustar los offsets
		malo1.addCustomCollider(enemyCollider);
	    mov.initializeEntity(malo1);
		entList.addEntity(malo1);
			
		Door door1 = new Door(224, 38, 32, 32, this,"SecondLevel", 224, 350); 
		entList.addEntity(door1);
		
		Rayo rayo2 = new Rayo(this, 112);
		Rayo rayo = new Rayo(this, 163);
		entList.addEntity(rayo);
		entList.addEntity(rayo2);
		
		
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

	/*@Override
	public void initializeSpritesAndTiles() {
		Spritesheet ss = new Spritesheet(AM.getImage("BloqueSprites02"));
		//pilar
		int pilar = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pilar"+x+y, x, y, ss, 0xff00f6f0+pilar,1,1,1,1);
				pilar++;
			}
		}
		//pilar Oscuro
		int pilarOscuro = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pilarOscuro"+x+y, x+12, y, ss, 0xff309b10+pilarOscuro,1,1,1,1);
				pilarOscuro++;
			}
		}
		//suelo
		spr_t.initSpriteOnTileOnHex("suelo", 1, 4, ss, 0xffff7f27); 
		//suelo2
		spr_t.initSpriteOnTileOnHex("suelo2", 0, 4, ss, 0xffac1e6e); 
		//suelo oscuro
		spr_t.initSpriteOnTileOnHex("sueloOscuro", 14, 0, ss, 0xfff3e224);
		//pasillo
		spr_t.initSpriteOnTileOnHex("pasillo1", 0, 5, ss, 0xffff2745);
		spr_t.initSpriteOnTileOnHex("pasillo2", 1, 5, ss, 0xffff2746);
		//borde grande1
		spr_t.initSpriteOnTileOnHex("bordeGra1", 1, 3, ss, 0xfffd0691, 1, 1, 1, 1);
		//borde lateral derecha1
		spr_t.initSpriteOnTileOnHex("bordeDer1", 2, 2, ss, 0xfffff200, 1, 0, 1, 0);
		//borde lateral izquierda1
		spr_t.initSpriteOnTileOnHex("bordeIzq1", 3, 2, ss, 0xfffd0634, 0, 1, 0, 1);
		//borde grande2
		spr_t.initSpriteOnTileOnHex("bordeGra2", 14, 1, ss, 0xff13369b, 1, 1, 1, 1);
		//borde lateral derecha2
		spr_t.initSpriteOnTileOnHex("bordeDer2", 14, 2, ss, 0xff13369c, 1, 0, 1, 0);
		//borde lateral izquierda2
		spr_t.initSpriteOnTileOnHex("bordeIzq2", 14, 3, ss, 0xff13369d, 0, 1, 0, 1);
		//cielo
		spr_t.initSpriteOnTileOnHex("agua", 0, 3, ss, 0xffa349a4, 3,3,3,3);
		//puerta
		int puerta = 0;
		for(int y = 0;y<6;y++){
			for(int x=0;x<8;x++){
				spr_t.initSpriteOnTileOnHex("puerta"+x+y, 4+x, y, ss, 0xff28a661+puerta, 1, 1, 1, 1);
				puerta++;
			}
		}
		//pared
		int pared = 0;
		for(int y = 0;y<2;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pared"+x+y, 2+x, y, ss, 0xff06599c+pared, 1, 1, 1, 1);
				pared++;
			}
		}
		//sombreado
		//parte1
		int sombra = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("sombraP1"+x+y, x+2, y+3, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		
		//parte2
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("sombraP2"+x+y, x+12, y+3, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		//parte3
		for(int y = 0;y<2;y++){
			for(int x=0;x<14;x++){
				spr_t.initSpriteOnTileOnHex("sombraP3"+x+y, x, y+6, ss, 0xffa62c00+sombra);
				sombra++;
			}
		}
		
		
	
	}
*/

}
