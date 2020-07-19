### Given a string, find the length of the longest substring without repeating characters.
#### Method 1 use sliding window with HashSet
* Right pointer can move if charactor at the right pointer can be added into the set
* If that charactor could not be added into the set, left pointer should move till the window no longer includes charactor at the right pointer. 
  Then the charactor at the right pointer can be added into the set. The charactors outof the window are also removed from the set.
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                res = Math.max(res, i - left + 1);
            } else {
                while (!set.add(s.charAt(i))) {
                    set.remove(s.charAt(left++));
                }
            }
        }
        return res;
    }
}
```
#### Method 2 use sliding window with HashMap
* HashMap key is the Charactor and value is the pointer of the charactor.
* Record the left pointer of the window, only if the value need to update is larger than the left pointer, left pointer update.
* HashMap do not have to remove the elements, only need to update the value of the element.
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int temp = map.get(s.charAt(i)) + 1;
                left = Math.max(left, temp);
            }
            map.put(s.charAt(i), i);
            max = Math.max(i - left + 1, max);
        }
        return max;
    }
}
```
