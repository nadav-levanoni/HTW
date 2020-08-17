/**
 * By: Nadav Levanoni
 */

import java.util.Comparator;

public class HighScoreComparator implements Comparator<Score>
{
    public int compare(Score hs1, Score hs2) {
        return hs1.getScore() - hs2.getScore();
    }
}
