package game.menus;

import game.AssetManager;
import game.menus.views.UIButtonColored;
import game.menus.views.UIView;

public class MainMenu extends Menu {

	public MainMenu(int w, int h) {
		super(w, h);
		AssetManager.getSingleton().load("MainMenu");
	}

	@Override
	protected void initContent() {

		parentView.setBackground("bg");
		UIView btnJugar = new UIButtonColored(parentView, this, 105, 130, 100, 24, "btnJugar");
		UIView btnCredits = new UIButtonColored(parentView, this, 105, 165, 100, 24, "btnCredts");
		UIView btnSalir = new UIButtonColored(parentView, this, 105, 200, 100, 24, "btnSalir");
		
		btnJugar.setBackground("btnJugar");
		btnCredits.setBackground("btnCredts");
		btnSalir.setBackground("btnSalir");
		
		btnJugar.setSelectable();
		btnCredits.setSelectable();
		btnSalir.setSelectable();
		
		//falta anadir accion con closure
		
		parentView.addSubview(btnJugar);
		parentView.addSubview(btnCredits);
		parentView.addSubview(btnSalir);
		
	}

}
