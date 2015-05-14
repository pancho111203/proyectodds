package game.input;


import game.graphics.Sprite;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{

	private static Mouse mouse;
	private int numButtons,x,y;
	
	private Statebutton[] buttons;
	private boolean[] pressed;
	
	private enum Statebutton{
		PRESSED,
		HOLD,
		RELEASED
	}
	
	public static Mouse getSingleton(){
		if(mouse==null){
			mouse = new Mouse();
		}
		return mouse;
	}
	
	public Mouse() {
		numButtons = MouseInfo.getNumberOfButtons();
		
		buttons = new Statebutton[numButtons];
		pressed = new boolean[numButtons];
		
		for (int i=0;i<numButtons;i++){
			buttons[i]=Statebutton.RELEASED;
			pressed[i]=false;
		}
		
	}
	
	public void update(){
		
		for(int i = 0;i<numButtons;i++){
			if(pressed[i]){
				if(buttons[i]==Statebutton.RELEASED){
					buttons[i] = Statebutton.PRESSED;
				}else{
					buttons[i] = Statebutton.HOLD;
				}
			}else{
				buttons[i] = Statebutton.RELEASED;
			}
		}
	}
	
	//retorna true si se ha pulsado sobre un sprite concreto del juego (singlesprite/animator/image)
	//TODO por alguna razón tiene un margen de error cuanto más te alejas de (0,0) de +-5
	public boolean spritePressed(Sprite s, int b){
		
		if(pressed[b] && ( x>s.getX()&&x<(s.getX()+s.getWidth())) && (y>s.getY()&&y<(s.getY()+s.getHeight())) )
		return true;
		
		return false;
	}
	
	public boolean mouseDown(int b) {
		if(b>0&&b<numButtons)	return (buttons[b] == Statebutton.PRESSED || buttons[b] == Statebutton.HOLD);
		else return false;
	}
	
	public boolean mousePressed(int b) {
		if(b>0&&b<numButtons) return (buttons[b] == Statebutton.PRESSED);			
		else return false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		x=e.getX()/2;
		y=e.getY()/2;
		pressed[e.getButton()] = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed[e.getButton()] = false;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}



}
