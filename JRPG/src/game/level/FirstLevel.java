package game.level;

import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FirstLevel extends Level {
	
	private String imgLevelPath;
	private BufferedImage imgToLvL;
	
	public FirstLevel(int startPosX, int startPosY, String pathImgToLvL, int w, int h){
		super(startPosX,startPosY,w,h);
		imgLevelPath = pathImgToLvL;
		loadLevel();
	}
	
	public FirstLevel(int startPosX, int startPosY, BufferedImage imgToLvL, int w, int h){
		super(startPosX,startPosY,w,h);
		imgLevelPath = null;
		this.imgToLvL = imgToLvL;
		loadLevel();
	}
	
	@Override
	public void loadLevel(){
		loadLevelFromImage();
	}
	
	
	public void loadLevelFromImage(){
		try{
			BufferedImage image;
			if(imgLevelPath!=null)	image = ImageIO.read(Level.class.getResource(imgLevelPath));
			else image = imgToLvL;
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
		
		//AM.playSound("footsteps"); 
	}

	@Override
	public void initializeSpritesAndTiles() {
		Spritesheet ss = new Spritesheet(AM.getImage("BloqueSprites01"));
		//pilar
		int pilar = 0;
		for(int y = 0;y<3;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("borde"+x+y, x, y, ss, 0xff00f6f0+pilar,1,1,1,1); //TEST
				pilar++;
			}
		}
		//suelo
		spr_t.initSpriteOnTileOnHex("suelo", 1, 4, ss, 0xffff7f27); //y: 0 y 1 diferentes suelos
		//borde grande
		spr_t.initSpriteOnTileOnHex("bordeGra", 1, 3, ss, 0xfffd0691, 1, 1, 1, 1);
		//borde lateral derecha
		spr_t.initSpriteOnTileOnHex("bordeDer", 2, 2, ss, 0xfffff200, 1, 0, 1, 0);
		//borde lateral izquierda
		spr_t.initSpriteOnTileOnHex("bordeIzq", 3, 2, ss, 0xfffd0634, 0, 1, 0, 1);
		//cielo
		spr_t.initSpriteOnTileOnHex("cielo", 0, 3, ss, 0xffa349a4);
		//puerta
		int puerta = 0;
		for(int y = 0;y<6;y++){
			for(int x=0;x<8;x++){
				spr_t.initSpriteOnTileOnHex("puerta"+x+y, 4+x, y, ss, 0xff28a661+puerta, 1, 1, 1, 1);
				puerta++;
			}
		}
		//paret
		int pared = 0;
		for(int y = 0;y<2;y++){
			for(int x=0;x<2;x++){
				spr_t.initSpriteOnTileOnHex("pared"+x+y, 2+x, y, ss, 0xff06599c+pared, 1, 1, 1, 1);
				pared++;
			}
		}
		
		
	
	}


}
