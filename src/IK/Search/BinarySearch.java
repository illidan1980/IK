package IK.Search;

/**
 * Created by Himanshu on 6/5/2016.
 */
public class BinarySearch {
    public static int bsearch(int[]arr, int target){
        if(arr == null) return -1;
        int start = 0, end = arr.length - 1;

        while(start < end){
            if(arr[start] ==  target){
                return start;
            }
            if(arr[end] == target){
                return end;
            }
            int center = (end - start) / 2;
            if(center == 0) return -1;
            if(arr[start + center] > target){
                end -= center;
            } else {
                start += center;
            }
        }
        return -1;
    }

    public static int bsearch1(int[] arr, int target){
        if(arr == null) return -1;
        int start = 0, end = arr.length - 1;

        while(start <= end){
            int center = start + (( end - start )/ 2);
            if(arr[center] == target) return center;
            if(arr[center] > target){
                end = center - 1;
            } else {
                start = center + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(bsearch(new int[]{1,2,3,4,5,6,7,8}, 0));
        System.out.println(bsearch1(new int[]{1,2,3,4,5,6,7,8}, 3));
    }
}
