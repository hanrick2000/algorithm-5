#### 3. Longest Substring Without Repeating Characters
  * Note this problem is looking for the longest, so update max at the side of the right pointer.
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                max = Math.max(max, i - left + 1);
            } else {
                while (set.add(s.charAt(i))) {
                    set.remove(s.charAt(left));
                    left++;
                }
            }
        }
        return max;
    }
}
```

------

#### 567. Permutation in String

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

  * This is the anagram problem use sliding window, the window size is fixed as the size of s2. Use hashmap to track the characters. 

```java
public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> map = map(s1);
        int match = 0;
        for (int i = 0; i < s2.length(); i++) {
            Character temp = s2.charAt(i);
            Integer count = map.get(temp);
            if (count != null) {
                map.put(temp, count - 1);
                if (count == 1) {
                    match++;
                }
            }
            if (i >= s1.length()) {
                temp = s2.charAt(i - s1.length());
                count = map.get(temp);
                if (count != null) {
                    map.put(temp, count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
            }
            if (match == map.size()) {
                return true;
            }
        }
        return false;
    }
    private HashMap<Character, Integer> map(String s1) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character sc : s1.toCharArray()) {
            Integer count = map.get(sc);
            if (count == null) {
                map.put(sc, 1);
            } else {
                map.put(sc, count + 1);
            }
        }
        return map;
    }
}
```
------

#### 76. Minimum Window Substring
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
  * A combination of the first two problem. Note this is the find min problem, so update the min when left pointer moves.
```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int left = 0;
        int right = 0;
        String res = "";
        HashMap<Character, Integer> map = map(t);
        int match = map.size();
        int min = s.length();
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            Integer temp = map.get(cur);
            if (temp != null) {
                map.put(cur, temp - 1);
                if (temp - 1 == 0) {
                    match--;
                } 
            } 
            while (match == 0) {
                if (min >= i - left + 1) {
                        min = i - left + 1;
                        right = i;
                        res = s.substring(left, right + 1);
                }
                cur = s.charAt(left);
                temp = map.get(cur);
                if (temp != null) {
                    map.put(cur, temp + 1);
                    if (temp == 0) {
                        match++;
                    }
                }
                left++;
            }
        }
        return res;
    }
    public HashMap<Character, Integer> map(String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
}
```

-------

#### 239. Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

  
