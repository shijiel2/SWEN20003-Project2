import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Unit, the class represents skeleton in the game
 * @author LiuShijie
 *
 */
public class Skeleton extends Unit{
	// timer to control move interval
	private int timer;
    
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Skeleton(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
		// initialize direction and timer
		this.setDir(App.UP);
		this.timer = App.SKULL_TIME;
	}
	
	/**
	 * judge whether the skull can move to the given position
	 * @param new_x_position New X position 
	 * @param new_y_position New Y position
	 * @param sprites All sprites in the game
	 * @return true for can move, false for can not
	 */
    public boolean canMove(float new_x_position, float new_y_position, ArrayList<Sprite> sprites) {
		
		boolean result = true;
		// assign result for different cases
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
     * overriding update in Sprite
     */
    @Override
    public void update(Input input, int delta, World world) {
		// refer to sprites in world
    	    ArrayList<Sprite> sprites = world.getSprites();
    	    // reduce timer
		if(timer > 0) {
			timer -= delta;
		}
		// if time is up, make move
		if(timer <= 0) {
			// reset timer 
			timer = App.SKULL_TIME;
			// compute new position
			float new_x_position = this.getXpostion();
		    float new_y_position = Sprite.new_y_position(this.getYpostion(), this.getDir());
        		// if can move, make the move
        		if (canMove(new_x_position, new_y_position, sprites)) {
        			moveTo(new_x_position, new_y_position);
        		}
        		// if cannot move, reverse direction and reset timer
        		else {
        			this.setDir(Sprite.reverseDir(this.getDir()));
        			timer = 0;
        		}
		}
		
		if(this.catchPlayer(sprites)) {
			world.restart();
		}
		
	}

}
