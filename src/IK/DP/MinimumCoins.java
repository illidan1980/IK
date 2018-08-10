package IK.DP;

import java.util.Arrays;

/**
 * Created by Himanshu on 6/30/2016.
 */
public class MinimumCoins {

    private static int coinChangeRecur(int[] coins, int index, int amount, int[] memo, int countCoins){
        if(amount == 0)
            return countCoins;
        if(coins.length == index)
            return Integer.MAX_VALUE;
        if(amount < 0)
            return Integer.MAX_VALUE;

        if(memo[amount] == Integer.MAX_VALUE){
            memo[amount] = Math.min(coinChangeRecur(coins, index + 1, amount, memo, countCoins),
                    coinChangeRecur(coins, index, amount - coins[index], memo, countCoins + 1));
        }
        return memo[amount];
    }
    public static int coinChange(int[] coins, int amount) {
        int[] arr = new int[amount + 1];

        Arrays.fill(arr, Integer.MAX_VALUE);
        int temp = coinChangeRecur(coins, 0, amount, arr, 0);
        if(Integer.MAX_VALUE == temp)
            return -1;
        return temp;
    }

    public static void main (String[] args){
        int[] denom = {1,2,5};
        System.out.println("Min coins " + coinChange(denom, 11));
    }
}
