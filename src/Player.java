/*
 * Name: Raymond Guo
 * Class Name: Game Locations
 * Description: Keeps track of player inventory and calculates score
 * Revision History (Date, Version, Comments)
 * 3/4/19, Class created
 * 3/8/19, Stubbed Class made
 * 3/16/19, getScore, buyArrow and moveForward methods updated
 * 3/26/19, newGame removed, added modifiers
 * 4/2/19, finalized class
 * 4/29/19 Updated comments and updated shoot arrow
 * Please tell me if there should
 * 
 * be anything else that should be added to this class <--
 */
public class Player
{
	
	//Player inventory
	private int arrowCount;
	private int gold;
	
	//Player stats
	private int turnsTaken;
	private boolean wumpusKilled;
	private int caveID;
	
	//Player info
	private String playerName;
	
	//Default constructor 
	public Player()
	{
		arrowCount = 0;
		gold = 0;
		turnsTaken = 0;
		playerName = "player1";
		wumpusKilled = false;
	}
	
	//Constructor that takes in player name ONLY
	public Player(String playerName, int caveID)
	{
		this.playerName = playerName;
		this.caveID = caveID;
		arrowCount = 0;
		gold = 0;
		turnsTaken = 0;
		wumpusKilled = false;
	}
	
	//Constructor that takes in arrow count, amount of gold, turns taken and player name
	public Player(int arrowCount, int gold, int turnsTaken, String playerName) 
	{
		this.arrowCount = arrowCount;
		this.gold = gold;
		this.turnsTaken = turnsTaken;
		this.playerName = playerName;
		wumpusKilled = false;
	}
	
	//To String method that returns player name
	public String toString()
	{
		return playerName;
	}
	
	//Accesors
	
	//Gets the amount of turns taken by player
	public int getTurns() {
		return turnsTaken;
	}
	//Gets the amount of gold the player has
	public int getGold() {
		return gold;
	}
	//Gets the number of arrows the player has
	public int getNumArrows() {
		return arrowCount;
	}
	//Gets the name of the player
	public String getPlayerName() { return playerName;}
	//Gets the player's current cave
	public int getPlayerCaveID() { return caveID; }
	
	/* Score calculation uses the formula of:
	 * 100 points - N + G + (5*A) + W
	 * Where: 
	 * N = # of turns
	 * G = # of gold left
	 * A = # of arrows left
	 * W = 50 if wumpus is killed, 0 otherwise
	 */
	public int getScore()
	{
		int score = 100 - turnsTaken + gold + (5 * arrowCount);
		//if the Wumpus is killed add 50 to score
		if(wumpusKilled)
		{
			return score + 50;
		}
		//otherwise just return the sore
		return score;
	}
	
	//Modifiers
	
	//Changes the amount of gold the player has by passed amount
	public void changeGold(int amount) {
		gold += amount;
	}
	//Changes the number of arrows the player has by passed amount
	public void changeNumArrows(int amount) {
		arrowCount += amount;
	}
	//Changes player name to passed String
	public void changePlayerName(String newName) {
		playerName = newName;
	}
	//Change the number of turns taken by passed amount
	public void changeTurnsTaken(int amount) {
		turnsTaken += amount;
	}
	
	//+ 1 gold, turns updated + 1
	public void moveForward() 
	{
		gold ++;
		turnsTaken ++;
	}
	
	//If the user wants to buy an arrow
	public void buyArrow()
	{
		//if they have more than or equal to 15 gold
		if(gold <= 15)
		{
			//Arrow count is updated, gold is subtracted
			arrowCount ++;
			gold = gold - 15;
		}
		//If they dont have enough gold, print an error message
		else
		{
			System.out.println("Not enough gold");
		}
	}
	
	//The user shoots an arrow
	//If the arrow hits, return true
	public boolean shootArrow(int direction, GameLocations location)
	{
		//if the user does not have enough arrows
		if(arrowCount <= 0)
		{
			//display an error message
			System.out.println("Not enough arrows!");
			return false;
		}
		arrowCount = arrowCount - 1;
		//if the user is near the wumpus
		if(location.nearWumpus())
		{
			//if the direction aimed at is equal to the room
			//that the wumpus is in
			if(direction == location.wumpusHit())
			{
				//updates wumpusKilled to true and returns true
				wumpusKilled = true;
				return true;
			}
		}
		//returns false as wumpus was not hit
		return false;
	}
	
}