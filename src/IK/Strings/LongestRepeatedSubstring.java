package IK.Strings;

/**
 * Created by HIMANSHU on 5/17/2016.
 */
public class LongestRepeatedSubstring {

    // Do not instantiate.
    private LongestRepeatedSubstring() { }

    /**
     * Returns the longest common string of the two specified strings.
     *
     * @param  s one string
     * @param  t the other string
     * @return the longest common string that appears as a substring
     */

    /**
     * Returns the longest repeated substring of the specified string.
     *
     * @param  text the string
     * @return the longest repeated substring that appears in <tt>text</tt>;
     *         the empty string if no such string
     */
    public static String lrs(String text) {
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                // lrs = sa.select(i).substring(0, length);
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        return lrs;
    }

    /**
     * Unit tests the <tt>lrs()</tt> method.
     */
    public static void main(String[] args) {
        String text = "abababab";
        System.out.println("'" + lrs(text) + "'");
    }
}
