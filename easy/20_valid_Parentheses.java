public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if ("([{".contains(String.valueOf(c))) {
                stack.offerFirst(c);
            } else {
                if (!stack.isEmpty() && isValid(stack.peekFirst(), c)) {
                    stack.pollFirst();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean isValid(Character a, Character b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
}
