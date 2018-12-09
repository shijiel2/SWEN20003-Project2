import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Tile, the class represents all cracked wall in the game
 * @author LiuShijie
 *
 */
public class CrackedWall extends Tile{
	// Indicate whether the wall is destoyed or not
	private boolean isCracked;
	
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public CrackedWall(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
		 this.isCracked = false;
	}
	
	/**
	 * update Cracked Wall state
	 */
	@Override
    public void update(Input input, int delta, World world) {
    	    // refer to sprites
        ArrayList<Sprite> sprites = world.getSprites();
	    // check if the wall was destroyed by TNT or not
    	    for(Sprite sprite: sprites) {
			if(sprite.getXpostion() == this.getXpostion() && sprite.getYpostion() == this.getYpostion() && sprite.getClass() == TNT.class) {
				this.isCracked = true;
			}
		}
	}
    
    /**
     * getter of isCracked
     * @return boolean of isCracked
     */
    public boolean getCracked() {
    	    return isCracked;
    }

}
