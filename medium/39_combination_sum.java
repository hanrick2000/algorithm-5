public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        helper(res, cur, candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> cur, int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(cur));
            return;
        } else if (target < 0) {
            return;
        } else if (index == candidates.length) {
            return;
        }
        int max = target / candidates[index];
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < i; j++) {
                cur.add(candidates[index]);
            }
            helper(res, cur, candidates, target - candidates[index] * i, index + 1);
            for (int j = 0; j < i; j++) {
                cur.remove(cur.size() - 1);
            }
        }
    }
}
**********
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        helper(res, cur, candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> cur, int[] candidates, int target, int index) {
        if (target < 0 || index == candidates.length) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(cur));
                return;
            }
            return;
        }
        int max = target / candidates[index];
        for (int i = 0; i <= max; i++) {
            cur.addAll(Collections.nCopies(i, candidates[index]));
            helper(res, cur, candidates, target - candidates[index] * i, index + 1);
            cur.subList(cur.size() - i, cur.size()).clear();
        }
    }
}
