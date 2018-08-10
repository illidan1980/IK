package IK.HashTables;

import java.util.*;

/**
 * Created  on 6/4/2016.
 */
public class AnagramGroups {
    public static List <List< String >> findAnagrams(List < String > dictionary) {
        Map< String, List < String >> sortedStringToAnagrams = new HashMap< >();
        for (String s: dictionary) {
            // Sorts the string, uses it as a key, and then appends
            // the original string as another value in the hash table.
            char[] sortedCharArray = s.toCharArray();
            Arrays.sort(sortedCharArray);
            String sortedStr = new String(sortedCharArray);
            if (!sortedStringToAnagrams.containsKey(sortedStr)) {
                sortedStringToAnagrams.put(sortedStr, new ArrayList<String>());

            }
            sortedStringToAnagrams.get(sortedStr).add(s);

        } List<List<String>> anagramGroups = new ArrayList<>();
        for (Map.Entry<String, List<String>> p : sortedStringToAnagrams.entrySet()) {
            if (p.getValue().size() >= 2) {
                // Found anagrams.
                anagramGroups.add(p.getValue());

            }

        }
        return anagramGroups;

    }
}
