public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
           return res;
        } 
        StringBuilder sb = new StringBuilder();
        helper(res, n, n, n, sb);
        return res;
    }
    private void helper(List<String> res, int left, int right, int n, StringBuilder sb) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }        
        if (left > 0) {
            sb.append('(');
            helper(res, left - 1, right, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right > left) {
            sb.append(')');
            helper(res, left, right - 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
