/*
 * Name: Raymond Guo
 * Class Name: Game Locations
 * Description: Keeps track of where objects are located in the cave
 * Revision History (Date, Version, Comments)
 * 3/4/19,  Class created
 * 3/8/19,  Stubbed Class made
 * 3/14/19, Added to Bit Bucket
 * 3/16/19, Updated movePlayer, wumpusEncounter getWumpus and getPlayer locations
 * 3/19/19, Updated to fit the new Room class, updated batEncounter, made new methods moveBatrand and movePlayerrand 
 * 3/26/19, Updated if wumpus warning, and pit encounter
 * 4/2/19, Added pit warning and bat warning and added new comments to code. 
 * 4/15/19, Changed to fit the new cave class
 * 4/21/19, Added Wumpus movement and updated all comments
 * 4/29/19, Added wumpusHit for the player method 
 */

public class GameLocations
{
	
	//Declaring a rooms array
	private Room[] rooms; 
	//Constructor that takes in an array of rooms generated from Cave
	public GameLocations(Room [] rooms) 
	{                                     
		this.rooms = rooms;
	}

	//Gets player location and returns index in rooms array
	public int getPlayerLocation()
	{
		//For each element in the array
		for(int i = 0; i < rooms.length; i++)
		{
			//If the index contains player
			if(rooms[i].containsPlayer())
			{
				//Returns the index i
				return i;
			}
		}
		//If player not found, print error and return -1
		System.out.println("Error, player not found");
		return -1;
	}
	
	//Gets Wumpus location and returns index in rooms array
	public int getWumpusLocation()
	{
		//For each element in the array
		for(int i = 0; i < rooms.length; i++)
		{
			//If the index comtains Wumpus
			if(rooms[i].containsWumpus())
			{
				//returns the index i
				return i;
			}
		}
		//If Wumpus not found, print error and return -1
		System.out.println("Error, Wumpus not found");
		return -1;
	}

	//To String method
	public String toString()
	{
		return "GameLocations";
	}
	
	/*Finds the linked rooms around player and checks
	 * if the Wumpus is present in any of them
	 * as well as if the player can access the room
	 */
	public boolean nearWumpus() 
	{
		//Gets the linked rooms around player
		int[] linkedPlayerSides = rooms[getPlayerLocation()].getLinkedSides();
		//Gets Wumpus index location
		int wumpusLocation = getWumpusLocation();
		//For all the linked rooms around the Wumpus
		for(int i = 0; i < linkedPlayerSides.length; i++)
		{
			//If the player can access the room
			if(rooms[getPlayerLocation()].accessValue(i))
			{
				//And if that room contains the Wumpus
				if(linkedPlayerSides[i] == wumpusLocation)
				{
					//Print a a warning message
					System.out.println("There is a Wumpus nearby!");
					return true;
				}
			}
		}
		//Wumpus is not near player
		return false;
	}
	
	//Used in player class to determine
	//if the players arrow has hit the wumpus
	public int wumpusHit()
	{
		//Gets the linked rooms around player
		int[] linkedPlayerSides = rooms[getPlayerLocation()].getLinkedSides();
		//For all the linked sides the player has
		for(int i = 0; i < linkedPlayerSides.length; i++)
		{
			//If the player can access the room
			if(rooms[getPlayerLocation()].accessValue(i))
			{
				//And if that room contains the Wumpus
				if(linkedPlayerSides[i] == getWumpusLocation())
				{
					//return index of the room
					return i;
				}
			}
		}
		return -1;
	}
	
	/*Finds the linked rooms around player and checks
	 * if the pit is present in any of them
	 * as well as if the player can access the room
	 */
	public boolean nearPit()
	{
		//Gets the linked rooms around player
		int[] linkedPlayerSides = rooms[getPlayerLocation()].getLinkedSides();
		//For all the linked rooms around the player
		for(int i = 0; i < linkedPlayerSides.length; i++)
		{
			//If the player can access the location
			if(rooms[getPlayerLocation()].accessValue(i))
			{
				//And if that room contains a pit
				if(rooms[rooms[getPlayerLocation()].linkedRoom(i)].containsPit()) 
				{
					//Prints out a warning message
					System.out.println("There is a pit nearby!");
					return true;
				}
			}
		}
		//Pit is not near player
		return false;
	}
	
	/*Finds the linked rooms around player and checks
	 * if bats are present in any of them
	 * as well as if the player can access the room
	 */
	public boolean nearBats()
	{
		//Gets the linked rooms around player
		int[] linkedPlayerSides = rooms[getPlayerLocation()].getLinkedSides();
		//For all the linked rooms around the player
		for(int i = 0; i < linkedPlayerSides.length; i++)
		{
			//If the player can access the location
			if(rooms[getPlayerLocation()].accessValue(i))
			{
				//And if that room contains a bats
				if(rooms[rooms[getPlayerLocation()].linkedRoom(i)].containsBats()) 
				{
					//Prints out a warning message
					System.out.println("There is a bats nearby!");
					return true;
				}
			}
		}
		//bats is not near player
		return false;
	}
	
