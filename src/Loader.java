import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

/**
 * The class to handle loading Sprites from CSV file
 */
public class Loader {
	
	// width of the map in tile counts
	private static int map_width;
	// height of the map in tile counts
	private static int map_hight;

	
	/**
	 * Converts a tile coordinate to a world coordinate
	 * @param dim A char ('x' or 'y') to indicate the dimension 
	 * of this coordinate
	 * @param tile_pos The tile coordinate
	 * @return the corresponding world coordinate in float
	 */
	private static float toWorldCoordinate (char dim, int tile_pos) {
		if(dim == 'x') {
			return (float) App.TILE_SIZE * tile_pos + ((App.SCREEN_WIDTH - map_width * App.TILE_SIZE) / 2) + App.TILE_SIZE /2;
		}
		else {
			return (float) App.TILE_SIZE * tile_pos + ((App.SCREEN_HEIGHT - map_hight * App.TILE_SIZE) / 2) + App.TILE_SIZE /2;
		}
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename The CSV file path to load the sprites
	 * @return an ArrayList of sprites
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		
		ArrayList<Sprite> sprites = new ArrayList<>();
		
		// read through the CSV file to load Sprites
		try (BufferedReader br =
	        new BufferedReader(new FileReader(filename))) {
	        String text;
	        // read the first line
	        text = br.readLine();
			String[] split_text = text.split(",");
			map_width = Integer.parseInt(split_text[0]);
			map_hight = Integer.parseInt(split_text[1]);
	        // Start to load the sprites
	        while ((text = br.readLine()) != null) {
	            // parse the information 
	        	    split_text = text.split(",");
	            String type = split_text[0];
	            int tile_x = Integer.parseInt(split_text[1]);
	            int tile_y = Integer.parseInt(split_text[2]);
	            // for each sprite create its object and complete isBlockedMap
	            switch (type) {
	                case "floor":
	                	    sprites.add(new Floor("res/floor.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                	    break;
	                case "wall":
                	        sprites.add(new Wall("res/wall.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
                	        break;
	                case "stone":
            	            sprites.add(new Stone("res/stone.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
            	            break;
	                case "target":
            	            sprites.add(new Target("res/target.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
            	            break;
	                case "player":
    	                    sprites.add(new Player("res/player_left.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
    	                    break;
	                case "door":
	                    sprites.add(new Door("res/door.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "switch":
	                    sprites.add(new Switch("res/switch.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "cracked":
	                    sprites.add(new CrackedWall("res/cracked_wall.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "ice":
	                    sprites.add(new Ice("res/ice.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "tnt":
	                    sprites.add(new TNT("res/tnt.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "skeleton":
	                    sprites.add(new Skeleton("res/skull.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "rogue":
	                    sprites.add(new Rogue("res/rogue.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
	                case "mage":
	                    sprites.add(new Mage("res/mage.png", toWorldCoordinate('x', tile_x), toWorldCoordinate('y', tile_y)));
	                    break;
        	            default:
        	            	    break;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return sprites;
	}
}
