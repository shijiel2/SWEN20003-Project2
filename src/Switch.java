import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Tile, the class represents all switch in the game
 * @author LiuShijie
 *
 */
public class Switch extends Tile{
	
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Switch(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
	}
	
	/**
	 * overriding update in Sprite
	 */
	@Override
    public void update(Input input, int delta, World world) {
	    // refer to sprites in world
        	ArrayList<Sprite> sprites = world.getSprites();
    	    Sprite door = null;
    	    // find the door
    	    for(Sprite sprite: sprites) {
    	    	    if(sprite.getClass() == Door.class) {
    	    	    	    door = sprite;
    	    	    }
    	    }
    	    // search through the sprites and open the door if needed
	    for(Sprite sprite: sprites) {
		    if(sprite.getXpostion() == this.getXpostion() && sprite.getYpostion() == this.getYpostion() && sprite instanceof Block) {
			    ((Door)door).open();
			    return;
		    }
	    }
	    ((Door)door).close();
    }

}
