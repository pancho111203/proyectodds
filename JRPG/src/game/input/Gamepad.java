package game.input;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;


public class Gamepad{
	//esta clase funciona con el mando de la PS3 con el driver del de la XBox, no se que pasará con otro...
	
	private static Gamepad gamepad;
    
    private Controller controller;
    //id de cada botón 
    public final int CROS=0,CIRC=1,SQUA=2,TRIA=3, UP=10, DOWN=11, LEFT=12, RIGHT=13;
    public final int L1=4,R1=5,SELECT=6,START=7,L3=8,R3=9;               				//TODO L2=14,R2=15 (gatillos)
    //ID de cada direccion de los dos pads
    public final int Lup=0,Ldown=1,Lleft=2,Lright=3,Rup=4,Rdown=5,Rleft=6,Rright=7;
    
    // Estado de los botones y axis
    private ArrayList<Boolean> buttonsValues;
    public boolean pads[],padsReaded[],buttonReaded[];
    
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
    
    public static Gamepad getSingleton(){
    	if(gamepad==null){
    		gamepad = new Gamepad();
		}
		return gamepad;
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
    private boolean pollController()   {
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
                if(component.getPollData() > 0.9f)
                    buttonsValues.add(Boolean.TRUE);
                else
                    buttonsValues.add(Boolean.FALSE);
        }
        
        //                                   FLECHAS
        //leer estado de flechas
        boolean arrows[] = pollArrows();
        //elimino el id del grupo de flechas general
        buttonsValues.remove(10);
        //añado un id para cada flecha con su estado
        for(int i=0;i<arrows.length;i++){        	
        	buttonsValues.add(arrows[i]);       	
        }
        //la primera vez pongo a false el estado leido de cada flecha
        try{
	        if (buttonReaded==null){
	        	buttonReaded = new boolean[buttonsValues.size()];
	        	buttonStateClear();
	        }
        }catch(NullPointerException e){
        	return false;
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
    	pollController();
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
		pollController();
		return pads[pad];
	}
	
	public boolean padChanged(int pad){
		pollController();
		if(getPadState(pad)&&!padsReaded[pad]){
			padsReaded[pad]=true;
			return true;
		}else if(!getPadState(pad)){
			padsReaded[pad]=false;
			return false;
		}
		return false;
	}
	
	private void buttonStateClear(){
		  for(int i=0;i<buttonReaded.length;i++){
			  buttonReaded[i]=false;
	      }
		}
	
	public boolean buttonChanged(int button){
		if(!pollController())return false;
		if(getButtonValue(button)&&!buttonReaded[button]){
			buttonReaded[button]=true;
			return true;
		}else if(!getButtonValue(button)){
			buttonReaded[button]=false;
			return false;
		}
		return false;
	}	
	
	public float getHatSwitchPosition()   {
        Identifier identifier = Component.Identifier.Axis.POV;
        return controller.getComponent(identifier).getPollData();
    }
	
	public boolean[] pollArrows(){
		int res = (int)(getHatSwitchPosition()*1000);
		boolean arrows[]= new boolean[4];
		
		switch (res){
			case 1000:
				arrows[0]=false; arrows[1]=false; arrows[2]=true;  arrows[3]=false; break;
				
			case 875:
				arrows[0]=false; arrows[1]=true;  arrows[2]=true;  arrows[3]=false; break;
				
			case 750:
				arrows[0]=false; arrows[1]=true;  arrows[2]=false; arrows[3]=false; break;
				
			case 625:
				arrows[0]=false; arrows[1]=true;  arrows[2]=false; arrows[3]=true;  break;
				
			case 500:
				arrows[0]=false; arrows[1]=false; arrows[2]=false; arrows[3]=true;  break;
				
			case 375:
				arrows[0]=true;  arrows[1]=false; arrows[2]=false; arrows[3]=true;  break;
				
			case 250:
				arrows[0]=true;  arrows[1]=false; arrows[2]=false; arrows[3]=false; break;
				
			case 125:
				arrows[0]=true;  arrows[1]=false; arrows[2]=true;  arrows[3]=false; break;
				
			default:
				arrows[0]=false; arrows[1]=false; arrows[2]=false; arrows[3]=false; break;
				
		}
		return arrows;
	}
    
}