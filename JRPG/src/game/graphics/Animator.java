package game.graphics;

import java.util.ArrayList;

public class Animator implements Sprite{
	
	//(n) es el numero de sprites en fila, (size) tamaño del sprite, (act) id del sprite actual en el array
	private int x,y,width,height;
	public int n,act, rate,size; // rate es el numero de frames que tarda en cambiar de sprite  
	public Spritesheet spriteSheet; //subSpritesheet solo con la fila de movimientos del sprite a animar
	public SingleSprite actSprite;
	public ArrayList<SingleSprite> sprites; // array de sprites en total y sprite actual en el que se encuentra
	private boolean onlyOneRun = false;
	private long delta=0;
	
	public Animator(int w,int h,int x, int y,int n,Spritesheet spriteSheet, int r,boolean oneRunOnly){
		onlyOneRun = oneRunOnly;
		this.x=x;
		this.y=y;
		this.width=w;
		this.height=h;
		rate = r;
		size=n;
		this.sprites = new ArrayList<SingleSprite>();
		this.spriteSheet = spriteSheet;
		slice();
		act=0;
		actSprite=sprites.get(act);
	}
	
	public Animator(int size,int x, int y,int n,Spritesheet spriteSheet, int r, boolean oneRunOnly){
		onlyOneRun = oneRunOnly;
		this.x=x;
		this.y=y;
		this.width=size;
		this.height=size;
		rate = r;
		size=n;
		this.sprites = new ArrayList<SingleSprite>();
		this.spriteSheet = spriteSheet;
		slice();
		act=0;
		actSprite=sprites.get(act);
	}
	
	public void slice(){
		for (int i=0;i<size;i++){
			addSprite( new SingleSprite(width ,height , x+i ,y , spriteSheet));
		}
	}
	
	//pasa al siguiente sprite
	public SingleSprite updateSprite(){
		if(act<sprites.size()-1){
			act++;
			actSprite=sprites.get(act);
		}else if(!onlyOneRun){
			
			act=0;
			actSprite=sprites.get(act);
		}
		return actSprite;
	}
	
	public SingleSprite getActual(){
		return actSprite;
	}
	public SingleSprite getFirst(){
		return sprites.get(0);
	}
	
	public void addSprite(Sprite e){
		if(e instanceof SingleSprite){
			sprites.add((SingleSprite)e);
			size++;
		}
		else System.err.println("Only SingleSprites accepted on Animator");
	}
	
	public void removeSprite(Sprite e){
		sprites.remove(e);
		size--;
	}
	
	public Sprite getSprite(int i){
		return sprites.get(i);
	}
	
	
	
	public void update(){
		
		//(done) se llama cada frame y va sumando un contador que llegado cierto numero le indica qu cambie al prox sprite
		//en el jugador esta funcion solo se llama para el animador actual(izq si se esta moviendo a la izquiea, etc)
		// el los tiles se llamara siempre desde la update() de el Level a todos
		delta+=1;
		
		if(delta>=rate){
			updateSprite();
			delta=0;
		}
		
	}
	
	public void startAgain(){
		//partiendo de que pongamos en el spritesheet los tiles en orden...
		act = 0;
		actSprite=getFirst();
	}
	
	public void changeRate(int r){
		rate = r;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void FlipAll() {
		for(int i = 0; i < sprites.size() ; i++){
			sprites.get(i).Flip();
		}
	}
}
