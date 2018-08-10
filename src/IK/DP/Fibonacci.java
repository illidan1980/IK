package IK.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Himanshu on 6/25/2016.
 */
public class Fibonacci {
    private static Map<Integer, Integer> cache = new HashMap<>();
    public static int fibonacci(int n){
        if(n <= 1){
            return n;
        }

        if(!cache.containsKey(n)){
            cache.put(n, fibonacci(n-2) + fibonacci(n-1));
        }

        return cache.get(n);
    }
}