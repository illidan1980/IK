package IK.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Himanshu on 6/4/2016.
 */
public class IntersectionOfSortedArrays {

    public static void main(String[] arr) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        for(int i = 5000; i < 5050; i++){
            arr1.add(i);
        }
        ArrayList<Integer> arr2 = new ArrayList<>();
        for(int i = 0; i < 10000; i++){
            arr2.add(i);
        }
        for (int i : getIntersection(arr1, arr2)) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : intersectTwoSortedArrays(arr1, arr2)) {
            System.out.print(i + " ");
        }
    }

    // m log n
    private static Iterable<Integer> getIntersection(List<Integer> arr1, List<Integer> arr2) {
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < arr1.size(); i++){
            if((i == 0 || arr1.get(i) != arr1.get(i-1)) &&
            Collections.binarySearch(arr2, arr1.get(i)) >= 0){
                ret.add(arr1.get(i));
            }
        }
        return ret;
    }

    // m + n
    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
        List<Integer> intersectionAB = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i).intValue() == B.get(j).intValue() && (i == 0 || A.get(i) != A.get(i - 1))) {
                intersectionAB.add(A.get(i));
                ++i;
                ++j;
            } else if (A.get(i) < B.get(j)) {
                ++i;
            } else { // A.get(i) > B.get(j).
                ++j;
            }
        }
        return intersectionAB;
    }

}
