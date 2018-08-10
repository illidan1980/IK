package IK.HashTables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created  on 6/4/2016.
 */
public class EntrySet {



        public static void main(String args[]) {
            // create hash map
            HashMap<Integer, String> newmap = new HashMap();

            // populate hash map
            newmap.put(1, "tutorials");
            newmap.put(2, "point");
            newmap.put(3, "is best");
            newmap.put(1, "tutor");

            // create set view for the map
            Set set=newmap.entrySet();
            for (Map.Entry<Integer, String> e: newmap.entrySet()) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
            // check set values
            System.out.println("Set values: " + set);
        }
    }

