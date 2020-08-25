Use DFS, put the solution in a List<Integer> cur, the integer in cur stands for the col of Queen at this row. The size of cur if the row of current position, search all the possible col for this row.
Find out whether the position is valid by checking 90, 45 and 135 degree positions.
```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, n);
        return res;
    }
    public void helper(List<List<String>> res, List<Integer> cur, int n) {
        if (cur.size() == n) {
            List<String> c = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] array = new char[n];
                for (int j = 0; j < n; j++) {
                    if (cur.get(i) == j) {
                        array[j] = 'Q';
                    } else {
                        array[j] = '.';
                    }
                }
                c.add(String.valueOf(array));
            }
            res.add(new ArrayList<String>(c)); 
            return;
        }
        for (int i = 0; i < n; i++) {
            if (valid(cur, i)) {
                cur.add(i);
                helper(res, cur, n);
                cur.remove(cur.size() - 1);
            }
        }
    }
    public boolean valid(List<Integer> cur, int col) {
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i) == col || Math.abs(cur.get(i) - col) == cur.size() - i) {
                return false;
            }
        }
        return true;
    }
}
```
