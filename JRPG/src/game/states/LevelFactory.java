package game.states;

import game.level.CustomLevel;
import game.level.FirstLevel;
import game.level.Level;
import game.level.SecondLevel;
import game.level.ThirdLevel;

public class LevelFactory {
	private int width, height;
	private LevelState ls;
	public LevelFactory(int w, int h, LevelState ls){
		width = w;
		height = h;
		this.ls = ls;
	}
	
	public Level getLevel(String ident,int spX, int spY){

		
		if(ident.equals("FirstLevel")){
			return new FirstLevel(spX,spY,width,height,ls); 			
		}else if(ident.equals("SecondLevel")){
			return new SecondLevel(spX,spY,width,height,ls); 
		}else if(ident.equals("ThirdLevel")){
			return new ThirdLevel(spX,spY,width,height,ls); 
		}
		
		return new CustomLevel(spX,spY,width,height,ls);
	}
}
