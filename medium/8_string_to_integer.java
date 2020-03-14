public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String s = str.trim();
        int i = 0;
        int sign = 1;
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        long res = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            } else {
                res = res * 10 + s.charAt(i) - '0';
            }
            if (res * sign >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (res * sign <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE ;
            }
        }
        return (int) res * sign;
    }
}
