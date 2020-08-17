/**
 * By: Nadav Levanoni
 */
import java.io.Serializable;

public class Score implements Serializable
{
    private int score;
    private String playerName;
    private String caveName;
    private int turns;
    private int gold;
    private int arrows;
    private int killWumpus;

    public Score(int score, String playerName, String caveName, int turns, int gold, int arrows, int killWumpus)
    {
        this.score = score;
        this.playerName = playerName;
        this.caveName = caveName;
        this.turns = turns;
        this.gold = gold;
        this.arrows = arrows;
        this.killWumpus = killWumpus;

    }

    public int getScore(){
        return score;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getCaveName(){
        return caveName;
    }

    public int getTurns(){
        return turns;
    }

    public int getArrows(){
        return arrows;
    }

    public int getKillWumpus(){
        return killWumpus;
    }

    public String toString(){
        return "Player Name: " + playerName + ", Score: " + score + ", Cave Name: " + caveName + "\nTurns: " + turns + ", Arrows used: " + arrows + ", Killed Wumpus: " + killWumpus + "\n";
    }
}
