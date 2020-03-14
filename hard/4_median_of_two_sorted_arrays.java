public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k = nums1.length + nums2.length;
        return k % 2 == 0 ? (helper(nums1, 0, nums2, 0, k / 2) + helper(nums1, 0, nums2, 0, k / 2 + 1)) / 2.0 :
        helper(nums1, 0, nums2, 0, k / 2 + 1);
    }
    private int helper(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midi = i + k / 2 - 1;
        int midj = j + k / 2 - 1;
        int valuei = midi < nums1.length ? nums1[midi] : Integer.MAX_VALUE;
        int valuej = midj < nums2.length ? nums2[midj] : Integer.MAX_VALUE;
        return valuei < valuej ?
        helper(nums1, midi + 1, nums2, j, k - k / 2) :
        helper(nums1, i, nums2, midj + 1, k - k / 2);
    }
}
