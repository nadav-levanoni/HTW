/**
 * By: Nadav Levanoni
 */

import java.io.*;
import java.util.*;
public class HighScore {

    static final int MAX_HIGH_SCORES = 10; //amount of high scores that will be tracked

    private static ArrayList<Score> highScoreList;
    private static String highScoresFileName;

    public HighScore() throws IOException {
        highScoresFileName = "highScores.bin";
        File highScoresFile = new File(highScoresFileName);
        if (highScoresFile.exists()) { //if the high score file exists it loads it
            highScoreList = loadHighScores(highScoresFileName);
        }
        else { //else it creates a new high score list
            resetHighScoreList();
        }
    }


    public static void updateList(int score, String playerName, String caveName, int turns, int gold, int arrows, int killWumpus)throws IOException {

        highScoreList.add(new Score(score, playerName, caveName, turns, gold, arrows, killWumpus));
        highScoreList.sort(new HighScoreComparator());

        saveHighScores(highScoreList, highScoresFileName);
    }

    private static ArrayList<Score> loadHighScores(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream( fileName );
        ObjectInputStream in = new ObjectInputStream(inputStream);
        try {
            return (ArrayList<Score>) in.readObject();
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Cannot deserialize high scores from file", e);
        }
    }

    private static void saveHighScores(ArrayList<Score> scores, String fileName) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(outputFile);
        out.writeObject(scores);
        out.flush();
        out.close();
    }


    //this method creates an ArrayList of type Score and then calls the saveHighScoresMethod
    private void resetHighScoreList() throws IOException {
        highScoreList = new ArrayList<Score>();
        for(int i = 1; i <= MAX_HIGH_SCORES; i++) {
            highScoreList.add(new Score(20 - i, "empty " + i, "empty " + i, 99, 0, 0, 0));
        }

        saveHighScores(highScoreList, highScoresFileName);
    }


    public static void printArrayList(){
        for(int i = 0; i < highScoreList.size(); i++){
            System.out.println(highScoreList.get(i));
        }
    }

    public static String getHighScores() {
        String finalHighScore = "";
        for (int i = highScoreList.size()-1; i >= 0; i--) {
            finalHighScore += highScoreList.get(i);
            finalHighScore = finalHighScore + "\n";
        }

        //DEBUG: System.out.print(finalHighScore);
        return finalHighScore;
    }

    public boolean isNewHighScore(int score){
        if(score > highScoreList.get(MAX_HIGH_SCORES-1).getScore())
            return true;
        else
            return false;
    }


}
