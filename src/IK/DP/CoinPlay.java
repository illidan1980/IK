package IK.DP;

import java.util.HashMap;

/**
 * Created by HIMANSHU on 5/18/2016.
 */
public class CoinPlay {
    static int maxWinRecur(int[] intCoins, int start, int end, boolean myTurn, HashMap<Integer, HashMap<Integer, Integer>> mem){
        if(start >= end){
            return 0;
        }
        if(!mem.get(start + 1).containsKey(end)){
            mem.get(start + 1).put(end, maxWinRecur(intCoins, start + 1, end, !myTurn, mem));
        }
        if(!mem.get(start).containsKey(end - 1)){
            mem.get(start).put(end - 1, maxWinRecur(intCoins, start, end - 1, !myTurn, mem));
        }
        if(myTurn) {
            return Math.max(intCoins[start] + mem.get(start + 1).get(end),
                    intCoins[end] + mem.get(start).get(end - 1));
        }
        else {
            return Math.min(mem.get(start + 1).get(end),
                    mem.get(start).get(end - 1));
        }
    }
    static int maxWin(int[] intCoins) {
        HashMap<Integer, HashMap<Integer, Integer>> mem = new HashMap<>(intCoins.length);
        for(int i = 0; i < intCoins.length; i++) {
            mem.put(i, new HashMap<Integer, Integer>());
        }
        return maxWinRecur(intCoins, 0, intCoins.length - 1, true, mem);
    }
    public static void main(String[] args){
        int[] arr = {8,15,3,7};
        System.out.println("Max sum: " + maxWin(arr));
    }
}
