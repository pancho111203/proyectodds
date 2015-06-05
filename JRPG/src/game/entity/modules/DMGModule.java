package game.entity.modules;

public class DMGModule extends Module{
	private int DMG;
	
	public DMGModule(int dmg){
		DMG = dmg;
	}
	
	public int getDMG() {
		return DMG;
	}

	public void setDMG(int dMG) {
		DMG = dMG;
	}
}
