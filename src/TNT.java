import java.util.ArrayList;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Block, the class represents all TNT blocks in the game
 * @author LiuShijie
 *
 */
public class TNT extends Block{
    
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public TNT(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
	}
	 /**
	  * add a Explosion sprite into the sprites in the game
	  * @param sprites All sprites in the game
	  */
	public void makeExplore(ArrayList<Sprite> sprites) {
		try {
			sprites.add(new Explosion("res/explosion.png", this.getXpostion(), this.getYpostion()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
