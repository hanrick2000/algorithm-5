public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        return new int[] {findLeft(nums, target), findRight(nums, target)};
    }
    private int findRight(int[] nums, int target) {
        int left = 0; 
        int right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        } else if (nums[left] == target){
            return left;
        }
        return -1;
    }
    private int findLeft(int[] nums, int target) {
        int left = 0; 
        int right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target){
            return right;
        }
        return -1;        
    }
}
