import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.SlickException;
/**
 * A sub-class of Sprite, the class represents all blocks in the game
 * @author LiuShijie
 *
 */
public class Block extends Sprite {
	// history stacks to store history information
	private Stack<Float> history_x_position;
	private Stack<Float> history_y_position;
    
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Block(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
		 // create the history stack
		 this.history_x_position = new Stack<>();
		 this.history_y_position = new Stack<>();
		 // record the first position
		 this.history_x_position.push(x);
		 this.history_y_position.push(y);
	}
	
	/**
	 * push the block in the direction as given
	 * @param dir The pushing direction 
	 */
	public void pushedTo(char dir) {
		// assign new position
		float new_x_position = Sprite.new_x_position(this.getXpostion(), dir);
	    float new_y_position = Sprite.new_y_position(this.getYpostion(), dir);
		// assign new direction
		this.setDir(dir);
		// move the block
		moveTo(new_x_position, new_y_position);
	}
	
	/**
	 * judge whether the block can be pushed in given direction
	 * @param dir The pushing direction
	 * @param sprites ArrayList of all sprites 
	 * @return true for can be pushed, false for can not be pushed
	 */
    public boolean canPushed(char dir, ArrayList<Sprite> sprites) {
		// compute the new position
    	    float new_x_position = Sprite.new_x_position(this.getXpostion(), dir);
	    float new_y_position = Sprite.new_y_position(this.getYpostion(), dir);
		// detect which sprites are in the new position
		for(Sprite sprite: sprites) {
			if (sprite.getXpostion() == new_x_position && sprite.getYpostion() == new_y_position) {
				// different sprite cases
				if(sprite.getClass() == Wall.class) {
					return false;
				}
				else if(sprite.getClass() == CrackedWall.class && this.getClass() != TNT.class) {
					return false;
				}
				else if(sprite.getClass() == Door.class) {
					return ((Door) sprite).getOpened();
				}
				else if(sprite instanceof Block) {
					return false;
				}
				else if(sprite instanceof Unit) {
					return false;
				}
			}
		}
		
		return true;
	}
    
    /**
     * getter of the history stack
     * @return the history stack in Stack type
     */
    public Stack<Float> getXhistory() {
    	    return this.history_x_position;
    }
    
    /**
     * getter of the history stack
     * @return the history stack in Stack type
     */
    public Stack<Float> getYhistory() {
	    return this.history_y_position;
    }
    
    /**
     * add new record into history stack
     */
    public void addHistory() {
	    this.history_x_position.push(this.getXpostion());
	    this.history_y_position.push(this.getYpostion());
    }
    
    /**
     * load history record from history stack
     */
    public void loadHistory() {
    	    this.setXpostion(this.history_x_position.pop());
    	    this.setYpostion(this.history_y_position.pop());
    	    this.setDir(App.EMPTY);
    }

}
