package game.input;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;


public class Gamepad{
    
    private Controller controller;
    //id de cada botón                                                   //todo(?) estos van como los pads y me da peresita :/
    public final int CROS=0,CIRC=2,SQUA=3,TRIA=4;                        //UP=0,DOWN=0,LEFT=0,RIGHT=0;      (flechas)
    public final int L1=4,R1=5,SELECT=6,START=7,L3=8,R3=9;               //L2=0,R2=0 						(gatillos)
    //ID de cada direccion de los dos pads
    public final int Lup=0,Ldown=1,Lleft=2,Lright=3,Rup=4,Rdown=5,Rleft=6,Rright=7;
    
    // Estado de los botones y axis
    private ArrayList<Boolean> buttonsValues;
    public boolean pads[],padsReaded[];
    
    public Gamepad()
    {
        this.controller = null;
        this.buttonsValues = new ArrayList<Boolean>();
        pads = new boolean[8];     
        padsReaded = new boolean[8];
        initController();
        axisClear();
        axisStateClear();
    }
   
    private void initController()
    {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        
        for(int i=0; i < controllers.length && controller == null; i++) {
            if( controllers[i].getType() == Controller.Type.GAMEPAD) {
                controller = controllers[i];
                break;
            }
        }
    }
    
    //actualiza el estado del pad
    //si retorna false, hay un problema con el controller
    public boolean pollController()
    {
        boolean isControllerValid;
        
        // Clear previous values of buttons.
        buttonsValues.clear();
        axisClear();
        
        
        try {
            isControllerValid = controller.poll();
        } catch (Exception e) {
            return false;
        }
        if(!isControllerValid)
            return false;
        
        Component[] components = controller.getComponents();
        
        axisPoll();
        
        for(int i=0; i < components.length; i++) {
            Component component = components[i];
            
            // Add states of the buttons
            if(component.getName().contains("But")||component.getName().contains("Bot"))
                if(component.getPollData() > 0.5f)
                    buttonsValues.add(Boolean.TRUE);
                else
                    buttonsValues.add(Boolean.FALSE);
        }
        
        return isControllerValid;
    }
    
    public int getNumberOfButtons()
    {
        return buttonsValues.size();
    }

    public ArrayList<Boolean> getButtonsValues()
    {
        return buttonsValues;
    }
    
    public boolean getButtonValue(int index)
    {
         try {
        	 return buttonsValues.get(index);
         }catch(Exception e){
        	 return false;
         }
    }
    
    
    //joysticks
    
    public float getXAxisValue()
    {
        Identifier identifier = Component.Identifier.Axis.X;
        return controller.getComponent(identifier).getPollData();
    }
    
    public float getYAxisValue()
    {
        Identifier identifier = Component.Identifier.Axis.Y;
        return controller.getComponent(identifier).getPollData();
    }
    
    
    public float getXRotationValue()
    {
        Identifier identifier = Component.Identifier.Axis.RX;
        return controller.getComponent(identifier).getPollData();
    }
    
    public float getYRotationValue()
    {
        Identifier identifier = Component.Identifier.Axis.RY;
        return controller.getComponent(identifier).getPollData();
    }
    
    
    public void LX(){
    	if (getXAxisValue()>0.5 && getXAxisValue()<1.5){
    		pads[Lright]=true;
    	}else if (getXAxisValue()<-0.5 && getXAxisValue()>-1.5){
    		pads[Lleft]=true;
    	}
    }
    
	public void LY(){
		if (getYAxisValue()>0.5 && getYAxisValue()<1.5){
			pads[Ldown]=true;
    	}else if (getYAxisValue()<-0.5 && getYAxisValue()>-1.5){
    		pads[Lup]=true;
    	}
	}
	
	public void RX(){
		if (getXRotationValue()>0.5 && getXRotationValue()<1.5){
			pads[Rright]=true;
    	}else if (getXRotationValue()<-0.5 && getXRotationValue()>-1.5){
    		pads[Rleft]=true;
    	}
	}
	
	public void RY(){
		if (getYRotationValue()>0.5 && getYRotationValue()<1.5){
			pads[Rdown]=true;
    	}else if (getYRotationValue()<-0.5 && getYRotationValue()>-1.5){
    		pads[Rup]=true;
    	}	
	}
	
	public void axisPoll(){
		RY();RX();
		LY();LX();
	}
	private void axisClear(){
        for(int i=0;i<pads.length;i++){
        	pads[i]=false;
        }
	}
	private void axisStateClear(){
	  for(int i=0;i<padsReaded.length;i++){
		  padsReaded[i]=false;
      }
	}
    
	public boolean getPadState(int pad){
		return pads[pad];
	}
	
	public boolean padChanged(int pad){
		
		if(getPadState(pad)&&!padsReaded[pad]){
			padsReaded[pad]=true;
			return true;
		}else if(!getPadState(pad)){
			padsReaded[pad]=false;
			return false;
		}
		return false;
	}
    
}