package IK.Strings;

import java.util.Scanner;

/**
 * Created by HIMANSHU on 5/17/2016.
 */
public class StringInterleave {
    public static boolean isInterleave(String i, String a, String b){
        int indI = 0, indA = 0, indB = 0;
        if(i.length() != a.length() + b.length())
            return false;
        while(indI < i.length()){
            if(indA < a.length() && i.charAt(indI) == a.charAt(indA)) {
                indA++;
            }
            else if(indB < b.length() && i.charAt(indI) == b.charAt(indB)) {
                indB++;
            }
            indI++;
        }
        if(indA != a.length() || indB != b.length())
            return false;
        else
            return true;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String[] words = new String[3];
        for(int i = 0; i < 3; i++){
            words[i] = scan.nextLine();
        }
        String i = words[0];
        String a = words[1];
        String b = words[2];
        System.out.println(isInterleave(i, a, b));
        // if there are characters left in a or b, but not in I then print false.

    }
}
