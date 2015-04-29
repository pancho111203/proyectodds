package game.level;

import game.graphics.RenderingLevel;
import game.graphics.Spritesheet;

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
	
	//he añadido la función render aquí para renderizar cosas de este nivel y no directamente en la clase padre generica level (creo que es más limpio)
	public void render(RenderingLevel render){
		super.render(render);

		
		//SoundManager.getSingleton().play(0); 
		//SoundManager.getSingleton().play(1);  //recomiendo solo uno a la vez, era para comprobar que se pueden solapar varios
	}

	@Override
	public void initializeSpritesAndTiles() {
		//TODO herramienta para facilitar creacion de sprites y tiles
		int hexValuesCol[] = {0xff00f6ff,0xff00f6f1,0xff00f6f2,0xff00f6f3,
				0xff00f6f4,0xff00f6f5,0xff00f6f6,0xff00f6f7,0xff00f6f8,
				0xff00f6f9,0xff00f6fa,0xff00f6fb,0xff00f6fc,0xff00f6fd,
				0xff00f6fe,0xff00f700,0xff00f701,0xff00f702,0xff00f703,
				0xff00f704,0xff00f705,0xff00f706,0xff00f707,0xff00f708
		};
		
		int i = 0;
		for(int y = 0;y<6;y++){
			for(int x=0;x<4;x++){
				spr_t.initSpriteOnTileOnHex("col"+x+y, x, y, Spritesheet.test, hexValuesCol[i]);
				i++;
			}
		}
		
		spr_t.initSpriteOnTileOnHex("verde", 0x00ff00, 0xffb5e61d);
		spr_t.initHex(0xffefe4b0, "verde");
		spr_t.initHex(0xffff7f27, "verde");
		spr_t.initHex(0xfffff200, "verde");
		spr_t.initHex(0xff7092be, "verde");
		spr_t.initHex(0xff00a2e8, "verde");
		spr_t.initHex(0xffa349a4, "verde");
		spr_t.initSpriteOnTileOnHex("blanco", 0xffffff, 0xff3f48cc);
		spr_t.initSpriteOnTileOnHex("rojo", 2, 2, Spritesheet.tiles, 0xff7f7f7f);
		spr_t.initHex(0xff22b14c, "rojo");
		
	
	}


}
