package game.entity.implementations.enemies.zeus;


public class InitialPhase implements ZeusPhase{

	private Zeus zeus;
	public InitialPhase(Zeus zeus) {
		this.zeus = zeus;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDmgDealt() {
	}
	@Override
	public void onDmgReceived() {
		zeus.weps.get("RayZeus").attack(4);
	}

}
