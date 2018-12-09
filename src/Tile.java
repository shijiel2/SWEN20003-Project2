import org.newdawn.slick.SlickException;
/**
 * A sub-class of Sprite, the class represents all tiles in the game
 * @author LiuShijie
 *
 */
public class Tile extends Sprite {
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Tile(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
	}

}
