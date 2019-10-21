
/**
 * Starts the Game
 * 
 * @author Nathan Turner
 *
 */
public class StartGame {

	/**
	 * Main Method that will run all the code
	 * From the other classes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Doing some testing to see if the RoomSetup.java is working right
		RoomSetup m = new RoomSetup();
		
		m.initializeLevel();
		m.playRoom("start");
	}

}
