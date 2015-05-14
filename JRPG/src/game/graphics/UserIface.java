package game.graphics;

import game.AssetManager;
import game.entity.Player;

public class UserIface {
	
	private Player player;
	private int hP,fur;
	private Image HPbar,life;
	
	public UserIface(Player p) {
		player=p;
		HPbar = new Image(AssetManager.getSingleton().getImage("HPbar"));
		life = new Image(AssetManager.getSingleton().getImage("life"));
	}
	
	public void update(){
		
	}
	
	public void render(RenderingLevel render){
		render.render(HPbar.getHeight(),HPbar.getWidth(), 0, 0, HPbar.pixels);
		render.render(life.getHeight(),life.getWidth(), 0, 0, life.pixels);
		
	}

	private int getFur() {
		return fur;
	}

	private void setFur(int fur) {
		this.fur = fur;
	}

	private int gethP() {
		return hP;
	}

	private void sethP(int hP) {
		this.hP = hP;
	}
	
	public void damage(int d){
		hP-=d;
	}
	
	public void incFur(int f){
		fur+=f;
	}

}
