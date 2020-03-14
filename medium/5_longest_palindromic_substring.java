public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        int max = 1;
        int left = 0;
        int right = 0;
        boolean[][] isP = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 1 || isP[j + 1][i - 1])) {
                    isP[j][i] = true;
                    if (max < i - j + 1) {
                        max = i - j + 1;
                        left = j;
                        right = i;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }
}

