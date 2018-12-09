import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Tile, the class represents all target in the game
 * @author LiuShijie
 *
 */
public class Target extends Tile {
	// indicate whether the target is occupied
	private boolean isOccupied;
    
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Target(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
		this.isOccupied = false;
	}
	
	/**
	 * overriding the update in Sprite
	 */
	@Override
	public void update(Input input, int delta, World world) {
		// refer to sprites in world
		ArrayList<Sprite> sprites = world.getSprites();
		// set to occupied if should
	    for(Sprite sprite: sprites) {
		    if(sprite.getXpostion() == this.getXpostion() && sprite.getYpostion() == this.getYpostion() && sprite instanceof Block) {
			    this.isOccupied = true;
			    return;
		    }
	    }
	    this.isOccupied = false;
    }
	
	/**
	 * getter of isOccipied
	 * @return boolean of isOccupied
	 */
	public boolean getOccupied() {
		return isOccupied;
	}

}
