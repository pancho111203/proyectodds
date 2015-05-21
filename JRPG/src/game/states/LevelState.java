package game.states;

import game.AssetManager;
import game.GameStart;
import game.entity.Entity;
import game.entity.list.EntityList;
import game.entity.list.Iterator;
import game.graphics.Rendering;
import game.graphics.RenderingLevel;
import game.level.FirstLevel;
import game.level.Level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class LevelState implements IState{

	private AssetManager AM;
	private GameStart game;
	private RenderingLevel render;
	private Level curLevel;
	
	public LevelState(GameStart game, int w, int h){
		this.game = game; 
		render = new RenderingLevel(w,h);
		AM = AssetManager.getSingleton(); 
		AM.load("FirstLevel");
		curLevel = new FirstLevel(80,0,AM.getImage("level3"),w,h); 
	}	

	@Override
	public void update() {
		
		curLevel.update();		
	
	}
	
	@Override
	public void render() {
		
		curLevel.render(render);
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void onEnter(String params) {
		
	}

	@Override
	public Rendering getRender() {
		return render;
	}

	@Override
	public void debug(Graphics g) {
		
		EntityList act = curLevel.getEntityList();
		int px = GameStart.pixelSize;
		g.setColor(Color.RED);
		Iterator it = act.getIterator();
		while(it.hasNext()){
			Entity e = (Entity)it.next();
			Rectangle r = e.getCollider();
			g.drawRect(((int)(r.getX()-curLevel.getXPosScreen())*px),((int)(r.getY()-curLevel.getYPosScreen())*px),(int)r.getWidth()*px,(int)r.getHeight()*px);
		}
	
	}

}
