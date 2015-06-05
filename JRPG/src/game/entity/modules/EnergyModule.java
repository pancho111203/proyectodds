package game.entity.modules;

public class EnergyModule extends Module{
	private int energy, maxEnergy;
	
	private int emptiedLockQuant;
	
	private boolean emptied = false;
	
	public EnergyModule(int startingEnergy, int maxEnergy){
		energy = startingEnergy;
		this.maxEnergy = maxEnergy;
		
		emptiedLockQuant = maxEnergy/10;
	}
	public void setEnergy(int e){
		if(e<=maxEnergy)energy=e;
		else{
			energy = maxEnergy;
		}
	}
	public int getEnergy(){
		return energy;
	}
	
	//devuelve -1 si es invalido
	public int substractEnergy(int x){
		if(emptied&&energy<emptiedLockQuant){
			return -1;
		}
			
		if(emptied){
			emptied = false;
		}
		
		if(energy-x<=0){
			emptied = true;
			return -1;
		}
		energy-=x;
		return energy;
	}
	
	//devuelve -1 si es invalido
	public int addEnergy(int x){
		if(energy+x>maxEnergy){
			return -1;
		}
		energy+=x;
		return energy;
	}
}