package game.graphics;

public class Animator implements Sprite{
	
	//(n) es el numero de sprites en fila, (size) tamaño del sprite, (act) id del sprite actual en el array
	private int x,y,width,height;
	public int n,act, rate; // rate es el numero de frames que tarda en cambiar de sprite  
	public Spritesheet spriteSheet; //subSpritesheet solo con la fila de movimientos del sprite a animar
	public SingleSprite sprites[], actSprite; // array de sprites en total y sprite actual en el que se encuentra
	
	private long delta=0;
	
	public Animator(int w,int h,int x, int y,int n,Spritesheet spriteSheet, int r){
		this.x=x;
		this.y=y;
		this.width=w;
		this.height=h;
		rate = r;
		this.sprites =new SingleSprite[n];
		this.spriteSheet = spriteSheet;
		slice();
		act=0;
		actSprite=sprites[act];
	}
	
	public Animator(int size,int x, int y,int n,Spritesheet spriteSheet, int r){
		this.x=x;
		this.y=y;
		this.width=size;
		this.height=size;
		rate = r;
		this.sprites =new SingleSprite[n];
		this.spriteSheet = spriteSheet;
		slice();
		act=0;
		actSprite=sprites[act];
	}
	
	
	public void slice(){
		for (int i=0;i<sprites.length;i++){
			sprites[i]= new SingleSprite(width ,height , x+(i*(width/16)) ,y , spriteSheet);
		}
	}
	
	//pasa al siguiente sprite
	public SingleSprite updateSprite(){
		if(act<sprites.length-1){
			act++;
			actSprite=sprites[act];
		}else{
			act=0;
			actSprite=sprites[act];
		}
		return actSprite;
	}
	
	public SingleSprite getActual(){
		return actSprite;
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
		actSprite=sprites[0];
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
		for(int i = 0; i < sprites.length ; i++){
			sprites[i].Flip();
		}
	}
}
