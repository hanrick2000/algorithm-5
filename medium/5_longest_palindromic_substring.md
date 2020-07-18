### Given a string s, find the longest palindromic substring in s.
    * T(i, j) stands for from index i to index j, whether the substring is a palindrom.
    * Base case T(i, i) is a parlindrom.
    * Build a boolean matrix, the diagonal elements can be filled as true. That means we only have the diagonal elements ready.
    * T(i, j) is true if s(i) == s(j) and T(i + 1, j - 1) is true.
    * That means before I fill T(i, j), the element T(i + 1, j - 1) should be filled already.
    * To make sure T(i + 1, j - 1) is filled, the direction of filling the boolean matrix should be from down to up, from left to right.
    
```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean[][] isP = new boolean[s.length()][s.length()];
        int left = 0;
        int right = 0;
        int max = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (i == j || i == j - 1 || isP[i + 1][j - 1])) {
                    isP[i][j] = true;
                    if (max < j - i + 1) {
                        max = j - i + 1;
                        left = i;
                        right = j;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
```
