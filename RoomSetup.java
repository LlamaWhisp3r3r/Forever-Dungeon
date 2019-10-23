// Imports Listed Here:
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Setups up the Rooms for a level & updates room's value
 * 
 * @author Nathan Turner
 *
 */
public class RoomSetup {
	
	// Creating random object
	private Random rand = new Random();
	
	// Scanner object
	Scanner scan = new Scanner(System.in);
	
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
	private Map<String, List<String>> allRooms = new HashMap<String, List<String>>();
	
	// The chance a room will be added in a certain direction
	private int roomAddChance = random(1);
	
	// Directions
	private String[] directions = {"u", "r", "d", "l"};
	private List<String> currentRoomList = new ArrayList<String>();
	private List<String> roomsVisted = new ArrayList<String>();
	
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
	
	
	public void playRoom(String room) {
		List<String> directions = getRoom(room);
		roomsVisted.add(room);
//		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("\n\n\n\n\n\n\n\n\n");
		System.out.println("Welcome to the " + getRoomName() + " room");
		System.out.println("There are rooms to your " + directions);
		System.out.println("Which Direction would you like to go to?");
		String answer = scan.nextLine();
		
		if(answer.equalsIgnoreCase("right")) {
			char r = answer.toLowerCase().charAt(0);
			String roomName = getRoom(room, r);
			playRoom(roomName);
		}else if(answer.equalsIgnoreCase("up")) {
			char r = answer.toLowerCase().charAt(0);
			String roomName = getRoom(room, r);
			playRoom(roomName);
		}else if(answer.equalsIgnoreCase("down")) {
			char r = answer.toLowerCase().charAt(0);
			String roomName = getRoom(room, r);
			playRoom(roomName);
		}else if(answer.equalsIgnoreCase("left")) {
			char r = answer.toLowerCase().charAt(0);
			String roomName = getRoom(room, r);
			playRoom(roomName);
		}
		
	}
	
	
	/**
	 * Gets everything in the room so that the player can
	 * See what's in it
	 * 
	 * @return roomList
	 */
	public List<String> getRoom(String room) {

		
		List<String> currentRoom = allRooms.get(room);
		List<String> m = new ArrayList<String>();
		if(!currentRoom.isEmpty()) {
			for(String man : currentRoom) {
				System.out.println(man);
				char s = man.charAt(0);
				if(s == 'u') {
					m.add("Up");
				}else if(s == 'r') {
					m.add("Right");
				}else if(s == 'd') {
					m.add("Down");
				}else if(s == 'l') {
					m.add("Left");
				}
				
			}
		}else {
			m.add("No Rooms");
		}
		return m;
	}
	
	
	/**
	 * Gets everything in the room so that the player can
	 * See what's in it
	 * 
	 * @return roomList
	 */
	private String getRoom(String room, char direction) {
		// Use this method to play a room and go to other rooms connected to it		
		List<String> currentRoom = allRooms.get(room);
		String newRoom = "Empty";
		for(String m : currentRoom) {
			char s = m.charAt(0);
			if(s == direction) {
				newRoom = m;
				break;
			}else if(s == direction) {
				newRoom = m;
				break;
			}else if(s == direction) {
				newRoom = m;
				break;
			}else if(s == direction) {
				newRoom = m;
				break;
			}
		}
		
		return newRoom;
		
	}
	
	
	/**
	 * Gets a random room type to be used later
	 * for how frequently certain types of monsters 
	 * spawn
	 * 
	 * @return nameList[x]
	 */
	private String getRoomName() {
		String[] nameList = {"Hollow", "Lava", "Hail", "Heaven", "Sky", "Water", "Fire"};
		int x = random(nameList.length -1);
		return nameList[x];
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
		
		if(roomNumber == roomsPerLevel) {
			return;
		}
		
		System.out.println("Current Room is: " + currentRoom);
		System.out.println("total Rooms Added Empty? " + totalRoomsAdded.isEmpty());
		System.out.println("allRooms contains current room? " + allRooms.containsKey(currentRoom));
		
		// Adds a middle room for sure every time it creates a level
		if(!totalRoomsAdded.contains("start")) {
			totalRoomsAdded.add("start");
			allRooms.put("start", totalRoomsAdded); // adds to list
			currentRoom = "start";
			currentRoomList.add(currentRoom); // Sets current room
			System.out.println("Created Start Room");
			
		}else if(!totalRoomsAdded.isEmpty() && allRooms.containsKey(currentRoom)) { // Gets a Random Room for the current Room
			for(int i=0; i < totalRoomsAdded.size(); i++) {
				// Checks if the room picked is in the current room list
				if(!currentRoomList.contains(totalRoomsAdded.get(i))) {
					System.out.println("Current Room was changed to: " + totalRoomsAdded.get(i));
					currentRoom = totalRoomsAdded.get(i); // changes the current room 
					currentRoomList.add(totalRoomsAdded.get(i)); // adds the current room to the list
				}
			}
		}
		
		// Now does some math to see where it should put rooms
		List<String> currentRoomRooms = new ArrayList<String>(); // An array to hold everything together
		currentRoomRooms.clear();
		System.out.println("Current Room Now: " + currentRoomRooms);
		
		for(int i=0; i < directions.length; i++) {
			
			// Checks if it should add a room connected to the current room
			if(roomAddChance == random(1) && roomNumber < roomsPerLevel) {
				boolean addedBOrS = false;
				
				// Checks if shop exist -> if not then it sees if it has the chance to add it
				if(!totalRoomsAdded.contains("shop") && random(7) == random(7)) {
					currentRoomRooms.add(directions[i] + "-shop"); // Adds room to list
					totalRoomsAdded.add("shop"); // Adds room to list
					System.out.println("Added Shop Room At Room: " + currentRoom);
					addedBOrS = true;
				}else if(!totalRoomsAdded.contains("boss") && random(10) == random(10)) {
					currentRoomRooms.add(directions[i] + "-boss"); // Adds room to list
					totalRoomsAdded.add("boss"); // Adds room to list
					System.out.println("Added Boss Room At Room: " + currentRoom);
					addedBOrS = true;
				}else if(addedBOrS == false) {
					roomNumber += 1; // Adds 1 to the roomNumber because it added a room
					System.out.println("Created a room at: " + rotation('u'));
					currentRoomRooms.add(directions[i] + rotation('u')); // Adds room to list
					totalRoomsAdded.add(directions[i] + rotation('u')); // Adds room to list
				}
				
			}
		}	
		
		System.out.println("Current Room Before: " + currentRoomRooms);
		if(!currentRoomRooms.isEmpty()) {
			allRooms.put(currentRoom, currentRoomRooms);
			System.out.println(currentRoomRooms);
			System.out.println("all Rooms: " + allRooms.toString());
			System.out.println("Total Rooms: " + totalRoomsAdded.toString());
			System.out.println("Starting Over");
			initializeLevel(); // Goes back to the start all over again
		}else if(currentRoomRooms.isEmpty()) {
			if(roomNumber < roomsPerLevel) {
				roomNumber += 1;
				String ran = directions[random(3)] + rotation('u'); // A String object used for storing room names
				currentRoomRooms.add(ran); // Adds the room to the list
				totalRoomsAdded.add(ran); // Adds the room to the list
				System.out.println("Created my own room at: " + ran);
			}
		
			
			// Checks if the boss room is on the level
			if(!totalRoomsAdded.contains("boss") && roomNumber > roomsPerLevel) {
				// For loop that goes through all the totalRoomsAdded items to see if they are in the currentRoom list
				for(int i=0; i < totalRoomsAdded.size(); i++) {
					int random = rand.nextInt(totalRoomsAdded.size()); // get a random number in the range of the array's size
					// If statement that checks if the current room list contains the totalRoomsAdded
					if(!currentRoomList.contains(totalRoomsAdded.get(random))) {
						int x = random(3); // Gets random number on range of 3
						currentRoom = totalRoomsAdded.get(random);
						currentRoomRooms.add(directions[x] + "-boss"); // Adds to List
						totalRoomsAdded.add("boss"); // Adds to List
						System.out.println("Added Boss Room By Default At Room: " + totalRoomsAdded.get(random));
						break; // Breaks off of loop
					}
				}
			}
			// Checks if the shop room is in the level
			if(!totalRoomsAdded.contains("shop") && roomNumber < roomsPerLevel) {
				// For loop that goes through all the totalRoomsAdded items to see if they are in the currentRoom list
				for(int i=0; i < totalRoomsAdded.size(); i++) {
					int random = rand.nextInt(totalRoomsAdded.size()); // get a random number in the range of the array's size
					// If statement that checks if the current room list contains the totalRoomsAdded
					if(!currentRoomList.contains(totalRoomsAdded.get(random))) {
						int x = random(3); // Gets random number in range of 3
						currentRoom = totalRoomsAdded.get(random);
						currentRoomRooms.add(directions[x] + "-shop"); // Adds to list
						totalRoomsAdded.add("shop"); // Adds to list
						System.out.println("Added Shop Room By Default At Room: " + totalRoomsAdded.get(random));
						break; // breaks off of loop
					}
				}
			}
			if(!currentRoomRooms.isEmpty()) {
				allRooms.put(currentRoom, currentRoomRooms);
				System.out.println(currentRoomRooms);
				System.out.println("all Rooms: " + allRooms.toString());
				System.out.println("Total Rooms: " + totalRoomsAdded.toString());
				System.out.println("Starting Over");
				initializeLevel(); // Goes back to the start all over again
			}
		}else {
			System.out.println("I don't know what happen...");
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
	private String rotation(char g) {
		if(g == 'i') {
			return ":" + roomNumber;
		}else {
			return "-" + roomNumber;
		}
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
