package IK.DP;

import java.util.List;

/**
 * Created  on 6/25/2016.
 */
public class MaximumSubarray {

    public static int findMaximumSubarray(List<Integer> arr){
        int sum = 0;
        int maxSum = 0;
        for(int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            if(sum < 0){
                sum = 0;
            }
            if(sum > maxSum){
                maxSum = sum;
            }
        }
        return maxSum;
    }
}
