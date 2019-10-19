// Imports Listed Here:
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Setups up the Rooms for a level & updates room's value
 * 
 * @author Nathan Turner
 *
 */
public class RoomSetup {
	
	// Creating random object
	Random rand = new Random();
	
	// Variables Created Here:
	
	// Level Variable
	private int level = 1;
	
	// RoomsPerLevel Variable
	private int roomsPerLevel = 6 * (level/2);
	
	// RoomList -> Creates a Dictionary that tells where all the rooms are compared to other rooms
	private Map<String, String> allRooms = new HashMap<String, String>();
	
	// The chance a room will be added in a certain direction
	private int roomAddChance = random(10);
	
	// Directions
	private String[] directions = {"up", "right", "left", "right"};
	
	// Final values to use for percentages
	private final int ITEM_CHANCE_FOR_CLEAR_ROOM = random(10);
	private final int CHEST_ROOM_CHANCE = random(25);	
	
	
	/**
	 * Creates random number in range
	 * 
	 * @param range
	 * @return rand.nextInt(range)
	 */
	public int random(int range) {
		range += 1;
		return rand.nextInt(range);
	}
	
	
	/**
	 * Gets everything in the room so that the player can
	 * See what's in it
	 * 
	 * @return roomList
	 */
	public Map<String, Integer> getRoom() {
		// Will return a Dictionary with a String pointer to an Integer
		
		// Creates Dictionary
		Map<String, Integer> roomList = new HashMap<String, Integer>();
		
		// Returns dictionary 
		return roomList;
	}
	
	
	/**
	 * Gets all the rooms relative to each other
	 * 
	 * @return roomList
	 */
	public Map<String, String> getAllRooms() {
		// Will return a Dictionary with a String to String 
		
		// Creates Dictionary
		Map<String, String> roomList = new HashMap<String, String>();
		
		// returns Dictionary
		return roomList;
	}
	
	
	/**
	 * Adds Items to room
	 * 
	 * @param item
	 */
	public void addToRoom(Map<String, Integer> item) {
		
	}
	
	
	/**
	 * Sets up all the rooms for the level
	 * 
	 */
	public void initializeLevel() {
		
		// Initialize level in here:
		
		// Clears any the previous level if there was one
		if(level > 1) {
			allRooms.clear();
		}
		
		// Adds a middle room for sure every time
		allRooms.put("Start Room", "start");
		
		// Now does some math to see where it should put rooms
		if(roomAddChance == roomAddChance) {
			System.out.println("TRUE!");
		}
		
		
	}
	
	
	/**
	 * Gets the current level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	
	/**
	 * Sets new level to the next one up
	 * 
	 * @param newLevel
	 */
	public void nextLevel() {
		level += 1;
	}

}
