package IK.Strings;

/**
 * Created by HIMANSHU on 5/17/2016.
 */
public class MinWindow {

    public static String minWindow(String text, String pattern){
        char[] s = text.toCharArray();
        char[] p = pattern.toCharArray();
        int[] arr = new int[256];
        int patLength = pattern.length();
        for(int i = 0; i < patLength; i++){
            arr[p[i]]++;
        }
        int min_length = Integer.MAX_VALUE;
        int min_start = -1;
        int end = 0, start = 0;
        while(end < s.length){
            if(arr[s[end]] > 0){
                patLength--;
            }
            arr[s[end]]--;
            while(patLength == 0){
                if(end - start + 1 < min_length){
                    min_start = start;
                    min_length = end - start + 1;
                }
                arr[s[start]]++;
                if(arr[s[start]] > 0){
                    patLength++;
                }
                start++;
            }
            end++;
        }
        if(min_start + min_length > s.length)
            return "";
        return text.substring(min_start, min_start + min_length);
    }

    public static void main(String[] args){
        String str = "abbababaafcaadcadb";
        String pattern = "abc";
        System.out.println("Min Window:" + minWindow(str, pattern));
    }
}
