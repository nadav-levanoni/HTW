import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
Name: Viraj Goyal, Class Name: Cave
Description: Keeps track of which rooms in the cave are connected to which other rooms. 
Revision History:
Date: 3/5/2019, Version: 1, Comments: Creating a toString method and constructor
Date: 3/12/2019, Version: 1, Comments: Creating a stubbed class 
Date: 3/28/2019, Version: 1, Comments: Deleting repetitive getConnectionsWumpus() method
Date: 4/2/2019, Version: 1, Comments: Added new caves on .txt files 
Date: 4/10/2019, Version: 1, Comments: Creating a random cave algorithm
*/
public class Cave 
{
	private Room[] rooms;
	public Cave()
	{
		rooms = new Room[30];
		for(int i = 0; i < rooms.length; i++)
		{
			rooms[i] = new Room(i + 1);
		}
	}
	public String toString()
	{
		String total = " ";
		for(int i = 0; i < rooms.length; i++)
		{
			total += rooms[i].toString() + "\n";
		}
		return total;
	}
	public Room[] getRooms() throws FileNotFoundException
	{
		convertCave("possibleConnections.txt");
		//randomConnectionsGeneration();
		randomObjectLocation();
		return rooms;
	}
	public int checkNumConnections(Room room)
	{
		int count = 0;
		for(int i = 0; i < 6; i++)
		{
			/*if(room.accessValue(i))
			{
				count++;
			}*/
		}
		return count;
	}
	public int getOtherSideNumber(int sideNumber)
	{
		if(sideNumber == 0 || sideNumber == 1 || sideNumber == 2)
		{
			return sideNumber + 3;
		}
		else
		{
			return sideNumber - 3;
		}
	}
	/*This method creates a random cave (will delete getCave method after I figure out 
	how to make a random cave) */
	//Connections for one room
	/*public void randomConnectionsGeneration()
	{
		int roomSum = 0;
		for(int j = 0; j < rooms.length; j++)
		{
			int randomNumConnections = (int)(Math.random() * 3 + 1);
			for(int i = 0; i < randomNumConnections; i++)
			{
				int randomConnection = (int)(Math.random() * 6);
				if(checkNumConnections(rooms[j]) < 3 && checkNumConnections(rooms[rooms[j].linkedRoom(randomConnection) - 1]) < 3 && rooms[j].accessValue(randomConnection) == false)
				{
					rooms[j].changeSideAccess(randomConnection, true);
					rooms[rooms[j].linkedRoom(randomConnection) - 1].changeSideAccess(getOtherSideNumber(randomConnection), true);
					if(checkNumConnections(rooms[j]) == 1)
					{
						roomSum += rooms[j].getRoomNumber();
					}
					if(checkNumConnections(rooms[rooms[j].linkedRoom(randomConnection) - 1]) == 1)
					{
						roomSum += rooms[j].linkedRoom(randomConnection);
					}
				}
				else
				{
					i--;
				}
			}
		}
		//Checking if all rooms are reachable by adding the room number if there is at least one connection that room has to another room
		if(roomSum < 465)
		{
			//reseting side access to false, so room can be regenerated
			for(int j = 0; j < rooms.length; j++)
			{
				for(int resetSide = 0; resetSide < 6; resetSide++)
				{
					rooms[j].changeSideAccess(resetSide, false);
				}
			}
			randomConnectionsGeneration();
		}
	}*/
	public void randomObjectLocation()
	{
		int randomRoom;
		for(int bat = 0; bat < 2; bat++)
		{
			//Player starts in room 1, so we don't include hazards in that room
			randomRoom = (int)(Math.random() * 29 + 1);
			if(rooms[randomRoom].containsBats() == false && rooms[randomRoom].containsPit() == false)
			{
				rooms[randomRoom].batsChange(true);
			}
			else
			{
				bat--;
			}
		}
		for(int pit = 0; pit < 2; pit++)
		{
			//Player starts in room 1, so we don't include hazards in that room
			randomRoom = (int)(Math.random() * 29 + 1);
			if(rooms[randomRoom].containsBats() == false && rooms[randomRoom].containsPit() == false)
			{
				rooms[randomRoom].pitChange(true);
			}
			else
			{
				pit--;
			}
		}
		randomRoom = (int)(Math.random() * 29 + 1);
		rooms[randomRoom].wumpusChange(true);
		rooms[0].playerChange(true);
	}
	public void convertCave(String filename) throws FileNotFoundException
	{
		int[] connections = new int[6];
		String path = "./input/";
		Scanner file = new Scanner(new File(path + filename));
		/*This for loop converts a text file that holds the connections to each room
		 * into a 2D array where the index of the rows is the room number minus 1
		 * and each row contains the connections of that room (which rooms you can
		 * go to from that room)
		 */
		for(int r = 0; r < rooms.length; r++)
		{
			String line = file.nextLine();
			Scanner sc = new Scanner(line);
			int count = 0;
			while(sc.hasNextInt()) {
				if(count == 0)
				{
					sc.nextInt();
				}
				connections[count] = sc.nextInt();
				//System.out.print(connections[count] + " ");
				count++;
			}
			rooms[r].initializeLinkedSide(connections);
			sc.close();
			//System.out.println();
		}
		file.close();
	}
	
	//This method returns an array of rooms that the Player can go to(connections)
	//The room that the player is currently in is passed as a parameter
	/*
	public int[] getConnections(int room)
	{
		This for loop uses the room array to copy the rooms that the player can
		travel to into a 1D array
		int[] connectionsPlayer = new int[rooms[room - 1].length];
		for(int c = 0; c < connectionsPlayer.length; c++) {
			connectionsPlayer[c] = rooms[room - 1][c];
		}
		return connectionsPlayer;
	}
	*/
}