	//Moves the player to another room
	public void movePlayer(int roomNum) 
	{
		//Gets player room number
		int playerLocation = getPlayerLocation();
		//Gets the linked rooms around player
		int[] linkedPlayerSides = rooms[playerLocation].getLinkedSides();
		//For all the linked values
		for(int i = 0; i < linkedPlayerSides.length; i ++)
		{
			//If the room number passed is equal to a linked side
			if(roomNum == linkedPlayerSides[i])
			{
				//If the room can be accessed by player 
				if(rooms[playerLocation].accessValue(i))
				{
					//The previous cave no longer contains player
					rooms[playerLocation].playerChange(false);
					//The passed cave now includes player
					rooms[roomNum].playerChange(true);
				}
			}
		}
	}
	
	//Moves Wumpus if arrow is shot
	public void moveWumpus()
	{
		//Gives  a 50% chance the Wumpus will move
		int rand = (int)(Math.random() *2) + 1;
		//If the random number is 1
		if(rand == 1)
		{
			//Moves Wumpus to a random nearby room
			moveWumpusRand();	
		}
	}
	
	//If the player Encounters Wumpus, return true               <-- Needs Trivia
	public boolean wumpusEncounter() 
	{
		//If the Wumpus and the player are in the same room
		if(getWumpusLocation() == getPlayerLocation())
		{
			//Runs Trivia and returns if they got 3/5 questions right
			//Generates a random number between 2 and 4
			int rand = (int)(Math.random()*3) + 2;
			//For every number of rand
			for(int i = 0; i < rand; i++)
			{
				//Moves Wumpus to a random room nearby
				moveWumpusRand();
			}
		}
		//Player did not encounter Wumpus
		return false;
	}
	
	//Returns true of player encountered bat
	public boolean batEncounter() 
	{	
		//Gets the room the player is located in
		int playerLocation = getPlayerLocation();
		//If the room contains bats
		if(rooms[playerLocation].containsBats()) {
			//Moves player to a random room
			movePlayerRand();
			//Moves bats to a random location
			moveBatRand(playerLocation);
			return true;
		}
		//If there is no bat encounter
		return false;
	}
	
	//Checks if player has fell into pit                         <-- Needs Trivia
	public boolean pitEncounter() 
	{
		//Gets player location
		int playerLocation = getPlayerLocation();
		//If the player location also has a pit
		if(rooms[playerLocation].containsPit()) {
			//ASKS TRIVIA QUESTION 
			//if player does trivia question right
			//	return false, player does not move nor fall into pit
			//else
			//Move a player to a random location
			movePlayerRand();
			return true;
		}
		//If there is no pit encounter
		return false;
	}
	
	//moves player to random location nearby
	public void movePlayerRand()
	{
		//Gets player location
		int playerLocation = getPlayerLocation();
		//Generates two random ints from 0 - 5
		int random1 = (int)(Math.random()*6);
		int random2 = (int)(Math.random()*6);
		//The new player location is choose to be a linked room
		int newPlayerLocation = rooms[playerLocation].linkedRoom(random1);
		//The player location is a linked room of the previous linked room
		int newPlayerLocation2 = rooms[newPlayerLocation].linkedRoom(random2);
		//While a hazard is present
		while(rooms[newPlayerLocation].containsBats() || rooms[newPlayerLocation].containsPit() || rooms[newPlayerLocation].containsWumpus())
		{
			//Reshuffle the choice for the second room
			random2 = (int)(Math.random()*6);
			newPlayerLocation2 = rooms[newPlayerLocation].linkedRoom(random2);
		}
		//Sets the old location to false for player
		rooms[playerLocation].playerChange(false);
		//Sets new location to true for player
		rooms[newPlayerLocation2].playerChange(true);
	}
	
	//Moves bat to random location near by
	public void moveBatRand(int roomNum)
	{
		//Generates two random ints
		int random1 = (int)(Math.random()*6);
		int random2 = (int)(Math.random()*6);
		//The new bat location is choose to be a linked room
		int newbatLocation = rooms[roomNum].linkedRoom(random1);
		//The bat location is a linked room of the previous linked room
		int newbatLocation2 = rooms[newbatLocation].linkedRoom(random2);
		//While another hazard or player is present
		while(rooms[newbatLocation].containsPlayer() || rooms[newbatLocation].containsPit() || rooms[newbatLocation].containsWumpus() || rooms[newbatLocation].containsBats())
		{
			//Reshuffle the choice for the second room
			random2 = (int)(Math.random()*6);
			newbatLocation2 = rooms[newbatLocation].linkedRoom(random2);
		}
		//Sets the old location to false for bats
		rooms[roomNum].batsChange(false);
		//Sets new location to true for bats
		rooms[newbatLocation2].batsChange(true);
	}
	
	//moves Wumpus to a random locations
	public void moveWumpusRand()
	{
		//gets wumpus location
		int wumpusLocation = getWumpusLocation();
		//generates two random intes
		int random = (int)(Math.random()*6);
		//The new wumpus location is choose to be a linked room
		int newWumpusLocation = rooms[wumpusLocation].linkedRoom(random);
		//While a hazard or player is present
		while(rooms[newWumpusLocation].containsPlayer())
		{
			//reshuffle the choice for the second room
			random = (int)(Math.random()*6);
			newWumpusLocation = rooms[newWumpusLocation].linkedRoom(random);
		}
		//Sets the old location to false for Wumpus
		rooms[wumpusLocation].wumpusChange(false);
		//Sets new location to true for Wumpus
		rooms[newWumpusLocation].wumpusChange(true);
	}

}
