
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class to represent the entire game world
 */
public class World {	
	// all the sprites in the world
	private ArrayList<Sprite> sprites;
	// current move
	private int currentMove;
	// current level
	private int currentLevel;
	
	/**
	 * The constructor to initialize the game world
	 */
	public World() {
		//initialization
		this.currentLevel = 0;
		this.currentMove = 0;
		// load sprites
		this.sprites = Loader.loadSprites("res/levels/" + Integer.toString(currentLevel) + ".lvl");
	}
	
	/**
	 * Update the entire world
	 * @param input The command entered
	 * @param delta Time passed since last frame (milliseconds)
	 */
	public void update(Input input, int delta) {
		
		Sprite crackedwall = null;
		Sprite tnt = null;
		Sprite explosion = null;
		
		// restart this level
		if(input.isKeyPressed(Input.KEY_R)) {
			restart();
		}
		
		// undo last move
		if(input.isKeyPressed(Input.KEY_Z) && currentMove > 0) {
			loadHistory();
			this.currentMove --;
		}
		
		// jump to next level
		if(input.isKeyPressed(Input.KEY_N)) {
			nextLevel();
		}
		
		// update all sprites and find sprites which need to handle separately(TNT, CrackedWall and Explosion)
		for (Sprite sprite: sprites) {
			sprite.update(input, delta, this);
			
			if(sprite.getClass() == CrackedWall.class && ((CrackedWall)sprite).getCracked()) {
				crackedwall = sprite;
			}
			if(sprite.getClass() == TNT.class) {
				tnt = sprite;
			}
			if(sprite.getClass() == Explosion.class) {
				explosion = sprite;
			}
		}
		
		// cracked wall has been destroyed, then remove them
		if(crackedwall != null) {
			((TNT)tnt).makeExplore(sprites);
			sprites.remove(crackedwall);
			sprites.remove(tnt);
		}
		// show explosion effect and remove it
		if(explosion != null) {
			((Explosion)explosion).timePassBy(delta);
			if(((Explosion)explosion).timeOver()) {
				sprites.remove(explosion);
			}
		}
		
		// check if this level is finished
		if(checkWin()) {
			nextLevel();
		}
		
	}
	
	/**
	 * Render the entire world
	 * @param g The Slick graphics object, used for drawing
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException {
		// render all sprites in the world
		for (Sprite sprite: sprites) {
			sprite.render(g);
		}
	}
	
	/**
	 * getter of sprites
	 * @return an ArrayList of sprites
	 */
	public ArrayList<Sprite> getSprites() {
		return this.sprites;
	}
	
	/**
	 * restart this level
	 */
	public void restart() {
		this.sprites = Loader.loadSprites("res/levels/" + Integer.toString(currentLevel) + ".lvl");
		this.currentMove = 0;
	}
	
	/**
	 * start next level
	 */
	public void nextLevel() {
		if(this.currentLevel < App.MAX_LEVEL) {
		    currentLevel += 1;
		    currentMove = 0;
		    this.sprites = Loader.loadSprites("res/levels/" + Integer.toString(currentLevel) + ".lvl");
	    }
	}
	
	/**
	 * check if this level is finished
	 * @return a boolean: true for finished, false for unfinished
	 */
	public boolean checkWin() {
		for(Sprite sprite: sprites) {
			if (sprite.getClass() == Target.class) {
				if (!((Target)sprite).getOccupied()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * add a move and record history
	 */
	public void addMove() {
		this.currentMove ++;
		addHistory();
	}
	
	/**
	 * getter of currentMove
	 * @return the integer value of current move
	 */
	public int getMove() {
		return this.currentMove;
	}
	
	/**
	 * record this moment and add to history stack
	 */
	public void addHistory() {
		for(Sprite sprite: sprites) {
			sprite.addHistory();
		}
	}
	
	/**
	 * load a history record from history stack
	 */
	public void loadHistory() {
		for(Sprite sprite: sprites) {
			sprite.loadHistory();
		}
	}
	
}
