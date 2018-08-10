package IK.Sort;
//274. H-Index   My Submissions QuestionEditorial Solution
//        Total Accepted: 32354 Total Submissions: 110119 Difficulty: Medium
//        Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
//        According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
//
//        For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
//0,1,3,5,6
//1,2,3,4,5
//        Note: If there are several possible values for h, the maximum one is taken as the h-index
// can't do without sorting. sort and return the index where the first value in the sorted array where the value is greater than index

import java.util.Arrays;

/**
 * Created by HIMANSHU on 4/30/2016.
 */
public class LeetHIndex {
    static int hIndex(int[] cit){
        Arrays.sort(cit);
        int temp = cit.length;
        for(int i = 0; i < cit.length; i++){
            if(cit[i] >= temp - i){
                return temp - i;
            }
        }
        return 0;
    }

    static int hIndexSelectionSort(int[] cit){

        for(int i = 0; i < cit.length; i++){
            int j= 1;
            for(; j < cit.length - i; j++){
                if(cit[j] < cit[j - 1]){
                    int temp = cit[j];
                    cit[j] = cit[j-1];
                    cit[j-1] = temp;
                }
            }
            // greatest placed in it's place
            //i is tracking how many placed so far --> i + 1
            // when the latest element placed (it's value) becomes less than the the latest value placed, we break.
            if(i + 1 > cit[--j])
                return i;
        }
        return cit.length;
    }

    public static void main (String[] args){
        int[] citations = {3,4,4,6,7,3,2,1};
        System.out.println("HIndex  = " + hIndex(citations));
        System.out.println("HIndex  = " + hIndexSelectionSort(citations));
    }
}
