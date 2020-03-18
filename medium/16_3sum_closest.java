public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - temp) < Math.abs(target - res)) {
                    res = temp;
                } else if (temp < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
