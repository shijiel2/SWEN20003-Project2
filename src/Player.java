import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.Input;

/**
 * A sub-class of Unit, the class represents player in the game
 * @author LiuShijie
 *
 */
public class Player extends Unit {
	// history stacks to store history information
	private Stack<Float> history_x_position;
	private Stack<Float> history_y_position;
	
	/**
	 * The constructor 
	 * @param image_src The image source of this sprite
	 * @param x The X axe world coordinate
	 * @param y The Y axe world coordinate
	 * @throws SlickException
	 */
	public Player(String image_src, float x, float y) throws SlickException {
		super(image_src, x, y);
		// create history stacks
		this.history_x_position = new Stack<>();
		this.history_y_position = new Stack<>();
		// record the first position
		this.history_x_position.push(x);
		this.history_y_position.push(y);
	}
	
	/**
	 * judge whether the player can move to the given position
	 * @param new_x_position New X position
	 * @param new_y_position New Y position
	 * @param dir Moving direction
	 * @param world The game world
	 * @return true for can move, false for can not
	 */
	public boolean canMove(float new_x_position, float new_y_position, World world) {
		// refer to the sprites
		ArrayList<Sprite> sprites = world.getSprites();
		boolean result = true;
		// search through the sprites
	    for(Sprite sprite: sprites) {
		    if (sprite.getXpostion() == new_x_position && sprite.getYpostion() == new_y_position) {
		    	    // assign result for different cases
		    	    if(sprite instanceof Block) {
					result = ((Block) sprite).canPushed(this.getDir(), sprites);
					if (result) {
						((Block)sprite).pushedTo(this.getDir());
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
		    	    // the case when the player moves into an enemy
				else if (sprite instanceof Unit && sprite.getClass() != Player.class) {
					world.restart();
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
        
    	    ArrayList<Sprite> sprites = world.getSprites();
    	
    	    // get the original position and reset direction
    		float new_x_position = this.getXpostion();
    		float new_y_position = this.getYpostion();
    		this.setDir(App.EMPTY);
    		boolean hasInput = false;
    			
    		// compute the new position after movement
    		if (input.isKeyPressed(Input.KEY_UP)) {
    			new_y_position -= App.TILE_SIZE;
    			this.setDir(App.UP);
    			hasInput = true;
    		}

    		if (input.isKeyPressed(Input.KEY_DOWN)) {
    			new_y_position += App.TILE_SIZE;
    			this.setDir(App.DOWN);
    			hasInput = true;
    		}
    			
    		if (input.isKeyPressed(Input.KEY_LEFT)) {
    			new_x_position -= App.TILE_SIZE;
    			this.setDir(App.LEFT);
    			hasInput = true;
    		}
    			
    		if (input.isKeyPressed(Input.KEY_RIGHT)) {
    			new_x_position += App.TILE_SIZE;
    			this.setDir(App.RIGHT);
    			hasInput = true;
    		}
    		
    		// if have input...
    		if(hasInput) {
    			
    			Sprite rogue = null;
    			Sprite mage = null;
    			
    			// add a move in World
    			world.addMove();
            // if can move, move
            if(canMove(new_x_position, new_y_position, world)) {
              	moveTo(new_x_position, new_y_position);
	        }
    			// find if there are rogue and mage
    			for(Sprite sprite: sprites) {
    				if(sprite.getClass() == Rogue.class) {
    					rogue = sprite;
    				}
    				if(sprite.getClass() == Mage.class) {
    					mage = sprite;
    				}
    			}
    		    // move rogue if it can move
    		    if(rogue != null) {
	            if(((Rogue)rogue).canMove(world))	{
	                	((Rogue)rogue).moveTo();
	            }
	            else {
	                	rogue.setDir(Sprite.reverseDir(rogue.getDir()));
	            }
	        }
    		    // move mage if it can move
    		    if(mage != null && ((Mage)mage).canMove(world)) {
	            	((Mage)mage).moveTo(world);
	        }
    		}
	}
    
   /**
    * overriding addHistory in Sprite, add record into history stack
    */
	@Override
    public void addHistory() {
    	    this.history_x_position.push(this.getXpostion());
    	    this.history_y_position.push(this.getYpostion());
    }
    
    /**
     * overriding loadHistory in Sprite, load record from history stack
     */
	@Override
    public void loadHistory() {
	    this.setXpostion(this.history_x_position.pop());
	    this.setYpostion(this.history_y_position.pop());
	    this.setDir(App.EMPTY);
    }
    
}
