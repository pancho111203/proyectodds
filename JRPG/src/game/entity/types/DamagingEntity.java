package game.entity.types;

public interface DamagingEntity { 
	public boolean isActive();
	public int getDmg();
	public void dealtDamage(int d);
}
