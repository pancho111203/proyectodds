package game.level.tiles;

import game.graphics.Sprite;
import game.graphics.Spritesheet;

public class SpriteTileFacade {

	// en esta clase tenemos toda la funcionalidad que hace falta por ahora
	// respecto a tiles y sprites
	// no he incluido funciones como añadir un sprite por separado porque es
	// algo que nunca se hara en la practica,
	// ya que todos los tiles que se añadan tiene que ser asociados a un tile al
	// menos. Lo mismo pasa con añadir
	// un un tile sin asociarlo a un hex


	private LevelSprites ls;
	private LevelTiles lt;

	public SpriteTileFacade() {
		lt = new LevelTiles();
		ls = new LevelSprites();
	}

	public void initSpriteOnTileOnHex(String name, int x, int y, Spritesheet sheet, int h) {
		// metodo combo que inicializa un sprite y lo coloca en un tile(con el
		// mismo nombre),
		// el cual se asigna a un hex
		Sprite aux = ls.createSprite(name, x, y, sheet);
		
		lt.addTile(name, aux);
		lt.addHex(h, name);
	}
	
	public void initSpriteOnTileOnHex(String name, int x, int y, Spritesheet sheet, int h, int state1,
			int state2, int state3, int state4) { // inicializa estados
		// metodo combo que inicializa un sprite y lo coloca en un tile(con el
		// mismo nombre),
		// el cual se asigna a un hex
		Sprite aux = ls.createSprite(name, x, y, sheet);

		lt.addTile(name, aux, state1,state2,state3,state4);
		lt.addHex(h, name);
	}

	public void initSpriteOnTileOnHex(String name, int x, int y, int n, Spritesheet sheet, int r, int h) {
		// lo mismo para anims
		Sprite aux = ls.createAnim(name, x, y, n, sheet, r);

		lt.addTile(name, aux);
		lt.addHex(h, name);
	}

	public void initSpriteOnTileOnHex(String name, int x, int y, int n, Spritesheet sheet, int r, int h, int state1,
			int state2, int state3, int state4) { // inicializa estados
		
		Sprite aux = ls.createAnim(name, x, y, n, sheet, r);

		lt.addTile(name, aux, state1,state2,state3,state4);
		lt.addHex(h, name);
	}

	public void initSpriteOnTileOnHex(String name, int color, int h) {
		// lo mismo para sprites de un color liso
		Sprite aux = ls.createSprite(name, color);

		lt.addTile(name, aux);
		lt.addHex(h, name);
	}

	public void initTileOnHex(int h, String name, String sprite, boolean animation) {
		// añade un tile con sprite y name custom, y lo asigna a un hex
		// si animation es true, se carga una animacion (SI NO EXISTE DEVUELVE
		// NULL)
		Sprite s;
		if (!animation) {
			// load SingleSprite
			s = ls.getSprite(sprite);
		} else {
			// load Animator
			s = ls.getAnim(sprite);
		}

		lt.addTile(name, s);
		lt.addHex(h, name);
	}

	public void initHex(int h, String name) { // añade un tile creado
												// previamente a el hex
		lt.addHex(h, name);
	}

	public void updateAnims() {
		ls.updateAnims();
	}

	public Tile getTile(int h) { // con el valor hex devuelve el Tile

		return lt.getTile(h);
	}

}
