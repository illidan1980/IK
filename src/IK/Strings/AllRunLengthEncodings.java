package IK.Strings;

import java.util.Scanner;

/**
 * Created  on 5/17/2016.
 */
public class AllRunLengthEncodings {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int longestNume = str.length() - 2;
        StringBuilder temp;
        while(longestNume >= 2) {
            temp = new StringBuilder();
            for (int i = 0; i + longestNume + 1 < str.length(); i++) {
                //print the longest numeronym first
                temp.append("\"" + str.substring(0, i + 1) + longestNume + str.substring(i + 1 + longestNume) + "\"" + ",");
            }
            temp.deleteCharAt(temp.lastIndexOf(","));
            longestNume--;
            System.out.println(temp.toString());
        }
    }
}
