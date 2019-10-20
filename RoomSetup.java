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
	
	// Variable for initialization
	private int timeThrough = 0;
	
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
	private String[] currentRoomList = new String[20];
	
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
	public String getAllRooms() {
		return allRooms.toString();
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
		
		// Adds for how many times through this function will go through
		timeThrough += 1;
		
		// Clears any the previous level if there was one
		if(level > 1 && timeThrough == 1) {
			System.out.println("Clearing all the rooms");
			allRooms.clear();
		}
		
		
		// Adds a middle room for sure every time
		if(!allRooms.containsKey("Start Room")) {
			allRooms.put("Start Room", "start");
			currentRoom = "Start";	
			System.out.println("Created Start Room");
		} 
		// Gets the current room in a really weird way
		else if(!totalRoomsAdded.isEmpty()) {
			for(int i=0; i < totalRoomsAdded.size(); i++) {
				boolean roomInCurrent = false;
				if(totalRoomsAdded.get(i) != currentRoom) {
					for(int k=0; k < currentRoomList.length; k++) {
						if(currentRoomList[k] == totalRoomsAdded.get(i)) {
							roomInCurrent = true;
						}
					}
					if(!roomInCurrent) {
						currentRoom = totalRoomsAdded.get(i);
						for(int g=0; g < currentRoomList.length; g++) {
							if(currentRoomList[g] == null) {
								System.out.println("Changed Current Room to: " + totalRoomsAdded.get(i));
								currentRoomList[g] = totalRoomsAdded.get(i);
								break;
							}
						}
					}
				}
			}
		}
		
		// Now does some math to see where it should put rooms
		for(int i=0; i < directions.length; i++) {
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
			String ran = directions[random(3)] + rotation();
			allRooms.put(ran, currentRoom);
			totalRoomsAdded.add(ran);
			System.out.println("Created my own room at: " + ran);
			initializeLevel();
		}
		// Checks if the roomsPerLevel is still there
		else if(allRooms.containsValue(currentRoom)) {
			System.out.println("Created rooms");
			initializeLevel();
		}
		
		System.out.println("If your seeing this you finished");
		
	}
	
	
	/**
	 * Gets the current level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	
	private String rotation() {
		return "-" + roomNumber;
	}
	
	
	/**
	 * Sets new level to the next one up
	 * 
	 * @param newLevel
	 */
	public void nextLevel() {
		level += 1;
		timeThrough = 0;
		totalRoomsAdded.clear();
		for(int i=0; i <= currentRoomList.length; i++) {
			currentRoomList[i] = null;
		}
	}

}
