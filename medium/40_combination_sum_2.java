public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
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
        for (int i = index; i < candidates.length; i++) {
            if (i == index || candidates[i] != candidates[i - 1]) {
                cur.add(candidates[i]);
                helper(res, cur, candidates, target - candidates[i], i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
