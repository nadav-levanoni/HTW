/*
 * Sahil Kancherla
 * Period 4/AP CS A
 * The GameControl class is responsible for handling data from the graphical interface and coordinating all the other classes
 * 
 * Revision History:
 * Date - Version - Comments
 * 3/20/19 - Version 1 - First couple methods
 * 3/27/19 - Version 2 - Added couple more methods
 * 4/1/19 - Version 3 - Edited some methods to be compatible with changes in other classes
 * 4/7/19 - Version 4 - Edited some more methods
 * 4/16/19 - Version 5 - Testing more methods
 * 4/24/19 - Version 7 - Testing more methods
 *  
 */


//import com.sun.tools.javac.Main;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GameControl {
	public static void main(String[]args) throws IOException {

		// This is for Mac OSX
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		// Display the JFrame (invoke later)
		new MainMenu().setVisible(true);


		//GraphicalInterface game = new GraphicalInterface();
		Cave cave = new Cave();
		//GameLocations location = new GameLocations(cave.getRooms());
		//System.out.println(location.getPlayerLocation());
		HighScore highScore = new HighScore();
		Player player = new Player();
		Trivia trivia = new Trivia();

			
		
		playTriviaRound(trivia, 3);
		
		//readIntroduction();
		//playOneRound(player,highScore, cave, trivia /*,location*/);
	}
	
	//Reads the rules and objective to the user
	public static void readIntroduction() {
		//Will eventually be sent to graphical interface to display on screen
		System.out.println("Welcome to Hunt the Wumpus");
		System.out.println("The rules are simple\nTry to defeat the Wumpus while escaping bats, pits, and of course\nThe Wumpus itself");
		System.out.println();
	}
	
	//Will allow user to set fields such as name and other personal characteristics
	//Takes player object as parameter
	public static void setFields(Player player) {
		
	}
	
	//Plays one round of the game and returns true if user wins, false if user is defeated
	//Only know that I will use player object right now, will add other objects when necessary
	public static void playOneRound(Player player, HighScore highScore, Cave cave, Trivia trivia /*, GameLocation location*/) throws IOException {
		Scanner userInput = new Scanner(System.in);
		boolean gameIsRunning = true;
		while(gameIsRunning) {
			movePlayer(player);
			
			

			//Player is able to buy an arrow
			System.out.println("TESTING BUYING ARROWS");
			System.out.println("Do you want to buy an arrow? Click 1 to purchase.");
			if(userInput.nextLine().equals("1")) {
				player.buyArrow();
				System.out.println("You have bought an arrow.");
			}
			else {
				System.out.println("Maybe some other time.");
			}
			System.out.println();
			
			
			
			//Read certain trivia questions
			ArrayList<Question> questionHopper = new ArrayList<Question>();
			questionHopper = trivia.getQuestion(5);
			
			for(int i = 0; i < 5; i++) {
				System.out.println(questionHopper.get(i));
			}
			
			//Player is able to shoot an arrow
			/*System.out.println("Do you want to shoot an arrow? Click 1 to shoot.");
			if(userInput.nextLine().equals("1")) {
				boolean validShot = player.shootArrow(0);
				if(validShot) {
					System.out.println("The arrow was shot.");
				}
			}
			else {
				System.out.println("You decided to not shoot the arrow at this time");
			}
			System.out.println();*/
			
			//Converts text file into 2D array in Cave class and prints
			System.out.println("TESTING PRINTING ROOMS");
			System.out.println("Here are the rooms:");
			cave.convertCave("cave1.txt");
			System.out.println();
				
			//Creating room object
			Room room20 = new Room(20);
			//Prints how many rooms are next to the room the player is in currently
			System.out.println("TESTING NUMBER OF ROOMS NEXT TO PLAYER");
			System.out.println(cave.checkNumConnections(room20));
			System.out.println();
			
			//Creates a random cave
			//cave.randomConnectionsGeneration();
			
			
			//Prints the side next to the side you are on
			System.out.println("TESTING PRINTING SIDES NEXT TO CURRENT SIDE");
			System.out.println("The side next to you is:");
			System.out.println(cave.getOtherSideNumber(0));
			System.out.println();
			
			
			//Prints player score
			System.out.println("TESTING PLAYER CURRENT SCORE");
			System.out.println("Your player's score is: " + player.getScore());
			System.out.println();
			
			
			
			/*
			//Prints player current location 
			System.out.println("TESTING PLAYER CURRENT LOCATION");
			System.out.println("The player is currently in room " + location.getPlayerLocation());
			System.out.println();
			
			//Prints wumpus current location 
			System.out.println("TESTING WUMPUS CURRENT LOCATION");
			System.out.println("The wumpus is currently in room " + location.getWumpusLocation());
			System.out.println();
			
			//Checking if player is near a pit
			System.out.println("TESTING IF PLAYER IS NEAR A PIT");
			if(location.nearPit()) {
				System.out.println("You are near a pit");
			}
			else {
				System.out.println("You are not near a pit");
			}
			
			//Checking if player is near a bat
			System.out.println("TESTING IF PLAYER IS NEAR A BAT");
			if(location.nearBat()) {
				System.out.println("You are near a bat");
			}
			else {
				System.out.println("You are not near a bat");
			}
			*/
			
			//Checks if user score is high enough to be on top 10 list
			System.out.println("TESTING CHECK HIGH SCORE");
			boolean added = highScore.isNewHighScore(player.getScore());
			System.out.println("Your score might be high enough to be on the leaderboard. Lets check.");
			if(added) {
				System.out.println("Your score is high enough to be added.");
			}
			else {
				System.out.println("Your score is not high enough to be added.");
			}
			System.out.println();
			
			

			//Updates leaderboard, which contains top 10 scores
			System.out.println("TESTING UPDATE HIGH SCORE");
			//highScore.updateList(player.getScore(), player.getPlayerName(), "empty",10,10,10,10);
			System.out.println("The leaderboard has been updated.");
			System.out.println();
			
			//Asks user if they want to continue playing
			System.out.print("Do you want to keep playing? Type 1 to exit and anything else to continue.");
			if(userInput.nextLine().equals("1")) {
				gameIsRunning = false;
				System.out.println("Thank you for playing!");
			}
			else {
				System.out.println("Have fun!");
			}
		}
	}
	
	//A player makes a move in this method
	public static void movePlayer(Player player) {
		player.moveForward();
		System.out.println("You have gained one gold. Your total gold is now: " + player.getGold());
		System.out.println();
	}
	//Ends the game and sends notification to other objects to reset
	//Will call reset methods in other classes
	public static void endGame() {
		
	}
	public static void playTriviaRound(Trivia trivia, int amountAsked) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		int correct = 0;
		ArrayList<Question> questionHopper = trivia.getQuestion(amountAsked);
		for(int i = 0; i < amountAsked;i++) {

			String[] options = questionHopper.get(i).getOptions();

			int response = JOptionPane.showOptionDialog(null, questionHopper.get(i).getQuestion(), null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			System.out.println(questionHopper.get(i).getQuestion());
			System.out.println(questionHopper.get(i).getOptionsString());
			if(input.nextLine().equals(questionHopper.get(i).getAnswer())) {
				System.out.println("Correct");
				correct++;
			}
			else {
				System.out.println("Incorrect");
			}
			System.out.println();
		}
		if((double)correct/amountAsked > 0.59) {
			System.out.println("You passed!");
		}
		else {
			System.out.println("You failed!");
		}
		input.close();
	}
	
	
	//Gets high scores from high score object and sends to graphical interface
	//Uses HighScore object to get high scores
	public static int[] getHighScores(HighScore scores) {
		return null;
	}
}
