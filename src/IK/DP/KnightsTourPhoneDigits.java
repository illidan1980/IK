package IK.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created  on 5/18/2016.
 */
public class KnightsTourPhoneDigits {
    static int numPhoneNumbers(int startdigit, int phonenumberlength) {
        HashMap<Integer, ArrayList<Integer>> possibleKeys = new HashMap<>(10);
        HashMap<Integer, HashMap<Integer, Integer>> mem = new HashMap<>();
        for(int i = 0; i < 10; i++){
            mem.put(i, new HashMap<Integer, Integer>(10));
        }
        for(int i = 0; i < 10; i++){
            possibleKeys.put(i, new ArrayList<Integer>());
            switch(i){
                case 0:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{4,6});
                    break;
                case 1:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{6,8});
                    break;
                case 2:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{7,9});
                    break;
                case 3:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{4,8});
                    break;
                case 4:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{3,9,0});
                    break;
                case 5:
                    break;
                case 6:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{7,0});
                    break;
                case 7:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{2,6});
                    break;
                case 8:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{1,3});
                    break;
                case 9:
                    Collections.addAll(possibleKeys.get(i), new Integer[]{4,2});
                    break;
                default:
            }
        }

        return numPhoneNumbersRec(startdigit, phonenumberlength, possibleKeys, mem);
    }

    private static int numPhoneNumbersRec(int startdigit, int phonenumberlength, HashMap<Integer, ArrayList<Integer>> possibleKeys, HashMap<Integer, HashMap<Integer, Integer>> mem) {
        if(phonenumberlength <= 0)
            return 1;
        int possibleComb = 0;
        for(int i: possibleKeys.get(startdigit)){
            if(!mem.get(i).containsKey(phonenumberlength - 1)){
                mem.get(i).put(phonenumberlength - 1, numPhoneNumbersRec(i, phonenumberlength - 1, possibleKeys, mem));
            }
            possibleComb += mem.get(i).get(phonenumberlength - 1);
        }
        return possibleComb;
    }

    public static void main(String[] args){
        System.out.println("phonenums: " + numPhoneNumbers(1, 5));
    }

}
