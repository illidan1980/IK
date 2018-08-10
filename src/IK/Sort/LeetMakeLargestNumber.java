package IK.Sort;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

/**
 * Created by HIMANSHU on 4/30/2016.
 */
public class LeetMakeLargestNumber {
    static class integerStringComprator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            String strO1 = o1.toString() + o2.toString();
            String strO2 = o2.toString() + o1.toString();
            int tracer = 0;
            while(tracer < strO1.length()){
                int temp = strO1.charAt(tracer) - strO2.charAt(tracer);
                if(temp != 0)
                    return temp;
                tracer++;
            }
            return 0;
        }
    }

    public static void main(String[] args){
        int[] arr = {3, 30, 34, 5, 9};
        List<Integer> ints = new ArrayList<Integer>();
        for(int i: arr){
            ints.add(i);
        }
        Integer[] newarr = ints.toArray(new Integer[ints.size()]);
        Arrays.sort(newarr, new integerStringComprator() );
        for(int i = newarr.length - 1; i >= 0; i--){
            System.out.print(newarr[i]);
        }
    }
}
