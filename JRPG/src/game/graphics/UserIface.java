package game.graphics;

import game.entity.implementations.Player;
import auxiliar.AssetManager;

public class UserIface {
	
	private Player player;
	private int hP,energy;
	private Image HPbar,HPBarInside,energyBarInside;
	
	public UserIface(Player p) {
		player=p;
		HPbar = new Image(AssetManager.getSingleton().getImage("HPbar"));
		HPBarInside = new Image(AssetManager.getSingleton().getImage("life"));
		energyBarInside = new Image(AssetManager.getSingleton().getImage("energy"));
		hP = player.getHP();
		energy = player.getEnergy();
	}
	
	public void update(){
		hP = player.getHP();
		energy = player.getEnergy();
	}
	
	public void render(Rendering render){
		render.render(HPbar.getHeight(),HPbar.getWidth(), 0, 0, HPbar.pixels);
		render.renderPart(HPBarInside.getHeight(),HPBarInside.getWidth(), 0, 0, HPBarInside.pixels,hP/(float)player.MAXHP);
		
		render.render(HPbar.getHeight(),HPbar.getWidth(), 160, 0, HPbar.pixels);
		render.renderPart(energyBarInside.getHeight(),energyBarInside.getWidth(), 160, 0, energyBarInside.pixels,energy/(float)player.MAXENERGY);
		
	}

	

}
