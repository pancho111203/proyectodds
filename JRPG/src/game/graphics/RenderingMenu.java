package game.graphics;

public class RenderingMenu extends Rendering{

	public RenderingMenu(int width, int height) {
		super(width, height);
	}
	
	public void renderImage(int xp, int yp, Image sp){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)

		super.render(sp.getHeight(),sp.getWidth(),xp,yp,sp.pixels);
	
	}
	
	public void renderImageColored(int xp, int yp, Image sp,int color){ // (xp,yp) es la posicion del sprite respecto a la pantalla(en pixels!!)
	
		int pix[] = new int[sp.pixels.length];
		
		for(int i=0;i<pix.length;i++){
			
			if(!isALPHA(sp.pixels[i])){
			
				pix[i]=sp.pixels[i]&color;
			
			}else pix[i]=sp.pixels[i];
			
		}
		
		super.render(sp.getHeight(),sp.getWidth(),xp,yp,pix);
	}
}
