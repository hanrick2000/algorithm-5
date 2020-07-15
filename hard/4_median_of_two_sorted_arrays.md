### There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
### Method 1
* Time complexity O(m+n). 
  * Find the mid. mid = (l1+l2)/2 
    * l1 + l2 = Odd index of mid is the median
    * l1+ l2 = even index of mid is the median - 1
  * Use move the smaller pointer strategy untile iterate to the mid.
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
                return -1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1 + len2) / 2;
        int me1 = 0;
        int me2 = 0;
        boolean flag;
        if ((len1 + len2) % 2 == 0) {
            flag = true;
        } else {
            flag = false;
        }
        int j = 0;
        int k = 0;
        for (int i = 0; i <= mid; i++) {
            me2 = me1;
            if (j < len1 && k < len2) {
                if (nums1[j] < nums2[k]) {
                    me1 = nums1[j];
                    j++;
                } else if (nums1[j] >= nums2[k]){
                    me1 = nums2[k];
                    k++;
                }
            } else if (j < len1){
                me1 = nums1[j];
                j++;
            } else {
                me1 = nums2[k];
                k++;
            }
        }
        return flag == true ? (me1 + me2) / 2.0 : me1;
    }
}
```
### Method 2
* Time complexity O(log(m+n)). 
```java
public class KthSmallestTwoSortedArray {
  public int kth(int[] a, int[] b, int k) {
    // Assumptions: a, b is not null, at least one of them
    // is not empty, k <= a.length + b.length, k >=1.
    return kth(a, 0, b, 0, k);
  }

  // in the subarray of a starting from index aleft, and
  // subarray of b starting from index bleft, find the kth smallest
  // element among these two subarrays.
  private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
    // three base cases:
    // 1. we already eliminate all the elements in a.
    // 2. we already eliminate all the elements in b.
    // 3. when k is reduced to 1, don't miss this base case.
    // The reason why we have this as base case is in the following
    // logic we need k >= 2 to make it work.
    if (aleft >= a.length) {
      return b[bleft + k - 1];
    }
    if (bleft >= b.length) {
      return a[aleft + k - 1];
    }
    if (k == 1) {
      return Math.min(a[aleft], b[bleft]);
    }
    // we compare the k/2 th element in a's subarray.
    // and the k/2 th element in b's subarray.
    // to determine which k/2 partition can be surely included
    // in the smallest k elements.
    int amid = aleft + k / 2 - 1;
    int bmid = bleft + k / 2 - 1;
    int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
    int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
    if (aval <= bval) {
      return kth(a, amid + 1, b, bleft, k - k / 2);
    } else {
      return kth(a, aleft, b, bmid + 1, k - k / 2);
    }
  }
}


```
