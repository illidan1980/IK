package IK.Sort;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HIMANSHU on 5/1/2016.
 */
public class InsertionSort {

    static Integer[] insertionSort(List<Integer> arr){
        // convert between a bunch of these collection types
        // list to array first
        Integer[] arr1 = arr.toArray(new Integer[arr.size()]);
        boolean swapNeeded = false;
        for(int i = 0; i < arr1.length; i++){
            int smallestIndex = i;
            for(int j = i; j < arr1.length; j++){
                //pick the smallest and place at 0 and so on.
                // so the next time the index to place will be 1 and so on--> that is i
                if(arr1[j] < arr1[smallestIndex]){
                    //change smallest
                    smallestIndex = j;
                    swapNeeded = true;
                }
            }
            if(swapNeeded) {
                int temp = arr1[i];
                arr1[i] = arr1[smallestIndex];
                arr1[smallestIndex] = temp;
            } else{
                break;
            }
        }

        return arr1;
        //what's insertion sort?
        // insert every element in it's right place
    }

    static Integer[] selectionSort(Integer[] arr){

        for(int i = 0; i < arr.length; i++){
            boolean swapped = false;
            for(int j = 1; j < arr.length - i; j++){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
        return arr;
    }
    static Integer[] selectionSort1(Integer[] arr){
        // just place the biggest element at the end, then at position one less and so on
        for(int i = arr.length -1; i >= 0; i--){
            //let the biggest element be at 0
            int largestIntIndex = 0;
            for(int j =1; j <= i; j++){
                if(arr[largestIntIndex] < arr[j]){
                    largestIntIndex = j;
                }
            }
        }
        return arr;
    }

  //  static Integer[] heapSort(Integer[] arr){

  //  }
    public static void main(String[] args){
        // take an array and sort it
        // talk about best, average and worst case
        Integer[] arr = {1,2,3,8,4,7,5};
        for(int i: selectionSort(arr)) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: insertionSort(Arrays.asList(arr))) {
            System.out.print(i + " ");
        }
    }

}
