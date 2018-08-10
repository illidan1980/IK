package IK.Sort;

/**
 * Created by HIMANSHU on 4/29/2016.
 */
public class SortCharacters_CountSort {
    static String sortString(String str){
        int[] arr = new int[256];
        StringBuilder bld = new StringBuilder();
        //trace and fill the array
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            arr[c]++;
        }

        // trace the array and print chars
        for(int i = 0; i < 256; i++){
            while(arr[i] > 0){
                bld.append((char)(arr[i]));
                arr[i]--;
            }
        }
        return bld.toString();
    }
    public static void main (String[] args){
        String str = "abcd";
        System.out.print(sortString(str));
    }
}
