
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
/**
 * The class represents all sprites in the game 
 * @author LiuShijie
 *
 */
public class Sprite {	
	
	// the x position in world coordinate
	private float x_position;
	// the x position in world coordinate
	private float y_position;
	// the direction of this sprite
	private char dir;
	// the image to represent the sprite
	private Image image;
	
	/**
	 * The constructor
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Sprite(String image_src, float x, float y) throws SlickException {
		this.x_position = x;
		this.y_position = y;
		this.dir = App.EMPTY;
		this.image = new Image(image_src);
	}
	
	/**
	 * update, which will be overriding in sub-classes
	 * @param input The command entered
	 * @param delta Time passed since last frame (milliseconds)
	 * @param world The world of this game 
	 */
	public void update(Input input, int delta, World world) {
		
	}
	
	/**
	 * add history to history stack, which will be overriding in sub-classes
	 */
	public void addHistory() {
		
	}
	
	/**
	 * load history from history stack, which will be overriding in sub-class
	 */
	public void loadHistory() {
		
	}
	
	/**
	 * compute the next Y position for sprites using 'one-move along direction' algorithm
	 * @param y_position The original Y position 
	 * @param dir The moving direction
	 * @return the float number of next Y position
	 */
	public static float new_y_position (float y_position, char dir) {
		
		if (dir == App.UP) {
			return y_position -= App.TILE_SIZE;
		}
		else if (dir == App.DOWN) {
			return y_position += App.TILE_SIZE;
		}
		else {
			return y_position;
		}
	}
	
	/**
	 * compute the next X position for sprites using 'one-move along direction' algorithm
	 * @param x_position The original X position 
	 * @param dir The moving direction
	 * @return the float number of next X position
	 */
    public static float new_x_position (float x_position, char dir) {
		
    	    if (dir == App.LEFT) {
			return x_position -= App.TILE_SIZE;
		}
    	    else if (dir == App.RIGHT) {
			return x_position += App.TILE_SIZE;
		}
    	    else {
    	    	    return x_position;
    	    }
	}
    
    /**
     * compute the opposite direction of given direction
     * @param dir The given direction
     * @return a char to indicate the direction
     */
    public static char reverseDir(char dir) {
    	    if (dir == App.LEFT) {
			return App.RIGHT;
		}
    	    else if (dir == App.RIGHT) {
			return App.LEFT;
		}
    	    else if (dir == App.UP) {
    			return App.DOWN;
    		}
    	    else if (dir == App.DOWN) {
    	    	    return App.UP;
    	    }
    	    else {
    	    	    return App.EMPTY;
    	    }
    }
	
    /**
     * move the sprite to given position
     * @param new_x_position The new X position
     * @param new_y_position The new Y position
     */
    public void moveTo(float new_x_position, float new_y_position) {
		this.setXpostion(new_x_position);
		this.setYpostion(new_y_position);
	}
	
	/**
	 * Render the sprite
	 * @param g The Slick graphics object, used for drawing
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException {
		this.image.drawCentered(this.x_position, this.y_position);
	}
	
	/**
	 * get the X position of a instance using method for privacy
	 * @return X axe world coordinate in float
	 */
	public float getXpostion () {
		return this.x_position;
	}
	
	/**
	 * get the Y position of a instance using method for privacy
	 * @return Y axe world coordinate in float
	 */
	public float getYpostion () {
		return this.y_position;
	}
	
	/**
	 * set the X position of a instance using method for privacy
	 * @param new_x_position The new X axe world coordinate assign to this sprite
	 */
	public void setXpostion (float new_x_position) {
		if (new_x_position >= 0 && new_x_position <= App.SCREEN_WIDTH) {
			this.x_position = new_x_position;
		}
	}
	
	/**
	 * set the Y position of a instance using method for privacy
	 * @param new_y_position The new Y axe world coordinate assign to this sprite
	 */
	public void setYpostion (float new_y_position) {
		if (new_y_position >= 0 && new_y_position <= App.SCREEN_HEIGHT) {
			this.y_position = new_y_position;
		}
	}
	
	/**
	 * setter of the Image
	 * @param image_src New image source
	 * @throws SlickException
	 */
	public void setImage (String image_src) throws SlickException {
		this.image = new Image(image_src);
	}
	
	/**
	 * setter of the direction
	 * @param dir New direction
	 */
	public void setDir(char dir) {
		this.dir = dir;
	}
	
	/**
	 * getter of the direction
	 * @return a char indicates the direction
	 */
	public char getDir() {
		return this.dir;
	}
	
}