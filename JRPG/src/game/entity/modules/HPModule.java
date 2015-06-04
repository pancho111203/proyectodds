package game.entity.modules;


public class HPModule {
	private int HP, maxHP, immuneTimeOnHit, immuneTime;
	
	private int immunityCounter = 0;
	private boolean immune = false;
	
	public void update(){
		if(HP<=0){
			immune = false;
		}
		if(immune){
			if(immunityCounter<immuneTime){
				immunityCounter++;
			}else{
				immune = false;
				immunityCounter = 0;
			}
		}

	}
	
	public HPModule(int startingHP, int maxHP, int immuneTimeOnHit){
		HP = startingHP;
		this.maxHP = maxHP;
		this.immuneTimeOnHit = immuneTimeOnHit;
	}
	public void setHP(int hp){
		if(hp<=maxHP)HP=hp;
		else{
			HP = maxHP;
		}
	}
	public int getHP(){
		return HP;
	}
	public boolean isAlive(){
		return HP>0;
	}
	
	public void hit(int damage){
		if(immune)return;
		
		if(HP-damage>=0){
			startImmunity(immuneTimeOnHit);
			HP-=damage;
		}
		else{
			HP = 0;
		}
	}	
	
	public void startImmunity(int time){
		if(HP>0){//solo puede ser immune si esta vivo
			immune = true;
			immuneTime = time;
		}
	}
	
	public boolean isImmune(){
		return immune;
	}
	
	public double getPercentage(){
		return (double)HP/(double)maxHP;
	}
	
	public boolean isFullHP(){
		return HP == maxHP;
	}
}
