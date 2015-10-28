package game.menus.views;

import game.graphics.Rendering;
import game.menus.Menu;

import java.util.Iterator;

public class UIButtonColored extends UIView {
	
	private int maskColor = 0xFEEECE;
	
	public UIButtonColored(UIView parent, Menu menu, int x, int y, int width, int height, String name) {
		super(parent, menu, x, y, width, height, name);
	}
	
	public void renderWhenSelected(Rendering render){
		for (Iterator<UIView> i = subviews.iterator(); i.hasNext();) {
			UIView view = (UIView) i.next();
			view.render(render);
			;
		}
		// FIRST RENDER SUBVIEWS(LASTLY ADDED SUBVIES RENDER LAST AND THEREFORE
		// SHOW UP)

		if (background != null) {
			render.renderImageColored(computedAbsoluteX, computedAbsoluteY, background, maskColor);
			// TODO por ahora no hay limitacion de width y height, asi que el
			// background puede exceder los limites
		}
	}
	public void setMaskColor(int c){
		maskColor = c;
	}

}
