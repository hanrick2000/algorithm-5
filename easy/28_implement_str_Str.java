public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }
        char[] hay = haystack.toCharArray();
        char[] ne = needle.toCharArray();
        for (int i = 0; i < hay.length - ne.length + 1; i++) {
            if (isSubstring(hay, ne, i)) {
                return i;
            }
        }
        return -1;
    }
    private boolean isSubstring(char[] hay, char[] ne, int i) {
        for (int j = 0; j < ne.length; j++) {
            if (hay[i + j] != ne[j]) {
                return false;
            }
        }
        return true;
    }
}
