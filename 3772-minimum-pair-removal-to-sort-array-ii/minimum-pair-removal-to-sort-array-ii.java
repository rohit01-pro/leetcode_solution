import java.util.*;

class Solution {
    class Pair implements Comparable<Pair> {
        long sum;
        int leftIdx;
        public Pair(long sum, int leftIdx) {
            this.sum = sum;
            this.leftIdx = leftIdx;
        }
        @Override
        public int compareTo(Pair other) {
            // Primary sort by sum, secondary sort by leftmost index
            if (this.sum != other.sum) return Long.compare(this.sum, other.sum);
            return Integer.compare(this.leftIdx, other.leftIdx);
        }
    }
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        long[] val = new long[n];
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int unsortedCount = 0;

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            prev[i] = i - 1;
            next[i] = i + 1 == n ? -1 : i + 1;
            if (i > 0) {
                pq.add(new Pair(val[i-1] + val[i], i-1));
                if (val[i-1] > val[i]) unsortedCount++;
            }
        }
        int operations = 0;
        while (unsortedCount > 0 && !pq.isEmpty()) {
            Pair top = pq.poll();
            int i = top.leftIdx;
            if (deleted[i] || next[i] == -1 || deleted[next[i]]) continue;
            int j = next[i];
            
            if (val[i] + val[j] != top.sum) continue;

            if (prev[i] != -1 && val[prev[i]] > val[i]) unsortedCount--;
            if (val[i] > val[j]) unsortedCount--;
            if (next[j] != -1 && val[j] > val[next[j]]) unsortedCount--;
            
            val[i] = val[i] + val[j];
            deleted[j] = true;
            int afterJ = next[j];
            next[i] = afterJ;
            if (afterJ != -1) prev[afterJ] = i;
            if (prev[i] != -1) {
                if (val[prev[i]] > val[i]) unsortedCount++;
                pq.add(new Pair(val[prev[i]] + val[i], prev[i]));
            }
            if (next[i] != -1) {
                if (val[i] > val[next[i]]) unsortedCount++;
                pq.add(new Pair(val[i] + val[next[i]], i));
            }
            operations++;
        }
        return operations;
    }
}