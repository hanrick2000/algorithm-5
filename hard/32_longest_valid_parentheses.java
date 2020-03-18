public class Solution {
    public int longestValidParentheses(String s) {
        /**
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] pa = s.toCharArray();
        return helper(pa);
    }
    private int helper(char[] pa) {
        int max = 0;
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < pa.length; i++) {
            if (pa[i] == '(') {
                stack.offerFirst(i);
            } else if (pa[i] == ')') {
                if (stack.isEmpty()) {
                    max = Math.max(sum, max);
                    sum = 0;
                } else {
                    int matchp = stack.pollFirst();
                    int matchl = i - matchp + 1;
                    if (stack.isEmpty()) {
                        sum += matchl;
                        matchl = sum;
                    } else {
                        matchl = i - stack.peekFirst();
                    }
                    max = Math.max(matchl, max);
                }
            }
        }
        return max;
        **/
        if(s.length() <= 1) return 0;
        int curMax = 0;
        int[] longest = new int[s.length()];
        for(int i=1; i < s.length(); i++){
            if(s.charAt(i) == ')' && i-longest[i-1]-1 >= 0 && s.charAt(i-longest[i-1]-1) == '('){
                    longest[i] = longest[i-1] + 2 + ((i-longest[i-1]-2 >= 0)?longest[i-longest[i-1]-2]:0);
                    curMax = Math.max(longest[i],curMax);
            }
        }
        return curMax;
    }
}
