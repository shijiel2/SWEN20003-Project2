import org.newdawn.slick.SlickException;
/**
 * A sub-class of Tile, the class represents all door in the game
 * @author LiuShijie
 *
 */
public class Door extends Tile{
	// indicate whether the door is open or close
	private boolean isOpened;
	// the image it should use when it is open
	private String opened_image_res;
	// the image it should use when it is close
	private String closed_image_res;
	
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Door(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
		 // initialization
		 this.isOpened = false;
		 this.opened_image_res = "res/floor.png";
		 this.closed_image_res = "res/door.png";
	}
	
	/**
	 * getter of isOpened
	 * @return boolean of isOpened
	 */
	public boolean getOpened() {
		return isOpened;
	}
	
	/**
	 * open the door
	 */
	public void open() {
		// set isOpened to true
		isOpened = true;
		// change the image 
		try {
			this.setImage(this.opened_image_res);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * close the door
	 */
	public void close() {
		// set isOpened to false 
		isOpened = false;
		// change the image
		try {
			this.setImage(this.closed_image_res);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
