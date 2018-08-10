package IK.Strings;

import java.util.Arrays;

/**
 * Created by HIMANSHU on 5/17/2016.
 */
public class LongestPalindrome {

    public static void main(String[] args){
        String str = "bababa";
        System.out.println(longestPalindrome(str));
    }
    public static String longestPalindrome(String s) {
        int n = s.length();
        int idx = 0, maxLen = 0;
        StringBuffer sb = new StringBuffer();
        sb.append('^');
        for (int i = 0; i < n; ++i) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        n = 2 * n + 3;
        int mx = 0, id = 0;
        int[] p = new int[n];
        Arrays.fill(p,0);
        for (int i = 1; i < n - 1; ++i) {
            p[i] = (mx > i) ? Math.min(p[2 * id - i], mx - i) : 0;
            while (sb.charAt(i + 1 + p[i]) == sb.charAt(i - 1 - p[i])) ++p[i];
            if (i + p[i] > mx) {
                id = i; mx = i + p[i];
            }
            if (p[i] > maxLen) {
                idx = i; maxLen = p[i];
            }
        }
        idx = (idx - maxLen - 1) / 2;
        return s.substring(idx, idx + maxLen);
    }
    public String longestPalindrome_1(String s) {
        int n = s.length();
        int idx = 0, maxLen = 0;
        int mx = 0, id = 0;
        int[] p = new int[2*n+1];
        Arrays.fill(p,0);
        for (int i = 0; i < 2*n+1; ++i) {
            p[i] = (mx > i) ? Math.min(p[2*id-i], mx - i) : 0;
            int left = i - 1 - p[i],  right = i + 1 + p[i];
            while (left>=0 && right <= 2*n) {
                if (left % 2 == 0 || s.charAt(left/2) == s.charAt(right/2)) {
                    ++p[i];
                } else break;
                --left;
                ++right;
            }
            if (i + p[i] > mx) {
                id = i; mx = i + p[i];
            }
            if (p[i] > maxLen) {
                idx = i; maxLen = p[i];
            }
        }
        idx = (idx - maxLen) / 2;
        return s.substring(idx, idx + maxLen);
    }
}
