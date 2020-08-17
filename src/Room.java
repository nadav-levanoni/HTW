/*
 * Name: Raymond Guo and Viraj Goyal
 * Class Name: Room
 * Description: Keeps track of what is in the assigned room
 * Revision History (Date, Version, Comments)
 * 3/19/19 Room Class created, constructor, accessor, and modifiers created. 
 * 4/9/19 Added it to be compatible with 6 sides
 * 4/29/19 updated comments
 * Rooms class needs access to either graphical interface or screen, something to
 * build the actual room up. The rooms class should contain some sort of way to build
 * an individual room up using either screen or graphical interface. 
 * 
 * For example, the rooms class should contain 5 different room structures for rooms that are pre-built.
 * Those rooms are then assigned values from the rooms class whether they contain say bats or pits
 * For this, we also need individual tunnel structure potentially for the rooms?
 * Accessors need to be provided from the camera, screen class for this to happen. 
 */
public class Room
{
	
	//Booleans that represent game objects
	private boolean containsWumpus;
	private boolean containsBats;
	private boolean containsPit;
	private boolean containsPlayer;
	
	//The room number of the room
	private int roomNumber;
	
	//Which doors can be accessed in the room
	private boolean[] sideAccess;
	
	//Which rooms can be accessed with the doors
	private int[] linkedSides; 
	
	//Constructor that takes the room number
	public Room(int roomNumber)
	{
		//sets all contain values to false
		containsWumpus = false;
		containsBats = false;
		containsPit = false;
		containsPlayer = false;
		
		//index 0 = top, 1 = north east, 2 = south east, 
		//3 = bottom, 4 = south west, 5 = north west
		linkedSides = new int[6]; 
		sideAccess = new boolean[6];
		
		//Sets values for the two arrays
		for(int i = 0; i < sideAccess.length; i++)
		{
			sideAccess[i] = false;
			linkedSides[i] = 0;
		}
		
		//sets room number value
		this.roomNumber = roomNumber;
	}
	public String toString()
	{
		//displays all room number
		String total = roomNumber + "  ";
		for(int i = 0; i < sideAccess.length; i++)
		{
			if(sideAccess[i])
			{
				//displays all the linked sides
				total += linkedSides[i] + " ";
			}
		}
		return total;
	}
	
	//Accessors
	
	//Returns the array of linked Sides to this room
	public int[] getLinkedSides(){
		return linkedSides;
	}
	
	//Returns an array of the accessiable rooms to this room
	public boolean[] getSideAccess(){
		return sideAccess;
	}
	
	//Returns this rooms room number
	public int getRoomNumber(){
		return roomNumber;
	}
	
	//Returns if this room contains the wumpus
	public boolean containsWumpus() {
		return containsWumpus;
	}
	
	//Returns if this room contains bats
	public boolean containsBats() {
		return containsBats;
	}
	
	//Returns if this room contains pits
	public boolean containsPit() {
		return containsPit;
	}
	
	//Returns if this room contains player
	public boolean containsPlayer() {
		return containsPlayer;
	}
	
	//Returns if this room has access to the room in the pointed direction
	public boolean accessValue(int index){
		//if the index is out of array size
		if(index < 0 || index > sideAccess.length)
		{
			return false;
		}
		return sideAccess[index];
	}
	
	//Returns the linked room in the pointed direction 
	public int linkedRoom(int index){
		//if the index is out of array size
		if(index < 0 || index > linkedSides.length)
		{
			return -1;
		}
		return linkedSides[index];
	}
	
	//Modifiers
	
	//Modifies if the wumpus is present or not in this room
	public void wumpusChange(boolean newStatus){
		containsWumpus = newStatus;
	}
	
	//Modifies if the bats are present or not in this room
	public void batsChange(boolean newStatus){
		containsBats = newStatus;
	}
	
	//Modifies if the pits are present or not in this room
	public void pitChange(boolean newStatus) {
		containsPit = newStatus;
	}
	
	//Modifies if the player is present or not in this room
	public void playerChange(boolean newStatus) {
		containsPlayer = newStatus;
	}
	
	//Changes if a room linked to this room is accessiable or not
	public void changeSideAccess(int index, boolean newAccessValue) {
		sideAccess[index] = newAccessValue;
	}
	
	//Changes which rooms is linked to this room
	public void changeLinkedSide(int index, int linkedRoom) {
		linkedSides[index] = linkedRoom;
	}
	
	//Initializes all the linked sides with an array passed from Cave
	public void initializeLinkedSide(int[] allConnections)
	{
		//for all objects in the array
		for(int i = 0; i < allConnections.length; i++)
		{
			//The linked sides is equal to the connections
			linkedSides[i] = allConnections[i];
		}
	}
	
}