package game.states.games;

import game.level.CustomLevel;
import game.level.Level;
import game.level.game1.FirstLevel;
import game.level.game1.SecondLevel;
import game.level.game1.ThirdLevel;

public class Game1Factory implements ILevelFactory {
	private int width, height;
	private IGameState ls;
	public Game1Factory(int w, int h, IGameState ls){
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
