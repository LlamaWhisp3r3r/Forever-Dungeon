// Imports Listed Here:
import java.util.List;
import java.util.ArrayList;
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
	private Random rand = new Random();
	
	// Variables Created Here:
	
	// Level Variable
	private int level = 1;
	
	// Create variable for what room the program will add to
	private String currentRoom;
	
	// Keeps track of how many rooms were created
	private int roomNumber = 0;
	
	// Total roomsAdded list
	private List<String> totalRoomsAdded = new ArrayList<String>();
	
	// RoomList -> Creates a Dictionary that tells where all the rooms are compared to other rooms
	private Map<String, String> allRooms = new HashMap<String, String>();
	
	// The chance a room will be added in a certain direction
	private int roomAddChance = random(1);
	
	// Directions
	private String[] directions = {"u", "r", "l", "r"};
	private List<String> currentRoomList = new ArrayList<String>();
	
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
	public void playRoom(String room) {
		// Use this method to play a room and go to other rooms connected to it
	}
	
	
	/**
	 * Gets all the rooms relative to each other
	 * 
	 * @return roomList
	 */
	public String getAllRooms() {
		return allRooms.toString(); // Returns all the rooms in a String
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
		
		// RoomsPerLevel Variable
		int roomsPerLevel = 7 * level;
		
		System.out.println("Started Level Initialiazing");
		System.out.println("Room Number is: " + roomNumber + "\nRooms Per Level = " + roomsPerLevel);
		
		// Adds a middle room for sure every time it creates a level
		if(!allRooms.containsKey("Start Room")) {
			allRooms.put("Start Room", "start");
			currentRoom = "Start";	
			System.out.println("Created Start Room");
		} 
		// Gets a Random Room for the current Room
		// Compared to early code this makes the run more random.
		else if(!totalRoomsAdded.isEmpty()) {
			int place = totalRoomsAdded.size();
			place = rand.nextInt(place);
			// Checks if the room picked is in the current room list
			if(!currentRoomList.contains(totalRoomsAdded.get(place))) {
				currentRoom = totalRoomsAdded.get(place); // changes the current room 
				currentRoomList.add(totalRoomsAdded.get(place)); // adds the current room to the list
			}
		}
		
		// Now does some math to see where it should put rooms
		for(int i=0; i < directions.length; i++) {
			// Checks if it should add a room connected to the current room
			if(roomAddChance == random(1) && roomNumber <= roomsPerLevel) {
				roomNumber += 1;
				System.out.println("Created a room at: " + rotation());
				allRooms.put(directions[i] + rotation(), currentRoom);
				totalRoomsAdded.add(directions[i] + rotation()); 
			}
		}	
		
		// Checks if the system created any rooms within the current room
		if(!allRooms.containsValue(currentRoom) && roomNumber <= roomsPerLevel) {
			roomNumber += 1;
			String ran = directions[random(3)] + rotation(); // A String object used for storing room names
			allRooms.put(ran, currentRoom); // Adds the room to the list
			totalRoomsAdded.add(ran); // Adds the room to the list
			System.out.println("Created my own room at: " + ran);
			initializeLevel(); // Goes back to the start all over again
		}
		// Checks if the roomsPerLevel is still there
		else if(allRooms.containsValue(currentRoom)) {
			System.out.println("Created rooms");
			initializeLevel(); // Goes back to the start all over again
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
	 * Gets the order of which the rooms were created
	 * 
	 * @return "-" + roomNumber
	 */
	private String rotation() {
		return "-" + roomNumber;
	}
	
	
	/**
	 * Sets new level to the next one up
	 * 
	 * @param newLevel
	 */
	public void nextLevel() {
		level += 1; // Changes level 
		totalRoomsAdded.clear(); // Clears totalRoomsAdded
		currentRoomList.clear(); // Clears the current room list
		allRooms.clear(); // Clears all the rooms
	}

}
