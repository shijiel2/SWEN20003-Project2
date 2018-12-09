import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Unit, the class represents rogue in the game
 * @author LiuShijie
 *
 */
public class Rogue extends Unit {
    
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Rogue(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
		this.setDir(App.LEFT);
	}
	
	/**
	 * judge whether the mage can make next move
	 * @param world The game world
	 * @return
	 */
    public boolean canMove(World world) {
        	// refer to the sprites
       	ArrayList<Sprite> sprites = world.getSprites();
       	boolean result = true;
        // compute the new position
		float new_x_position = Sprite.new_x_position(this.getXpostion(), this.getDir());
		float new_y_position = Sprite.new_y_position(this.getYpostion(), this.getDir());
		char dir = this.getDir();
		// assign result in different cases
	    for(Sprite sprite: sprites) {
		    if (sprite.getXpostion() == new_x_position && sprite.getYpostion() == new_y_position) {
                if(sprite instanceof Block) {
					result = ((Block) sprite).canPushed(dir, sprites);
					if (result) {
						((Block)sprite).pushedTo(dir);
					}
				}
		        	else if(sprite.getClass() == Wall.class) {
					result = false;
				}
				else if(sprite.getClass() == CrackedWall.class && !((CrackedWall) sprite).getCracked()) {
					result = false;
				}
				else if(sprite.getClass() == Door.class && !((Door) sprite).getOpened()) {
					result = false;
				}
			}
		}
		return result;
	}
    
    /**
     * move the mage to its next position
     */
    public void moveTo() {
      	// compute new position
    	    float new_x_position = Sprite.new_x_position(this.getXpostion(), this.getDir());
		float new_y_position = Sprite.new_y_position(this.getYpostion(), this.getDir());
		// move to
		super.moveTo(new_x_position, new_y_position);
    }
    
    /**
     * overriding update in Sprite
     */
    @Override
    public void update(Input input, int delta, World world) {
		
	    if(this.catchPlayer(world.getSprites())) {
		    world.restart();
	    }
    }

}
