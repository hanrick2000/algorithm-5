## Dynamic Programming

  * Prove by induction
  * Solve the bigger problem use the smaller solutions.
  
  ------
  1D original data such as a rope, a word, a peice of wood, find MAX or MIN (cut ,merge, etc)
    
  * if the weight of each smallest element in the original data is identical/similar, then this kind of problem is usually simple:Linear scan and look back to the previouse elements
  * if the weight are not the same: start from the center, for each M[i,j], we usually need to try out all possible k that (i < k < j), M[i,j] = max(M[i,k] +/-/*M[k,j]) for all possible k.
   
   ------
   Longest Ascending subarray
   
   Solution
    1. Base case: M[0] = 1
    2. Induction rule: M[i] represetns the max length of the ascending subarray [from the 0-th element to the i-th element],
   must including the i-th element.
   Only if the i-th element is included in the solution, the i + 1 solution can include the contribution.
   
   M[i] = M[i - 1] + 1 if (input[i-1] < input[i]); 1 otherwise
    3. Time = O(n) Space = O(n) -> O(1)
    
   -------
   Maximal Product when cutting rope
   
   Given a rope with integer-length n, how to cut the rope into m 
   integer-length parts, get the maximal product of the length of the parts. (m > 0)
   
  * Method 1, left part and right part can both be cutted.
  
   ```java
   public int cutRod(int n) {
    int[] M = new int[n+1];
    M[0] = 0;
    M[1] = 1;
    for (int i = 1; i <= n; i++) {
      int curMax = 0;
      for (int j = 1; j <= i/2; j++) {
        curMax = Math.max(curMax, Math.max(j, M[j]) * Math.max(i - j, M[i - j]));
      }
      M[i] = curMax;
    }
    return M[n];
   }
   ```
  * Method 2 left side cannot be cutted (more general than Method 1, because the left
        side may not be possible to be repeatedly used)
        
    Time = O(n^2)
    Space = O(n)
    
------
Jump Game
    
    Given an array of non-negative integers, you are initially positioned at the first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Determin if you are able to reach the last index.
    
Solution:
  1. Base case: there is only one element in the array that is the target itself.
  2. Induction rule: from left to right, 
      M[i] = true if there exists a j, where M[j] = true, and j - i <= input[i]
      false otherwise
    
* Time = O(n^2)
* Space = O(n)
  
---------
Minimum number of jumps (instead wheather or not)

INF if cannot jump to the target.

  1. Base case: M[n] = 0;
  2. Induction rule: M[i] represents the minimum number of steps needed in order to jump
  from the i-th element to the target.
  
  M[i] = 1 + min(M[j]) where j <= i+input[i]
  
* Time = O(n^2)
* Space = O(n)
  
---------
Dictionary Problem

Given a word, can it be composed by concatenating words from a given dictionary?


----------

Largest sum of a subarray

Given an unsorted array, find the subarray that has the greatest sum, return the sum.

Solution:
  * Base case: M[0] = input[0]
  * Indunction rule: M[i] represents (from the 0-th element to the i-th element), the largest sum of the subarray, including the i-th element.
    * M[i] = M[i - 1] + input[i] if (M[i - 1] > 0)
    * input[i] otherwise
  * Time = O(n)
  * Space = O(n) -> O(1)

How to return the boarder of the subaarray?

  * index start, end, solution start, solution end;
  * when we reset M[i] = input[i], we reset start = i, end = i, 
  * local_sum = input[i]
  * when global max is updated to a larger value, we set solution start = start, solution end = i
  
-----------


    
        
        
   
