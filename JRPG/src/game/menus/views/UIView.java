package game.menus.views;

import game.AssetManager;
import game.graphics.Image;
import game.graphics.Rendering;
import game.menus.Menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class UIView {

	protected Menu menu;
	protected UIView parent;
	protected String name;
	protected int x, y; // relative to parent
	protected int width, height;
	protected int computedAbsoluteX, computedAbsoluteY;
	protected ArrayList<UIView> subviews;
	protected boolean selectable = false;
	protected Image background;


	
	private UIView(UIView parent, Menu menu) {
		name = "View";
		this.parent = parent;
		if (parent != null) {
			computedAbsoluteX = parent.computedAbsoluteX + parent.x;
			computedAbsoluteY = parent.computedAbsoluteY + parent.y;
		} else {
			computedAbsoluteX = 0;
			computedAbsoluteY = 0;
		}
		this.menu = menu;
		subviews = new ArrayList<UIView>();

	}

	public UIView(UIView parent,Menu menu, int x, int y, int width, int height, String name) {
		this(parent, menu);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}


	public void notifyPositionChange() { // used by parent to notify of change
											// of coordinates
		// so the child can recompute its absolute pos
		if (parent != null) {
			computedAbsoluteX = parent.computedAbsoluteX + parent.x;
			computedAbsoluteY = parent.computedAbsoluteY + parent.y;
		}

	}

	public void update() {
		for (Iterator<UIView> i = subviews.iterator(); i.hasNext();) {
			UIView view = (UIView) i.next();
			view.update();
		}
	}

	public void render(Rendering render) { // needs to use the absolute position
											// to paint itself
		for (Iterator<UIView> i = subviews.iterator(); i.hasNext();) {
			UIView view = (UIView) i.next();
			view.render(render);
			;
		}
		// FIRST RENDER SUBVIEWS(LASTLY ADDED SUBVIES RENDER LAST AND THEREFORE
		// SHOW UP)

		if (background != null) {
			render.renderImage(computedAbsoluteX, computedAbsoluteY, background);
			// TODO por ahora no hay limitacion de width y height, asi que el
			// background puede exceder los limites
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setX(int x) {
		this.x = x;
		for (Iterator<UIView> i = subviews.iterator(); i.hasNext();) {
			UIView view = (UIView) i.next();
			view.notifyPositionChange();
		}
	}

	public void setY(int y) {
		this.y = y;
		for (Iterator<UIView> i = subviews.iterator(); i.hasNext();) {
			UIView view = (UIView) i.next();
			view.notifyPositionChange();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int h) {
		height = h;
	}

	public void setWidth(int w) {
		width = w;
	}
	public boolean isSelectable(){
		return selectable;
	}
	public void setSelectable(){
		selectable = true;
		menu.addToTheEndOfSelectionList(this);
	}
	public void setBackground(BufferedImage img) {
		// IMP! THE BACKGROUND MUST BE SET WITH LIMITS IN WIDTH AND HEIGHT LIKE
		// HERE OR IT WILL EXCEED VIEW BOUNDS
		background = new Image(img, width, height);
	}

	public void setBackground(String bgName) {
		background = new Image(AssetManager.getSingleton().getImage(bgName), width, height);
	}

	public void addSubview(UIView view) {
		subviews.add(view);
	}

	public void removeSubview(UIView view) {
		subviews.remove(view);
	}
	
	public void action(){
	}
	public void renderWhenSelected(Rendering render){
	}
	
//	public UIView nextView(){
//		// next view must resturn the first view in the list of subviews
//		// if there is no subview it returns the next brother
//		// if there is no more brothers it returns the brother of the parent
//		// if its the top parent and has no brothers, return null
//		
//		if(subviews.size()>0){
//			return subviews.get(0);
//		} else if(parent!=null&&parent.subviews.size() > positionInParentArray+1) {
//			return parent.subviews.get(positionInParentArray+1);
//		} else if(parent!=null&&parent.parent!=null&&parent.parent.subviews.size() > parent.positionInParentArray+1){
//			return parent.parent.subviews.get(parent.positionInParentArray+1);
//		} else {
//			return null;
//		}
//	}
//	public UIView prevView(){
//
//	}
}
