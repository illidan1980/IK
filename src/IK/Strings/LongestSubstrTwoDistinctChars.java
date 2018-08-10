package IK.Strings;

import java.util.HashSet;

/**
 * Created  on 5/17/2016.
 */
public class LongestSubstrTwoDistinctChars {

    public static void main(String[] args){
        String str = "ababebebebababaabe";

        System.out.println(longestSubTwoChars(str));
    }

    private static String longestSubTwoChars(String str) {
        HashSet<Character> set = new HashSet<>(2);
        int length = 0, max_length = Integer.MIN_VALUE;
        int start = 0, max_start = 0;
        for(int i = 0; i < str.length(); i++){
            if(set.size() < 2){
                set.add(str.charAt(i));
                length++;
                continue;
            }
            if(set.contains(str.charAt(i))){
                length++;
                if(length > max_length){
                    max_length = length;
                    max_start = start;
                }
            }else {
                for(Object ch: set){
                    if((Character) ch != str.charAt(i - 1)){
                        set.remove(ch); // very important.
                    }
                }
                start = i - 1;
                while(set.contains(str.charAt(start))){
                    start--;
                }
                start++;
                length = i - start + 1;
                set.add(str.charAt(i));
            }
        }
        if(max_length < 0)
            return "";
        return str.substring(max_start, max_start + max_length);

    }
}
