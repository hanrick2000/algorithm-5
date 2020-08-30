#### 1328. Break a Palindrome
Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.

After doing so, return the final string.  If there is no way to do so, return the empty string.

  Example 1:

  Input: palindrome = "abccba"
  Output: "aaccba"
  Example 2:

  Input: palindrome = "a"
  Output: ""
  
      * if p.length == 1 remove it
      * iterate from 0 - length/2
      * if all 'a', replace the last char.
    
  ```java
  class Solution {
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) {
            return "";
        }
        char[] array = palindrome.toCharArray();
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != 'a') {
                array[i] = 'a';
                return String.valueOf(array);
            }
        }
        array[array.length - 1] = 'b';
        return String.valueOf(array);
    }
}
  ```
  
----

#### 239. Sliding Window Maximum

Given an array nums, there is a sliding window of size k 
which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

  * use a deque, the max is at the left edge of the deque, the new element will be added from right side of the deque, poll all the 
  element smaller than the new element in the deque from right side before the new element is added. 
  * remove left element if it is of out of the window.
  
```java

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peekLast() < nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
            if (i >= k - 1) {
                res[left] = dq.peekFirst();
                if (dq.peekFirst() == nums[left]) {
                    dq.pollFirst();
                }
                left++;
            }
        }
        return res;
    }
}
```
  
 
