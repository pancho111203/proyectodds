package game.entity;

public class Stats {

	private int HP,ENERGY,DMG;
	private final int MAXENERGY = 1000;
	
	public Stats(){
		HP=0;
		ENERGY=MAXENERGY;
	}
	
	public Stats(int hp,int energy,int dmg){
		HP=hp;
		ENERGY= (energy<MAXENERGY)? energy:MAXENERGY;
		setDMG(dmg);
	}
	
	public void setHP(int hp){
		HP=hp;
	}
	
	public int getHP(){
		return HP;
	}
	public void setEnergy(int en){
		ENERGY=en;
	}
	
	public int getEnergy(){
		return ENERGY;
	}
	
	public int substractEnergy(int x){
		if(ENERGY-x<=0){
			return -1;
		}
		ENERGY-=x;
		return ENERGY;
	}
	public int addEnergy(int x){
		if(ENERGY+x>MAXENERGY){
			return -1;
		}
		ENERGY+=x;
		return ENERGY;
	}
	
	public boolean isAlive(){
		return HP>0;
	}
	
	public void hit(int damage){
		if(HP-damage>=0)HP-=damage;
		else{
			HP = 0;
		}
	}

	public int getDMG() {
		return DMG;
	}

	public void setDMG(int dMG) {
		DMG = dMG;
	}


}
