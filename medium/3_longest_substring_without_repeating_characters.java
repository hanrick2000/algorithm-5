public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                res = Math.max(res, i - left + 1);
            } else {
                while (!set.add(s.charAt(i))) {
                    set.remove(s.charAt(left++));
                }
            }
        }
        return res;
    }
}
