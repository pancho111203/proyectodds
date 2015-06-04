package game.entity.modules;

public class EnergyModule {
	private int energy, maxEnergy;
	public EnergyModule(int startingEnergy, int maxEnergy){
		energy = startingEnergy;
		this.maxEnergy = maxEnergy;
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
		if(energy-x<=0){
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