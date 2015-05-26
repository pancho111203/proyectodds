package game.graphics;

import game.AssetManager;
import game.entity.implementations.Player;

public class UserIface {
	
	private Player player;
	private int hP,fur;
	private Image HPbar,life;
	
	public UserIface(Player p) {
		player=p;
		HPbar = new Image(AssetManager.getSingleton().getImage("HPbar"));
		life = new Image(AssetManager.getSingleton().getImage("life"));
		hP = player.getHP();
	}
	
	public void update(){
		hP = player.getHP();
	}
	
	public void render(RenderingLevel render){
		render.render(HPbar.getHeight(),HPbar.getWidth(), 0, 0, HPbar.pixels);
		render.renderPart(life.getHeight(),life.getWidth(), 0, 0, life.pixels,hP/100f);
		
	}

	
	public void damage(int d){
		hP-=d;
	}
	
	public void incFur(int f){
		fur+=f;
	}

}
