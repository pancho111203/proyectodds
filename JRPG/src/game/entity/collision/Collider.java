package game.entity.collision;

import game.entity.Enemy;
import game.entity.Entity;
import game.entity.Player;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Collider extends Rectangle{

	public static ArrayList<Collider> colliders;
	public static Collider playerCollider;
	
	Entity owner;
	
	public Collider(int x, int y, int w,int h, Enemy e) {
		super(x, y, w*16, h*16);
		owner = e;
		colliders = new ArrayList<Collider>();
		colliders.add(this);
	}
	
	public Collider(int x, int y, int w,int h, Player e) {
		super(x, y, w*16, h*16);
		owner = e;
		playerCollider=this;
	}
	
	public void update(int x, int y){
	    setLocation(x, y);
	}
	
	public static void checkCollisions(){
		
		for(int i=0;i<colliders.size();i++){
			if(playerCollider.intersects(colliders.get(i))){
				
				((Player)playerCollider.owner).collidesWith(colliders.get(i));
				((Enemy)colliders.get(i).owner).collidesWith(playerCollider);
				
			}
		}
		
	}
	
	

}
