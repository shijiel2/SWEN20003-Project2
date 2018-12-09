import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * A sub-class of Unit, the class represents mage in the game
 * @author LiuShijie
 *
 */
public class Mage extends Unit{
	
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Mage(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
	}
    
	/**
	 * judge whether the mage can make next move
	 * @param world The game world
	 * @return true for can move, false for can not
	 */
    public boolean canMove(World world) {
		// refer to the sprites
       	ArrayList<Sprite> sprites = world.getSprites();
       	boolean result = true;
		// compute the new position
		float new_x_position = new_position(world, App.X);
		float new_y_position = new_position(world, App.Y);
		// assign result in different cases
	    for(Sprite sprite: sprites) {
		    if (sprite.getXpostion() == new_x_position && sprite.getYpostion() == new_y_position) {
			    
		    	    if(sprite.getClass() == Wall.class) {
					result = false;
				}
				else if(sprite.getClass() == CrackedWall.class && !((CrackedWall) sprite).getCracked()) {
					result = false;
				}
				else if(sprite.getClass() == Door.class && !((Door) sprite).getOpened()) {
					result = false;
				}
				else if(sprite instanceof Block) {
					result = false;
				}
			}
		}
		return result;
	}
    
    /**
     * move the mage to its next position
     * @param world The game world
     */
    public void moveTo(World world) {
	    super.moveTo(new_position(world, App.X), new_position(world, App.Y));
    }
    
    // helper function 
    // accept param dist The distance
    // return -1 if distance < 0, and 1 otherwise
    private int sgn(float dist) {
    	    if(dist < 0) {
    	    	    return -1;
    	    }
    	    else {
    	        return 1;
    	    }
    }
    
    /**
     * check mage state
     */
    @Override
    public void update(Input input, int delta, World world) {
	    // restart if it catch player
        if(this.catchPlayer(world.getSprites())) {
	        world.restart();
        }
    }
    
    /**
     * compute the new position using the given algorithm
     * @param world The game world
     * @param dim The dimension (X or Y) need to compute
     * @return the new position of desired dimension
     */
    public float new_position(World world, char dim) {
        // refer to the sprites  
    	    ArrayList<Sprite> sprites = world.getSprites();
       	Sprite player = null;
	    // get original position
	    float m_x_position = this.getXpostion();
	    float m_y_position = this.getYpostion();
        // find player
        for(Sprite sprite: sprites) {
    	        if(sprite.getClass() == Player.class) {
    	    	        player = sprite;
    	        }
        }
        // compute new position
        if(player != null) {
    	        float p_x_position = player.getXpostion();
    	        float p_y_position = player.getYpostion();
    	    
    	        float dist_x = p_x_position - m_x_position; 
    	        float dist_y = p_y_position - m_y_position;
    	    
    	        if(Math.abs(dist_x) > Math.abs(dist_y)) {
    	    	        m_x_position += App.TILE_SIZE * sgn(dist_x);
    	        }
    	        else {
    	    	        m_y_position += App.TILE_SIZE * sgn(dist_y);
    	        }
        }
        // return desired dimension position
        if(dim == App.X) {
        	    return m_x_position;
        }
        else{
    	        return m_y_position;
        }
       	
    }
    
}
