package game.menus;


public class CreditsMenu extends Menu{

	public CreditsMenu(int w, int h) {
		super(w, h);
	}

	@Override
	protected void initContent() {
		parentView.setBackground("creditos");
		parentView.setSelectable();
	}


}
