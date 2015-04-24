package game.graphics;

public class Animator {
	
	//(w,h) ancho y alto del subSpritesheet, (n) es el numero de sprites en fila, (size) tamaño del sprite, (act) id del sprite actual en el array
	public int x,y,n,size,act;  
	public Spritesheet spriteSheet; //subSpritesheet solo con la fila de movimientos del sprite a animar
	public Sprite sprites[], actSprite; // array de sprites en total y sprite actual en el que se encuentra
	
	public Animator(int s,int x, int y,int n,Spritesheet spriteSheet){
		this.x=x;
		this.y=y;
		this.size=s;
		this.sprites =new Sprite[n];
		this.spriteSheet = spriteSheet;
		slice();
		act=0;
		actSprite=sprites[act];
	}
	
	//a partir de la posición del primer sprite, su tamaño y el numero de sprites, los saca todos y al array
	public void slice(){
		for (int i=0;i<sprites.length;i++){
			sprites[i]= new Sprite(size,x+(i*size),y+(i*size),spriteSheet);
		}
	}
	
	//pasa al siguiente sprite
	public Sprite updateSprite(){
		if(act<sprites.length-1){
			act++;
			actSprite=sprites[act];
		}else{
			act=0;
			actSprite=sprites[act];
		}
		return actSprite;
	}
	
	public Sprite getActual(){
		return actSprite;
	}
	
	public void update(){
		//TODO se llama cada frame y va sumando un contador que llegado cierto numero le indica qu cambie al prox sprite
		//en el jugador esta funcion solo se llama para el animador actual(izq si se esta moviendo a la izquiea, etc)
		// el los tiles se llamara siempre desde la update() de el Level a todos
	}
	
	public void startAgain(){
		//TODO reinicia el animador para que empiece desde el sprite inicial(util para el jugador, que tiene que empezar la animacion de saltar p ej)
	}
}
