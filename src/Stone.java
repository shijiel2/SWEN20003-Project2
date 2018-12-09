
import org.newdawn.slick.SlickException;

/**
 * A sub-class of Block, the class represents all stone blocks in the game
 * @author LiuShijie
 *
 */
public class Stone extends Block {
    
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Stone(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
	}
	
}
