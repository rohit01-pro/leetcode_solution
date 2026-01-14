//rohit01-pro solved with priorityqueue(min-heap),by sorting both the intervals.
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[] result = new int[m];
        int intervalIdx = 0;
        
        for (int i = 0; i < m; i++) {
            int queryVal = sortedQueries[i][0];
            int originalIdx = sortedQueries[i][1];
            
            while (intervalIdx < n && intervals[intervalIdx][0] <= queryVal) {
                int l = intervals[intervalIdx][0];
                int r = intervals[intervalIdx][1];
                minHeap.offer(new int[]{r - l + 1, r});
                intervalIdx++;
            }
            while (!minHeap.isEmpty() && minHeap.peek()[1] < queryVal) {
                minHeap.poll();
            }
            result[originalIdx] = minHeap.isEmpty() ? -1 : minHeap.peek()[0];
        }
        return result;
    }
}