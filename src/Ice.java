import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * A sub-class of Block, the class represents all ice blocks in the game
 * @author LiuShijie
 *
 */
public class Ice extends Block{
	// the timer to control move interval
	private int timer;
	// the X position when the ice is motionless
	private float still_x_position;
	// the Y position when the ice is motionless
	private float still_y_position;
	
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Ice(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
		this.still_x_position = x;
		this.still_y_position = y;
		// initialize timer
		this.timer = App.ICE_TIME;
	}
	
	/**
	 * judge whether the ice can move to the given position
	 * @param new_x_position New X position
	 * @param new_y_position New Y position
	 * @param sprites All sprites in the game
	 * @return true for can move, false for can not move
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
     * overriding update
     */
    @Override
	public void update(Input input, int delta, World world) {
		
		ArrayList<Sprite> sprites = world.getSprites();
		// if the ice is motionless, assign position to still_position
		if(this.getDir() == App.EMPTY) {
			this.still_x_position = this.getXpostion();
			this.still_y_position = this.getYpostion();
		}
		// if the ice is moving, reduce the timer
		if(this.getDir() != App.EMPTY && timer > 0) {
			timer -= delta;
		}
		// if the time is up, make a move
		if(this.getDir() != App.EMPTY && timer <= 0) {
			// compute the new position
			float new_x_position = Sprite.new_x_position(this.getXpostion(), this.getDir());
    		    float new_y_position = Sprite.new_y_position(this.getYpostion(), this.getDir());
        		// if can move, move
        		if (canMove(new_x_position, new_y_position, sprites)) {
        			this.moveTo(new_x_position, new_y_position);
        		}
        		// if can not, set direction
        		else {
        			this.setDir(App.EMPTY);
        		}
        		// reset the timer
        		timer = App.ICE_TIME;
		}
	}
	/**
	 * overriding addHistory in Sprite
	 * only record positions when the ice is still
	 */
	@Override
	public void addHistory() {
	    super.getXhistory().push(still_x_position);
	    super.getYhistory().push(still_y_position);
    }

}
