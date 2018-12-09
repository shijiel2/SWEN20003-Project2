import org.newdawn.slick.SlickException;
/**
 * A sub-class of Sprite, the class represents all explosion in the game
 * @author LiuShijie
 *
 */
public class Explosion extends Sprite{
	// the timer to control explosion effect time
	private int timer;
	
	/**
     * constructor
     * @param image_src Image sourse
     * @param x X coordinate
     * @param y Y coordinate
     * @throws SlickException
     */
	public Explosion(String image_src, float x, float y) throws SlickException {
		 super(image_src, x, y);
		// initialize timer
		 this.timer = App.EXPLORE_TIME;
	}
	
	/**
	 * reduce the time passed by
	 * @param delta The time passed by
	 */
	public void timePassBy(int delta) {
		this.timer -= delta;
	}
	
	/**
	 * judge whether the time is up
	 * @return true for time is up, false for not
	 */
	public boolean timeOver() {
		if(this.timer <= 0) {
			return true;
		}
		return false;
	}

}
