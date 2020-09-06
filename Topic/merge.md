#### 56. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return intervals;
        }
        int[][] res = new int[intervals.length][2];
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int count = 0;
        res[0] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= res[count][1]) {
                res[count][1] = Math.max(res[count][1], intervals[i][1]);
             } else {
                count++; 
                res[count] = intervals[i];
             }
        }
        return Arrays.copyOf(res, count + 1);
    }
}
```
