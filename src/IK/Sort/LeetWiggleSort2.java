package IK.Sort;

/**
 * Created by HIMANSHU on 4/30/2016.
 */
//Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
//        Example:
//        (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
//        (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
//
//        Note:
//        You may assume all input has valid answer.
//
//        Follow Up:
//        Can you do it in O(n) time and/or in-place with O(1) extra space?
    // first solution: sort and then swap every pair until the end.
    // so 1,2,3,4,5,6 becomes 2,1,4,3,6,5 Nope! so every pair starting index 1
    // so 1,2,3,4,5,6 becomes 1,3,2,5,4,6
    // order n means no sort, so some partial step in sorting. Selection sort?
    // that still means nlogn / 2. what does wiggle sort mean?
    // quicksort would mean
    //lets learn wigglesort.
    // yeesh! 1,2,3,4,5,6,7 becomes 1,3,2...
    // Unknown if duplicates.
public class LeetWiggleSort2 {
    static void wiggleSort2(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(i % 2 == 1 && arr[i] < arr[i + 1]){
                int temp = arr[i + 1];
                arr [i + 1] = arr[i];
                arr [i] = temp;
            }
        }
    }
    public static void main (String[] args){
        int[] arr = {5,7,343,5,6645,232,232,1,6345};
        wiggleSort2(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
