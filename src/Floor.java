import org.newdawn.slick.SlickException;

/**
 * A sub-class of Tile, the class represents all floor in the game
 * @author LiuShijie
 *
 */
public class Floor extends Tile {
	
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Floor(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
	}
	
 }