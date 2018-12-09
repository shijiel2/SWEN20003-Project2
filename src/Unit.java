import java.util.ArrayList;

import org.newdawn.slick.SlickException;
/**
 * A sub-class of Sprite, the class represents all units in the game
 * @author LiuShijie
 *
 */
public class Unit extends Sprite{
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Unit(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
	}
	
	/**
	 * detect whether this unit is in the same position as player
	 * @param sprites All sprites in the world
	 * @return true for in the same position, false for not
	 */
	public boolean catchPlayer(ArrayList<Sprite> sprites) {
	    // search through the sprites
		for(Sprite sprite: sprites) {
	    	    if(sprite.getXpostion() == this.getXpostion() && sprite.getYpostion() == this.getYpostion() && sprite.getClass() == Player.class) {
	    	    	    return true;
	    	    }
	    }
	    return false;
	}

}
