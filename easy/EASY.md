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

------

#### 572. Subtree of Another Tree

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

  * 

```java
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }
    public boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (isEql(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }
    public boolean isEql(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && isEql(s.left, t. left) && isEql(s.right, t.right);
        
    }
}
```

----
#### Subtree with Maximum Average

Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

```java
public class Solution {
    private TreeNode res;
    private float max = Integer.MIN_VALUE;
    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        sum(root);
        return res;
    }
    public Res sum(TreeNode root) {
        if (root == null) {
            return new Res(0, 0);
        }
        Res left = sum(root.left);
        Res right = sum(root.right);
        int sum = left.sum + right.sum + root.val;
        int count = left.count + right.count + 1;
        float ave = (float) sum / (float)count;
        if (ave > max) {
            max = ave;
            res = root;
        }
        return new Res(sum, count);
    }
    class Res {
        int sum;
        int count;
        Res(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
}

```
----

#### Max Of Min Altitudes

https://leetcode.com/discuss/interview-question/383669/

Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.

Don't include the first or final entry. You can only move either down or right at any point in time.

Thought process:

Iterate over first row and column. The minimum value must be propagated all the way down the line.
Example:
6, 7, 8
5, 4, 2
8, 7, 6
The top row becomes 6, 6, 6 and the first column becomes 6, 5, 5. Resulting matrix:
6, 6, 6
5, 4, 2
5, 7, 6

Each of the internal elements in the grid will be the minimum of (1) itself, (2) the element above it in the grid, or (3) the element left of it in the grid. Therefore, we want to choose the maximum of two minimum comparisons. Example:
i = 1, j = 1, element = 4.
[i-1, j] = [0, 1] = 6
[i, j-1] = [1, 0] = 5
Therefore, we keep the element 4, since max(min(4, 6), min(4, 5)) == max(4, 4) == 4. For similar reasons, element [1, 2] will remain 2.

Element [2,1], however, will become 5. Note that position [2, 1] can be reached via 6 -> 5 -> 5 -> 7, so we select max(5, 4) and choose 5 as the new element:
i = 2, j = 1, element = 7.
[i-1, j] = [1, 1] = 4
[i, j-1] = [2, 0] = 5
max(min(7, 4), min(7, 5)) == max(4, 5) == 5.
For similar reasons, element [2, 2] will become 5.

We return the value in the bottom right. The answer is 5.

```java
 private static int maxScore1D(int[][] grid) {
    // Assume there is at least one element
    int r = grid.length, c = grid[0].length;
    int[] dp = new int[c];
    // Init
    dp[0] = Integer.MAX_VALUE; // first entry is not considered
    for (int j = 1; j < c; ++j) dp[j] = Math.min(dp[j - 1], grid[0][j]);
    // DP (for each row)
    for (int i = 1; i < r; ++i) {
      // update the first element in each row
      dp[0] = Math.min(dp[0], grid[i][0]);
      for (int j = 1; j < c; ++j) {
        if (i == r - 1 && j == c - 1) {
          dp[j] = Math.max(dp[j - 1], dp[j]); // last entry is not considered
        } else {
          int score1 = Math.min(dp[j - 1], grid[i][j]); // left  dp[i][j-1]
          int score2 = Math.min(dp[j], grid[i][j]);     // up    dp[i-1][j]
          dp[j] = Math.max(score1, score2);
        }
      }
    }
    return dp[c - 1];
  }
```
----

https://leetcode.com/discuss/interview-question/373006/Amazon-or-OA-2019-or-Favorite-Genres

```java
class Solution {
   public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
   	Map<String, List<String>> res = new HashMap<>();
   	Map<String, String> songstogenre = new HashMap<>();
   	
   	for(String genre : genreMap.keySet()) {
   		List<String> songs = genreMap.get(genre);
   		for(String song : songs) {
   			songstogenre.put(song, genre);
   		}
   	}
       Map<String, Integer> count = new HashMap();
   	int max = 0;
   	for(String user : userMap.keySet()) {
           count = new HashMap();
           max = 0;
           res.put(user, new ArrayList());
   		List<String> songs = userMap.get(user);
   		for(String song : songs) {
   			String genre = songstogenre.get(song);
   			int c = count.getOrDefault(genre, 0) + 1;
   			count.put(genre, c);
               max = Math.max(c, max);
   		}
           for (String key : count.keySet()) {
               if (count.get(key) == max) {
                   res.get(user).add(key);
               }
           }
   	}
   	return res;
   }
}
```
