package IK.DP;

import java.util.HashSet;
import java.util.Set;

/**
 * Created  on 5/18/2016.
 */
public class WordBreak {
    public static String wordBreak(String text, HashSet<String> dict){
        String ret = "";
        int prev = 0;
        for(int i = 0; i < text.length(); i++){
            for(int j = i; j < text.length(); j++){
                if(dict.contains(text.substring(i, j+1))){
                    ret = ret + text.substring(prev, i) + " " + text.substring(i, j+1);
                    prev = j + 1;
                    i = j;
                    break;
                }
            }
        }
        return ret.trim();
    }
    public static void main(String[] args){
        HashSet<String> set = new HashSet<String>();
        set.add("apple");
        set.add("pie");
        String text = "applepie";
        System.out.println("word break: " + wordBreak(text, set).trim());
    }
}
