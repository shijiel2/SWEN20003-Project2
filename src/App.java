/**
 * Project skeleton for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    /** a char to indicate UP direction */
    public static final char UP = 'u';
    /** a char to indicate DOWN direction */
    public static final char DOWN = 'd';
    /** a char to indicate LEFT direction */
    public static final char LEFT = 'l';
    /** a char to indicate RIGHT direction */
    public static final char RIGHT = 'r';
    /** a char to indicate NO direction */
    public static final char EMPTY = 'e';
    /** a char to indicate X dimension */
    public static final char X = 'x';
    /** a char to indicate Y dimension */
    public static final char Y = 'y';
    /** explosion time */
    public static final int EXPLORE_TIME = 400;
    /** time interval between every ice's movement */
    public static final int ICE_TIME = 250;
    /** time interval between every skull's movement */
    public static final int SKULL_TIME = 1000;
    /** max number of levels */
    public static final int MAX_LEVEL = 5;
    
    
    private World world;

    public App()
    {    	
        super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
    	world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
    	world.render(g);
    	g.drawString("Moves: " + world.getMove(), 100, 100);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(true);
        
        app.setTargetFrameRate(30);
        
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}