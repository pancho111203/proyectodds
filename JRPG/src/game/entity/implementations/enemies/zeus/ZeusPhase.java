package game.entity.implementations.enemies.zeus;


public interface ZeusPhase {
	public void update();
	public void onEnter();
	public void onExit();
	public void onDmgDealt();
	public void onDmgReceived();
}
