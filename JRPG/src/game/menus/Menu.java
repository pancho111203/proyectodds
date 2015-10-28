package game.menus;

import game.AssetManager;
import game.graphics.Rendering;
import game.input.GameInput;
import game.menus.views.UIView;

import java.util.ArrayList;

public abstract class Menu {

	protected GameInput gi;
	protected AssetManager AM;
	protected UIView parentView;
	protected int selectedView = 0;
	protected ArrayList<UIView> viewsForSelection;
	
	public Menu(int w, int h) {
		AM= AssetManager.getSingleton();
		gi= GameInput.getSingleton();
		parentView = new UIView(null, this, 0, 0, w, h, "Root");
		viewsForSelection = new ArrayList<UIView>();
		
		initContent();

	}
	
	public void update(){	
		
		parentView.update();
		checkInput();
	}

	public void render(Rendering render){
		 parentView.render(render);
		 if(selectedView>=0 && selectedView < viewsForSelection.size()){
			 viewsForSelection.get(selectedView).renderWhenSelected(render); 
		 }
		 //TODO /OPT (opt = optimizar)la selectedview se renderiza dos veces, posiblemente arreglarlo en el futuro
	}
	
	private void checkInput(){
		boolean up = gi.inputPressed(gi.UP);
		boolean down = gi.inputPressed(gi.DOWN);
		boolean action = gi.inputPressed(gi.ACTION);
		
		if(up&&!down){
			navigateToPrevView();
		}
		if(down&&!up){
			navigateToNextView();
		}
		if(action){			
			actionSelectedView();
		}
	}
	
	protected abstract void initContent(); //used in the subclasses to initialize all the views

	public void addToTheEndOfSelectionList(UIView view){
		viewsForSelection.add(view);
	}
	
	private void navigateToNextView(){
		selectedView++;
		selectedView = selectedView % viewsForSelection.size();
	}
	private void navigateToPrevView(){
		selectedView--;
		selectedView = selectedView % viewsForSelection.size();
	}
	private void actionSelectedView(){
		viewsForSelection.get(selectedView).action();
	}
	
	
	
	
	
//	private void navigateToNextView(){ //limit is used to avoid infinite loops(in case there is no selectable view)
//		if(selectedView == null){
//			selectedView = parentView;
//		}
//		
//		while(selectedView!=null&&!selectedView.isSelectable()){
//			selectedView = selectedView.nextView();
//		}
//		
//	}
//	private void navigateToPrevView(){
//		if(selectedView == null){
//			selectedView = parentView;
//		}//PREV NOT FINISHED
//		while(selectedView!=null&&!selectedView.isSelectable()){
//			selectedView = selectedView.prevView();
//		}
//	}
//	private void actionSelectedView() {
//		selectedView.action();
//	}
}
