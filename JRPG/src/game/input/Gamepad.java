package game.input;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;


public class Gamepad{
    
    private Controller controller;
                                                                           //todo(?)
    public final int CROS=0,CIRC=2,SQUA=3,TRIA=4;                        //UP=0,DOWN=0,LEFT=0,RIGHT=0;
    public final int L1=4,R1=5,SELECT=6,START=7,L3=8,R3=9;                //L2=999,R2=999,
    
    // Estado de los botones
    private ArrayList<Boolean> buttonsValues;
    //Estado de los Joystics
    public boolean Lup=false, Ldown=false, Rup=false, Rdown=false;  
    public boolean Lleft=false, Lright=false, Rleft=false, Rright=false;
    
    public Gamepad()
    {
        this.controller = null;
        this.buttonsValues = new ArrayList<Boolean>();
        initController();
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

    //si retorna false hay un problema con el controller
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
         return buttonsValues.get(index);
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
    
    
    public void RX(){
    	if (getXAxisValue()>0.5 && getXAxisValue()<1.5){
    		Rright=true;
    	}else if (getXAxisValue()<-0.5 && getXAxisValue()>-1.5){
    		Rleft=true;
    	}
    }
    
	public void RY(){
		if (getYAxisValue()>0.5 && getYAxisValue()<1.5){
			Rdown=true;
    	}else if (getYAxisValue()<-0.5 && getYAxisValue()>-1.5){
    		Rup=true;
    	}
	}
	
	public void LX(){
		if (getXRotationValue()>0.5 && getXRotationValue()<1.5){
			Lright=true;
    	}else if (getXRotationValue()<-0.5 && getXRotationValue()>-1.5){
    		Lleft=true;
    	}
	}
	
	public void LY(){
		if (getYRotationValue()>0.5 && getYRotationValue()<1.5){
    		Ldown=true;
    	}else if (getYRotationValue()<-0.5 && getYRotationValue()>-1.5){
    		Lup=true;
    	}	
	}
	
	public void axisPoll(){
		RY();RX();
		LY();LX();
	}
	private void axisClear(){
        Lup=false; Ldown=false; 
        Rup=false; Rdown=false; 
        Lleft=false; Lright=false; 
        Rleft=false; Rright=false;
	}
    
    
}
    

