package IK.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created  on 6/25/2016.
 */
public class CombinationsForFinalScore {
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        int[][] numCombinationsForScore = new int[individualPlayScores.size()][finalScore + 1];
        for(int i = 0; i < individualPlayScores.size(); i++){
            numCombinationsForScore[i][0] = 1;
            for(int j= 0; j <= finalScore; j++){
                int withoutThisPlay = i - 1 >= 0 ? numCombinationsForScore[i - 1][j] : 0;
                int withThisPlay = j >= individualPlayScores.get(i) ?
                        numCombinationsForScore[i][j - individualPlayScores.get(i)] : 0;
                numCombinationsForScore[i][j] = withoutThisPlay + withThisPlay;
            }
        }
        return numCombinationsForScore[individualPlayScores.size() - 1][finalScore];
    }

    public static void main(String[] args){
        System.out.println(numCombinationsForFinalScore(12, new ArrayList<Integer>(Arrays.asList(2,3,7))));
    }
}

