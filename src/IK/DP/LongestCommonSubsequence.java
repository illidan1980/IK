package IK.DP;

/**
 * Created  on 5/18/2016.
 */
public class LongestCommonSubsequence {
    public static StringBuilder LCSrecur(String s1, String s2, int s1i, int s2i, StringBuilder sb, int sbi, StringBuilder sb1, StringBuilder sb2){
        if(s1i == s1.length() || s2i == s2.length())
            return sb;
        if(s1.charAt(s1i) == s2.charAt(s2i)){
            sb.append(s1.charAt(s1i));
            LCSrecur(s1, s2, s1i + 1, s2i + 1, sb, sbi + 1, sb1, sb2);
        } else {
            //decide which one is bigger based on the length of sb

            sb1 = new StringBuilder(LCSrecur(s1, s2, s1i + 1, s2i, sb, sbi, sb1, sb2));
            sb2 = new StringBuilder(LCSrecur(s1, s2, s1i, s2i + 1, sb, sbi, sb1, sb2));
            // check the length now
            sb.delete(sbi + 1, sb.length());
            if(sb1.length() >= sb2.length()){
                return sb1;
            } else {
                return sb2;
            }
        }
        return sb;
    }
    public static String lcsFixed ( String a, String b){
        if(a.length() == 0 || b.length() == 0)
            return "";
        if(a.charAt(0) == b.charAt(0)){
            return a.charAt(0) + lcsFixed(a.substring(1, a.length()), b.substring(1, b.length()));
        }
        String a1 = lcsFixed(a.substring(1, a.length()), b.substring(0, b.length()));
        String a2 = lcsFixed(a.substring(0, a.length()), b.substring(1, b.length()));

        return a1.length() > a2.length() ? a1 : a2;
    }

    public static String lcs(String a, String b){
        int aLen = a.length();
        int bLen = b.length();
        if(aLen == 0 || bLen == 0){
            return "";
        }else if(a.charAt(aLen-1) == b.charAt(bLen-1)){
            return lcs(a.substring(0,aLen-1),b.substring(0,bLen-1))
                    + a.charAt(aLen-1);
        }else{
            String x = lcs(a, b.substring(0,bLen-1));
            String y = lcs(a.substring(0,aLen-1), b);
            return (x.length() > y.length()) ? x : y;
        }
    }

    public static String LCS(String s1, String s2){
        // start comparing one character at a time
        // print the string as well
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();

        return LCSrecur(s1, s2, 0 , 0, sb, 0, sb1, sb2).toString();
    }
    public static void main(String[] args){
        //take an example which makes sense.
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        // now find the subsequence and print
        System.out.println("LCS: " + LCS(s1, s2));
        System.out.println("LCS: " + lcsFixed(s1, s2));
    }

}
