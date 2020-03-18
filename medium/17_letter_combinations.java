public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        String[] dig = new String[] {"0", "1","abc","def","ghi","jkl","mno","pqrs","tuv", "wxyz"};
        helper(digits, res, sb, dig);
        return res;
    }
    private void helper(String digits, List<String> res, StringBuilder sb, String[] dig) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (Character c : dig[digits.charAt(sb.length()) - '0'].toCharArray()) {
            sb.append(c);
            helper(digits, res, sb, dig);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
