package game.level;

import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;
import game.sound.SoundManager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FirstLevel extends Level {
	
	private String imgLevelPath;
	
	public FirstLevel(int startPosX, int startPosY, String pathImgToLvL, int w, int h){
		super(startPosX,startPosY,w,h);
		imgLevelPath = pathImgToLvL;
		loadLevel();
	}
	
	@Override
	public void loadLevel(){
		loadLevelFromImage(imgLevelPath);
	}
	
	
	public void loadLevelFromImage(String path){
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width=image.getWidth();
			height=image.getHeight();			
			tiles=new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width); // se pasa la imagen al array tiles en formato RGB
												// mas adelante el metodo getTile se encarga de mapear cada color a un tile
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error! No se ha podido cargar el nivel.");
		}
		
		
	}

	public void render(RenderingLevel render){
		super.render(render);
		
		SoundManager.getSingleton("FirstLevel").play(1); 
	}

	@Override
	public void initializeSpritesAndTiles() {
		//TODO herramienta para facilitar creacion de sprites y tiles
		
		//pilar
		int pilar = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("borde"+x+y, x, y, Spritesheet.test, 0xff00f6f0+pilar);
				pilar++;
			}
		}
		//suelo
		spr_t.initSpriteOnTileOnHex("suelo", 1, 4, Spritesheet.test, 0xffff7f27); //y: 0 y 1 diferentes suelos
		//borde grande
		spr_t.initSpriteOnTileOnHex("bordeGra", 1, 3, Spritesheet.test, 0xfffd0691);
		//borde lateral derecha
		spr_t.initSpriteOnTileOnHex("bordeDer", 2, 2, Spritesheet.test, 0xfffff200);
		//borde lateral izquierda
		spr_t.initSpriteOnTileOnHex("bordeIzq", 3, 2, Spritesheet.test, 0xfffd0634);
		//cielo
		spr_t.initSpriteOnTileOnHex("cielo", 0, 3, Spritesheet.test, 0xffa349a4);
		//puerta
		int puerta = 0;
		for(int y = 0;y<6;y++){
			for(int x=0;x<8;x++){
				spr_t.initSpriteOnTileOnHex("puerta"+x+y, 4+x, y, Spritesheet.test, 0xff28a661+puerta);
				puerta++;
			}
		}
		//paret
		int paret = 0;
		for(int y = 0;y<2;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("paret"+x+y, 2+x, y, Spritesheet.test, 0xff06599c+paret);
				paret++;
			}
		}
		
		
	
	}


}
