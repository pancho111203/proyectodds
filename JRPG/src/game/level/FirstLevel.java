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
		this.imgToLvL=imgToLvL;
		loadLevel();
	}
	
	@Override
	public void loadLevel(){
		loadLevelFromImage();
	}
	
	
	public void loadLevelFromImage(){
		try{
			BufferedImage image;
			if(imgLevelPath==null) image = imgToLvL;
			else image = ImageIO.read(Level.class.getResource(this.imgLevelPath));
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
				spr_t.initSpriteOnTileOnHex("col"+x+y, x, y,new Spritesheet(AM.getImage("pilar_pequeno")), hexValuesCol[i]);
				i++;
			}
		}
	
	}

	

}
